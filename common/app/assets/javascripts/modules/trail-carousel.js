define(['common', 'bonzo', 'qwery', 'bean'], function (common, bonzo, qwery, bean) {

    var trailCarousel = {

        init: function(config, context) {
            var currentIndex = 0,
                size = 6,
                selectedClass = 'trail__carousel-selected',
                carouselTrails = common.$g('.front-trailblock-tone-features .trail', context).each(function(trail, index) {
                if (index < size) {
                    var $trail = bonzo(trail);
                    $trail
                        .addClass('trail--featured')
                        .addClass('trail__carousel');
                    if (index === currentIndex) {
                        $trail.addClass(selectedClass);
                    }
                }
            });

            bean.on(context.querySelector('.front-trailblock-tone-features'), 'click', '.trail__carousel', function(e) {
                e.preventDefault();
                bonzo(carouselTrails[currentIndex]).removeClass(selectedClass);
                currentIndex = (currentIndex + 1) % size;
                bonzo(carouselTrails[currentIndex]).addClass(selectedClass);

            });
        }

    };

    return trailCarousel;

});
