(function(){
	
	angular.module('udd.category')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("category", {
			url: '/category',
			views:{

				navbar: {

					templateUrl: "app/components/core/navbar.html",
					controller: 'CoreController',
					controllerAs: 'coc'

				},
				content: {

					templateUrl: "app/components/category/category.html",
					controller : 'CategoryController',
					controllerAs : 'cac'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();