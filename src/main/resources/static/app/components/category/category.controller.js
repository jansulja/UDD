/**
* CategoryController Controller
*
* Description
*/
(function(){

	angular.module('udd.category').controller('CategoryController',CategoryController); 

	CategoryController.$inject = ['Restangular','editCategoryModal'];

	function CategoryController(Restangular,editCategoryModal) {
		
		var cac = this;
		
		cac.init = function(){

 			Restangular.all('category').getList()  // GET: /ebooks
 			.then(function(categories) {
		  // returns a list of users
		  cac.categories = categories; // first Restangular obj in list: { id: 123 }
		  console.log(categories);
		})

 		}



 		cac.create = function(){

 			editCategoryModal.open().then(function(data){
 				cac.init();
 			});

 		}

 		cac.init();
 	}  

 })(); 