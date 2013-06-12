define(['common', 'ajax', 'fixtures', 'modules/discussion/discussion'], function(common, ajax, fixtures, Discussion) {

    describe("Discussion", function() {

        var discussion,
            config;

        var conf = {
                    id: 'discussion',
                    fixtures: [
                        '<p class="byline"><span itemscope="" itemtype="http://schema.org/Person" itemprop="author"><a rel="author" itemprop="url name" data-link-name="auto tag link" href="/profile/contributor">Contributor Name</a></span></p>',
                        '<div class="article__container"></div>',
                        '<div class="article__discussion"></div>'
                    ]
                   };

        beforeEach(function() {
            ajax.init({page: {
                ajaxUrl: "",
                edition: "UK"
            }});

            config = {
                page: {
                    commentable: true,
                    shortUrl: 'http://gu.com/p/3gefb',
                    discussionApiUrl: 'fixtures'
                },
                switches: {
                    discussion: true
                }
            };

            mockAjax = jasmine.createSpy('ajax');

            fixtures.render(conf);

            discussion = new Discussion({
                id:      config.page.shortUrl,
                context: document,
                config:  config
            });
        });


        it("should not initialise if article is not commentable", function(){
            config.page.commentable = false;

            discussion = new Discussion({
                id:      config.page.shortUrl,
                context: document,
                config:  config
            }).init();

            expect(discussion).toBe(false);
            expect(discussion.discussionUrl).toBe(undefined);
        });


        it("should correctly build API URL endpoints", function(){
            discussion.init();
            expect(discussion.discussionCountUrl).toContain('/p/3gefb/comments/count');
            expect(discussion.discussionUrl).toContain('/discussion/p/3gefb');
        });


        it("should AJAX request the comment count", function(){
            discussion.init({
                ajax: mockAjax
            });

            expect(mockAjax.calls.length).toBe(1);
        });


        it("should insert Discussion tabs if article has comments", function(){
            runs(function() {
                discussion.init();
            });

            waits(500);

            runs(function() {
                expect(document.querySelectorAll('.d-tabs').length).toBe(1);
            });
        });


        it("should do nothing if article has no comments", function(){
            discussion = new Discussion({
                id:      'http://gu.com/p/3gefb-nocomments',
                context: document,
                config:  config
            }).init();

            waits(500);

            runs(function() {
                expect(document.querySelectorAll('.d-tabs').length).toBe(0);
            });
        });


        it("should fire off an AJAX request when the user clicks on Comments Tab", function(){
            runs(function() {
                discussion.init();
            });

            waits(500);

            runs(function() {
                expect(document.querySelectorAll('.d-tabs').length).toBe(1);
            });
        });

    });
});