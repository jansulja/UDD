(function(){
	
	angular.module('udd.user')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("user", {
			url: '/user',
			views:{

				navbar: {

					templateUrl: "app/components/core/navbar.html",
					controller: 'CoreController',
					controllerAs: 'coc'

				},
				content: {

					templateUrl: "app/components/user/user.html",
					controller : 'UserController',
					controllerAs : 'usc'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();