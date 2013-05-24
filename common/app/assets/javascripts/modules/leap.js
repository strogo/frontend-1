define(['common'], function(common) {

        // Leap
        var gestureStart;

        var paused = true; // for debug
        var finger

        function vector(start, end) {
            var diff = [
                end[0] - start[0],
                end[1] - start[1],
                end[2] - start[2]
            ];
            var direction;
            var mainAxis = Math.abs(diff[0]) > Math.abs(diff[1]) ? 'x' : 'y';
            if (mainAxis == 'x') {
                direction = (diff[0] > 0) ? 'right' : 'left';
            } else {
                direction = (diff[1] > 0) ? 'up' : 'down';
            }
            return {
                diff: diff,
                direction: direction
            };
        }

        function fingerAt(pos) {
            if (pos) {
                finger.css('top', pos[1]);
                finger.css('left', pos[0]);
                finger.addClass('is-visible');

                // FIXME: find matching band, set
                if (isNowZone) {
                    var story = findStoryAt(pos);
                    if (story) {
                        selectStory(story);
                    }
                }
            } else {
                finger.removeClass('is-visible');
            }
        }

        function handleSwipe(start, end, type) {
            var v = vector(end.startPosition, end.position);
            console.log("GESTURE", type, v.direction, start.handCount) // start, end
            if (type == 'hand') {
                if (start.handCount > 0) {
                    if (v.direction == 'left') {
                        common.mediator.emit('leap:left');
                    } else if (v.direction == 'right') {
                        common.mediator.emit('leap:right');
                    }
                }
            }
        }

        function readGesture(frame) {
            if (frame.gestures && frame.gestures.length > 0) {
                var startSwipeGestures = _.filter(frame.gestures, function(g) {
                    return g.state == 'start' && g.type == 'swipe';
                });
                var stopSwipeGestures = _.filter(frame.gestures, function(g) {
                    return g.state == 'stop' && g.type == 'swipe';
                });
                if (startSwipeGestures.length > 0 && ! gestureStart) {
                    // if starting gestures and not already tracking some
                    gestureStart = startSwipeGestures[0];
                    gestureStart.handCount = frame.hands.length;
                } else if (stopSwipeGestures.length > 0 && gestureStart) {
                    // if stopping gestures and some was started
                    var stoppedGesture = _.find(stopSwipeGestures, function(g) {
                        return g.id == gestureStart.id;
                    });
                    // if stopping a started gesture
                    if (stoppedGesture) {
                        var start = gestureStart;
                        gestureStart = null;
                        return [start, stoppedGesture];
                    }
                }
            }
        }

        var controllerOptions = {enableGestures: true};


    var LeapSwipe = function()  {

        this.init = function() {
            Leap.loop(controllerOptions, function(frame) {
                if (! paused) {
                    console.log(frame);
                }

                var gestureType;
                var fingerCount = frame.fingers.length;
                var firstFinger = frame.fingers[0];

                if (fingerCount == 1 && firstFinger.tipPosition[2] < 0) {
                    gestureType = 'finger';
                } else {
                    gestureType = 'hand';
                }

                var gest = readGesture(frame);
                if (gest) {
                    console.log('got gesture');
                    handleSwipe(gest[0], gest[1], gestureType);
                }
            });
        }
    };

    return LeapSwipe;

});