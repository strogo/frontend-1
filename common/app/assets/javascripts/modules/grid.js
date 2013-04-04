define([], function () {

    var Grid = function (config, context) {

        var els,
            vUnit = 24*2, // twice the grid baseline
            self = this;

        this.reflow = function(){
            [].forEach.call(els, function(el) {
                var children = el.children,
                    len  = children.length,
                    childrenHeight = 0,
                    h = 0;
                    
                for (var i = 0; i < len; i++) {
                    childrenHeight += children[i].offsetHeight;
                }
                if (childrenHeight) {
                    h = childrenHeight % vUnit;
                    h = h ? childrenHeight + vUnit - h : childrenHeight;
                    if (h !== parseInt(el.style.height, 10)) {
                        el.setAttribute("style","height:" + h + "px");
                    }
                }
            });
        };

        els = (context ? context : document).querySelectorAll('.grid-height');

        if(els) {
            self.reflow();
            window.onresize = function() {
                self.reflow();
            };
        }

    };

    return Grid;
});
