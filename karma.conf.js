module.exports = function(karma) {
    karma.set({
        basePath: '',

        frameworks: ['jasmine', 'requirejs'],

        browsers: ['PhantomJS'],

        // list of files / patterns to load in the browser
        files: [
            {pattern: 'common/app/assets/javascripts/common.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/bonzo/src/bonzo.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/qwery/mobile/qwery-mobile.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/bean/bean.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/reqwest/src/reqwest.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/dom-write/dom-write.js', included: false},
            {pattern: 'common/app/assets/javascripts/modules/analytics.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/eventEmitter/EventEmitter.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/swipe/swipe.js', included: false},
            {pattern: 'common/app/assets/javascripts/components/swipeview/src/swipeview.js', included: false},
            {pattern: 'common/app/public/javascripts/vendor/omniture.js', included: false},
            {pattern: 'common/app/test/assets/javascripts/helpers/fixtures.js', included: false},



            {pattern: 'common/app/assets/javascripts/modules/*.js', included: false},
            {pattern: 'common/app/assets/javascripts/modules/**/*.js', included: false},
            {pattern: 'common/test/assets/javascripts/spec/*.spec.js', included: false},
            {pattern: 'common/test/assets/javascripts/fixtures/*.js', included: false},
            {pattern: 'common/test/assets/javascripts/helpers/*.js', included: false},
            //'common/test/assets/javascripts/spec/Url.spec.js',
            'common/test/assets/javascripts/run.js'
        ],

        plugins: [
            'karma-jasmine',
            'karma-requirejs',
            'karma-phantomjs-launcher',
            'karma-teamcity-reporter',
            'karma-chrome-launcher'
            // 'karma-firefox-launcher',
            // 'karma-junit-reporter'
        ]
    });
};