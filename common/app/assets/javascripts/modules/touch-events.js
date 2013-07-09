define(['modules/detect', 'bean'], function(detect, bean) {

    var hasTouch = detect.hasTouchScreen(),
        touch = {},
        tapTimeout,
        isTap;

    function parentIfText(node) {
        return 'tagName' in node ? node : node.parentNode;
    }

    function reset() {
        isTap = false;
        if (tapTimeout) { clearTimeout(tapTimeout); }
        tapTimeout = null;
        touch = {};
    }


    bean.on(document.body, {
        click: function(e) {
            if (!hasTouch) {
                touch.clickHasFired = true;
                bean.fire(e.target, 'tap', [e]);
            }
        },

        touchstart: function(e) {
            isTap = true;
            touch.originalEvent = e;
            touch.el = parentIfText(e.touches[0].target);
        },

        touchmove: function(e) {
            isTap = false;
        },

        touchend: function(e) {
            // delay by one tick so we can cancel the 'tap' event if 'scroll' fires
            // ('tap' fires before 'scroll')
            tapTimeout = setTimeout(function() {
                if (!touch.clickHasFired) {
                    bean.fire(touch.el, 'tap', [touch.originalEvent]);
                }
                reset();
            }, 0);
        },

        touchcancel: function(e) {
            reset();
        }
    });

    bean.on(window, 'scroll', reset);
});