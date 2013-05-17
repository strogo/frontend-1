define(["reqwest", "when"], function (reqwest, when) {

    var makeAbsolute = function () {
        throw new Error("AJAX has not been initialised yet");
    };

    var edition = function () {
        throw new Error("Edition has not been initialised yet");
    };

    function appendEdition(params) {
        var delimiter = params.url.indexOf('?') > -1 ? '&' : '?';
        params.url = params.url + delimiter + '_edition=' + edition;
        return params;
    }

    function ajax(params) {

        var deferred = when.defer();

        params.success = function() {
            deferred.resolve.apply(this, arguments);
        }
        params.error = function() {
            deferred.reject.apply(this, arguments);
        }

        params = appendEdition(params);
        if(params.url.lastIndexOf("http://", 0)!==0){
            params.url = makeAbsolute(params.url);
        }

        ajax.reqwest(params);

        return deferred.promise;
    }

    ajax.reqwest = reqwest; // expose publicly so we can inspect it in unit tests

    ajax.init = function (config) {

        edition = config.page.edition;

        makeAbsolute = function (url) {
            return config.page.ajaxUrl + url;
        };
    };

    return ajax;

});