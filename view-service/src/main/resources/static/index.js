angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.loadProducts = function () {
        $http.get('http://localhost:8289/v1/app/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.loadProducts();
});