(function(){
	
	angular.module('udd.ebook.remove')
	.controller('RemoveController',RemoveController);
	
	RemoveController.$inject = ['Restangular','$uibModalInstance','Ebook','$http','Notification'];
	
	function RemoveController(Restangular,$uibModalInstance,Ebook,$http,Notification){
		
		var rec = this;
	
		

		rec.init = function(){

			

		}


		rec.cancel = function(){
			$uibModalInstance.dismiss();
		}



		rec.submitForm = function(){

			$uibModalInstance.close(true);

		}

		rec.init();

	}





})();