/**
* ResultsController Controller
*
* Description
*/
(function(){

	angular.module('udd.ebook.results').controller('ResultsController',ResultsController); 

	ResultsController.$inject = ['Restangular','ResultsService'];

 	function ResultsController(Restangular,ResultsService) {
		var rec = this;

		rec.results = ResultsService.getResults();

	}  

})(); 