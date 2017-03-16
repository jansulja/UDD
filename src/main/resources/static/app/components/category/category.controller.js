/**
* CategoryController Controller
*
* Description
*/
(function(){

	angular.module('udd.category').controller('CategoryController',CategoryController); 

	CategoryController.$inject = ['Restangular','editCategoryModal','Notification','Category'];

	function CategoryController(Restangular,editCategoryModal,Notification,Category) {
		
		var cac = this;
		
		cac.init = function(){

 			Restangular.all('category').getList()  // GET: /ebooks
 			.then(function(categories) {
		  // returns a list of users
		  cac.categories = categories; // first Restangular obj in list: { id: 123 }
		  console.log(categories);
		})

 		}

 		cac.delete = function(category){
 			Category.customDELETE(category.categoryId)
                          .then(function(res){
                               Notification.success({message: 'Removed', delay: 3000});
                               cac.init();
                           }, 
                           function errorCallback() {
                               Notification.error({message: 'Error removing category', delay: 3000});
                           } 
                          );
 		}

 		cac.create = function(){

 			editCategoryModal.open().then(function(data){
 				cac.init();
 			});

 		}

 		cac.edit = function(category){
 			editCategoryModal.edit(category).then(function(data){
 				cac.init();
 			});
 		}

 		cac.init();
 	}  

 })(); 