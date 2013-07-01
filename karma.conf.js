module.exports = function(karma) {
    karma.set({
        basePath: '',

        frameworks: ['jasmine', 'requirejs'],

        // list of files / patterns to load in the browser
        files: [
            {pattern: 'common/app/assets/javascripts/modules/*.js', included: false},
            {pattern: 'common/app/assets/javascripts/modules/**/*.js', included: false},
            //{pattern: 'common/test/assets/javascripts/spec/*.spec.js', included: false},
            {pattern: 'common/test/assets/javascripts/fixtures/*.js', included: false},
            {pattern: 'common/test/assets/javascripts/helpers/*.js', included: false},
            'common/test/assets/javascripts/spec/Url.spec.js',
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