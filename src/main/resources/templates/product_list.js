angular.module('app', []).controller('productController', function ($scope, $http) {
  const contextPath = 'http://localhost:8089/app';

  $scope.loadProducts = function () {
      $http.get(contextPath + '/products')
          .then(function (response) {
              $scope.ProductList = response.data;
          })
  }

  $scope.a = 10;

    $scope.deleteProducts = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                console.log(123)
                $scope.loadProducts();
            })
    }

    $scope.loadProducts();

})