(function(){
	
	angular.module('udd.user')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("details", {
			url: '/user/details',
			views:{

				navbar: {

					templateUrl: "app/components/core/navbar.html",
					controller: 'CoreController',
					controllerAs: 'coc'

				},
				content: {

					templateUrl: "app/components/user/details/details.html",
					controller : 'UserDetailsController',
					controllerAs : 'udc'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();