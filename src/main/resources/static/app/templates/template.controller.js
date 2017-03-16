(function(){
	
	angular.module('udd.category.edit')
	.controller('EditCategoryController',EditCategoryController);
	
	EditCategoryController.$inject = ['Restangular','$uibModalInstance','Category','items','$http','Notification'];
	
	function EditCategoryController(Restangular,$uibModalInstance,Category,items,$http,Notification){
		
		var edc = this;
		edc.title = "";
		

		edc.init = function(){

		}


		edc.cancel = function(){
			$uibModalInstance.dismiss();
		}


		edc.submitForm = function(){

			if(items.status === 'edit'){

				

			}else{

				
			}
		}

		edc.init();

	}





})();