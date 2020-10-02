'use strict';
angular.module('todoApp', ['ngRoute', 'AdalAngular'])
    .config(['$routeProvider', '$httpProvider', 'adalAuthenticationServiceProvider', function ($routeProvider, $httpProvider, adalProvider) {

        $routeProvider.when("/Home", {
            controller: "homeCtrl",
            templateUrl: "/App/Views/Home.html",
        }).when("/TodoList", {
            controller: "todoListCtrl",
            templateUrl: "/App/Views/TodoList.html",
            requireADLogin: false,
//            resolve: {
//                token: ['$http', 'todoListSvc', function ($http, todoListSvc) {
//                    return $http.get('/.auth/me').then(function (response) {
//                        todoListSvc.setAuth(response.data[0].access_token);
//                        return response.data[0].access_token;
//                    });
//                }]
//            },
        }).when("/UserData", {
            controller: "userDataCtrl",
            templateUrl: "/App/Views/UserData.html",
        }).otherwise({redirectTo: "/Home"});

        adalProvider.init(
            {
                instance: 'https://login.microsoftonline.com/',
                tenant: 'vlvanchin.onmicrosoft.com',
                clientId: 'd61473c3-0e92-4626-8b69-2861a6c41fed',
                cacheLocation: 'localStorage',
            },
            $httpProvider
        );

    }]);
