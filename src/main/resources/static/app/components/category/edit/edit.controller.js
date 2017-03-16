(function(){
	
	angular.module('udd.category.edit')
	.controller('EditCategoryController',EditCategoryController);
	
	EditCategoryController.$inject = ['Restangular','$uibModalInstance','Category','items','$http','Notification'];
	
	function EditCategoryController(Restangular,$uibModalInstance,Category,items,$http,Notification){
		
		var ecc = this;
		ecc.title = "";
		

		ecc.init = function(){

			if(items.status === 'edit'){

				ecc.category = items.category;

			}else{

				ecc.category = { name: 'asd'};
				
			}

		}


		ecc.cancel = function(){
			$uibModalInstance.dismiss();
		}


		ecc.submitForm = function(){

			if(items.status === 'edit'){

				ecc.category.put().then(function(){
					Notification.success({message: 'Done', delay: 3000 });
					$uibModalInstance.close(ecc.category);
				},function(){
					Notification.error({message: 'Error updating category', delay: 3000});
				});

			}else{

				Category.post(ecc.category).then(function(){
					Notification.success({message: 'Done', delay: 3000 });
					$uibModalInstance.close(ecc.category);
				},function(){
					Notification.error({message: 'Error creating category', delay: 3000});
				});
				
			}
		}

		ecc.init();

	}





})();