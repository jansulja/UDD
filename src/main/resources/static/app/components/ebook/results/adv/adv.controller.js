(function(){
	
	angular.module('udd.ebook.results.adv')
	.controller('AdvController',AdvController);
	
	AdvController.$inject = ['localStorageService','usSpinnerService','Restangular','$uibModalInstance','Ebook','$http','Notification'];
	
	function AdvController(localStorageService,usSpinnerService,Restangular,$uibModalInstance,Ebook,$http,Notification){
		
		var adc = this;
	
		

		adc.init = function(){

			
			

		}


		adc.cancel = function(){
			$uibModalInstance.dismiss();
		}



		adc.submitForm = function(){

			

		}

		adc.init();

	}





})();