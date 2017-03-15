(function(){
	
	angular.module('udd.ebook')
		.config(config);
	
	config.$inject = ['$urlRouterProvider', '$stateProvider'];
	function config($urlRouterProvider, $stateProvider){
		$urlRouterProvider.otherwise("/home");

		$stateProvider
		.state("ebook", {
			url: '/ebook',
			views:{

				navbar: {

					templateUrl: "app/components/core/navbar.html",
					controller: 'CoreController',
					controllerAs: 'coc'

				},
				content: {

					templateUrl: "app/components/ebook/ebook.html",
					controller : 'EbookController',
					controllerAs : 'ebc'

				},
				footer: {

					templateUrl: "app/components/core/footer.html"

				}

			}

			//abstract: true
		});
	}
	
	
})();