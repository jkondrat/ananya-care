(function () {
    'use strict';

    angular.module('care', ['ngResource', 'ui.bootstrap', 'localization']).config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', { templateUrl: 'resources/partials/dashboard.html', controller: 'dashboardController' })
            .when('/indicator/new', { templateUrl: 'resources/partials/indicatorForm.html', controller: 'createIndicatorController' })
            .when('/forms', { templateUrl: 'resources/partials/forms/list.html', controller: 'formListController' })
            .when('/forms/new', { templateUrl: 'resources/partials/forms/form.html', controller: 'formController' })
            .when('/forms/:formId', { templateUrl: 'resources/partials/forms/form.html', controller: 'formController' })
            .otherwise({ redirectTo: '/' });
    }]).run(function($rootScope, i18nService) {
        $rootScope.msg = function(key, params) {
            return i18nService.getMessage(key, params);
        };
    });

}());

