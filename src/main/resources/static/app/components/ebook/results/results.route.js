(function(){
	
	angular.module('udd.ebook.results')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("results", {
			url: '/ebook/results',
			views:{

				navbar: {

					
						templateUrl: "app/components/core/navbar.html",
						controller: 'CoreController',
						controllerAs: 'coc'


				},
				content: {

					templateUrl: "app/components/ebook/results/results.html",
					controller : 'ResultsController',
					controllerAs : 'rec'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();