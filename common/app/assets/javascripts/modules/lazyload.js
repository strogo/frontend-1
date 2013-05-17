define(['common', 'ajax', 'bonzo', 'when'], function (common, ajax, bonzo, when) {

    var lazyLoad = function(opts) {

        /*
            Accepts these options:

            url               - string
            container         - element object
            beforeInsert      - function applied to response html before inserting it into container, optional
            success           - callback function, optional
            jsonpCallbackName - string, optional
            force             - boolean, default false. Reload an already-populated container
        */

        var deferred = when.defer();

        var into;

        opts = opts || {};
        opts.beforeInsert = opts.beforeInsert || function(html) { return html; };

        if (opts.url && opts.container) {
            into = bonzo(opts.container);
            if (opts.force || ! into.hasClass('lazyloaded')) {

                ajax({
                    url: opts.url,
                    type: 'jsonp',
                    jsonpCallbackName: opts.jsonpCallbackName
                }).then(function(json) {
                    into.html(opts.beforeInsert(json.html));
                    into.addClass('lazyloaded');
                    deferred.resolve();
                }, function() {
                    deferred.reject();
                });

            } else {
                deferred.resolve();
            }
        }

        return deferred.promise;
    };

    return lazyLoad;
});
