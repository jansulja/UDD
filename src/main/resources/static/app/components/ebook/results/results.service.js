/**
* ResultsService Service
*
* Description
*/
(function(){

	angular.module('udd.ebook.results').service('ResultsService',ResultsService); 

	ResultsService.$inject = ['Restangular'];

 	function ResultsService(Restangular) {
		
 		this.results = [];

 		this.setResults = function(data){
 			this.results = data;
 		}

		this.getResults = function(){

			results = [

				{
					author:"aaaaa",
					title:"aaaa",
					language:"aaa",
					keywords:"aaa",
					publicationYear:"aaa",
					filename:"aaa",
					mime:"aaa",
					category:"aaa",
					highlight:"aaaaaa"

				},
								{
					author:"bbb",
					title:"bbbb",
					language:"abaa",
					keywords:"bbbb",
					publicationYear:"bbbba",
					filename:"bbb",
					mime:"bbb",
					category:"bbb",
					highlight:"bbbb"

				}

			];

			return this.results;

		}


	}  

})(); 