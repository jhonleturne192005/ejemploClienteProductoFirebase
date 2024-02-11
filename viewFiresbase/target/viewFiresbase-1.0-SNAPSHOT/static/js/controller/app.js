angular.module("app",["ngRoute"])
        .config(function($routeProvider)
        {
           $routeProvider
                .when("/productos",{
                    controller: "rest_controller_productos",
                    templateUrl: "productos.html"
                }).otherwise({
                   redirectTo:"/"
               });
        });

