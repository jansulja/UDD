/**
* CategoryController Controller
*
* Description
*/
(function(){

	angular.module('udd.category').controller('CategoryController',CategoryController); 

	CategoryController.$inject = ['Restangular','removeModal','editCategoryModal','Notification','Category'];

	function CategoryController(Restangular,removeModal,editCategoryModal,Notification,Category) {
		
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
 			
 			var text = 'This will also remove all the ebooks of this category as well as all the users subscribed to this category. Are you sure you want to proceed?';

 			removeModal.open(text).then(function(data) {
				if(data){

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
				

			});

 			
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