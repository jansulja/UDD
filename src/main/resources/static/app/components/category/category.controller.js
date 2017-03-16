/**
* CategoryController Controller
*
* Description
*/
(function(){

	angular.module('udd.category').controller('CategoryController',CategoryController); 

	CategoryController.$inject = ['Restangular'];

 	function CategoryController(Restangular) {
		
 		var cac = this;
		
		Restangular.all('category').getList()  // GET: /ebooks
		.then(function(categories) {
		  // returns a list of users
		  cac.categories = categories; // first Restangular obj in list: { id: 123 }
		  console.log(categories);
		})

	}  

})(); 