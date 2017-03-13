(function(){
	
	angular.module('udd.login')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("login", {
			url: '/login',
			views:{

				navbar: {

					
						templateUrl: "app/components/core/navbar.html",
						controller: 'CoreController',
						controllerAs: 'coc'


				},
				content: {

					templateUrl: "app/components/login/login.html",
					controller : 'LoginController',
					controllerAs : 'lic'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();