(function(){
	
	angular.module('udd.core')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("home", {
			url: '/home',
			views:{

				navbar: {

					templateUrl: "app/components/core/navbar.html"

				},
				content: {

					templateUrl: "app/components/core/core.html"

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();