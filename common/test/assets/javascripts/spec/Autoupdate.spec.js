define(['common', 'ajax', 'bean', 'modules/autoupdate', 'modules/storage'], function(common, ajax, bean, Autoupdate, storage) {

    describe("Auto update", function() {

        var callback,
            delay,
            path,
            attachTo,
            server;

        // Have to stub the global guardian object
        window.guardian = {
            userPrefs : {
                set : function() { return true; },
                get : sinon.stub()
            }
        };

        beforeEach(function() {
            ajax.init({page: {
                ajaxUrl: "",
                edition: "UK"
            }});
            storage.set('gu.prefs.auto-update', 'on');
            path = 'fixtures/autoupdate';
            delay = 1000;
            attachTo = document.getElementById('update-area');
            callback = sinon.stub();
            // set up fake server
            server = sinon.fakeServer.create();
            server.autoRespond = true;

        });

        afterEach(function() {
            callback = null;
            server.restore();
        });

        // json test needs to be run asynchronously
        it("should request the feed and attach response to the dom", function(){

            server.respondWith([200, {}, '{ "html": "<span>foo</span>" }']);
            common.mediator.on('modules:autoupdate:loaded', callback);

            attachTo.innerHTML = '<span>bar</span>';

            var a = new Autoupdate({
                    path: path,
                    delay: delay,
                    attachTo: attachTo,
                    switches: {autoRefresh: true},
                    manipulationType: 'prepend'
                });
                a.init();

            waits(2000);

            runs(function(){
                expect(callback).toHaveBeenCalled();
                expect(attachTo.innerHTML).toBe('<span>foo</span><span>bar</span>');
                a.off();
            });
        });
        
        it("should optionally load the load the first update immediately after the module has initialised", function(){
            
            var callback1 = sinon.stub();
            server.respondWith([200, {}, '{ "html": "<span>foo</span>" }']);
            common.mediator.on('modules:autoupdate:loaded', callback1);

            var a = new Autoupdate({
                    path: path,
                    delay: 10000,
                    attachTo: attachTo,
                    switches: {autoRefresh: true},
                    loadOnInitialise: true
                });
                a.init();

            waits(200); // should be shorter than the 'delay' param

            runs(function(){
                expect(callback1).toHaveBeenCalled();
                expect(attachTo.innerHTML).toBe('<span>foo</span>');
                a.off();
            });
        });


        it("should get user prefs from local storage ", function(){
            server.respondWith([200, {}, '']);
            storage.set('gu.prefs.auto-update', 'off');

            var a = new Autoupdate({
                    path: path,
                    delay: delay,
                    attachTo: attachTo,
                    switches: {autoRefresh: true}
                });

            a.init();

            waits(2000);

            runs(function(){
                 var off = common.$g('[data-action="off"]').hasClass('is-active');
                 expect(off).toBe(true);
                a.off();
            });
        });

        it("should destroy itself if server sends turn off response", function() {
            server.respondWith([200, {}, '{ "refreshStatus": false }']);
            common.mediator.on('modules:autoupdate:destroyed', callback);

            var a = new Autoupdate({
                    path: 'fixtures/badupdate',
                    delay: delay,
                    attachTo: attachTo,
                    switches: {autoRefresh: true}
                });
                a.init();

            waits(2000);

            runs(function(){
                expect(callback).toHaveBeenCalled();
                a.off();
            });
        });
        
        it('should not poll if `autoRefresh` switch turned off (default)', function() {
            server.respondWith([200, {}, '']);
            common.mediator.on('modules:autoupdate:destroyed', callback);
            
            var a = new Autoupdate({
                    path:path,
                    delay:delay,
                    attachTo: attachTo
                });
                a.init();

            waits(2000);

            runs(function(){
                expect(callback).not.toHaveBeenCalled();
                a.off();
            });
        });
       
    });
});
