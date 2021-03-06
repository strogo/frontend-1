define(['common', 'ajax', 'modules/adverts/adverts', 'modules/adverts/iframeadslot', 'modules/detect', 'modules/storage'], function(common, ajax, Adverts, IframeAdSlot, detect, storage) {

    //Ignore audience science for these tests.
    storage.remove("gu.ads.audsci");

    beforeEach(function(){
        ajax.init({page: {
            ajaxUrl: "",
            edition: "UK"
        }});
    });

    var config = {
        page: {
            'keywords': 'keyword,go,here',
            'oasUrl':"http://oas.guardian.co.uk/RealMedia/ads/",
            'oasSiteIdHost':"beta.guardian.co.uk",
            'contentType': 'contentType',
            'section': 'section',
            'audienceScienceUrl': 'http://js.revsci.net/gateway/gw.js?csid=E05516',
            'pageId': ''
        },
        switches: {
            'audienceScience': true
        }
    };

    window.guardian = {
        userPrefs: {
            isOff: function() { return false; }
        }
    };
    
    describe("Iframe advert slots", function() {

        var createIframe = IframeAdSlot.prototype.createIframe;

        var slowUrl = "http://oas.guardian.co.uk/RealMedia/ads/adstream_nx.ads/beta.guardian.co.uk/oas.html/12345@[SLOT_NAME]?k=keyword&k=go&k=here&ct=contenttype&pt=contenttype&cat=section&";
        var fastUrl = "http://oas.guardian.co.uk/RealMedia/ads/adstream_sx.ads/beta.guardian.co.uk/oas.html/12345@[SLOT_NAME]?k=keyword&k=go&k=here&ct=contenttype&pt=contenttype&cat=section&";

        beforeEach(function() {
            IframeAdSlot.prototype.createIframe = function() { return; };
        });

        afterEach(function() {
            IframeAdSlot.prototype.createIframe = createIframe;
        });

        it("should generate the correct iframe URL for slow connection", function() {
            detect.getConnectionSpeed = function() { return 'low'; };
            var a = new IframeAdSlot('[SLOT_NAME]', null, config.page);
            expect(a.generateUrl()).toBe(slowUrl);
        });

        it("should generate the correct iframe URL for fast connection", function() {
            detect.getConnectionSpeed = function() { return 'high'; };
            var a = new IframeAdSlot('[SLOT_NAME]', null, config.page);
            expect(a.generateUrl()).toBe(fastUrl);
        });

        it("should know if it's loaded.", function() {
            var a = new IframeAdSlot('[SLOT_NAME]', null, config.page);
            expect(a.loaded).toBe(false);
            a.load();
            expect(a.loaded).toBe(true);
        });
    });

    describe("Advert object", function() {

        var adNodes = document.querySelectorAll('.ad-slot');

        function destroyIframes() {
            for (var i = 0, j = adNodes.length; i<j; ++i) {
                adNodes[i].innerHTML = '';
            }
        }

        beforeEach(function() {
            destroyIframes();
            Adverts.isOnScreen = function() { return true; };
        });

        xit("should be able to create iframe ads", function() {
            Adverts.init(config);
            Adverts.loadAds();

            waitsFor(function() {
                return (adNodes[1].firstChild && adNodes[1].firstChild.nodeName.toLowerCase() === 'iframe');
            }, "ad iframe never appeared", 1000);

            runs(function() {
                for (var i = 0, j = adNodes.length; i<j; ++i) {
                    expect(adNodes[i].firstChild.nodeName.toLowerCase()).toBe('iframe');
                }
            });
        });

    });

});
