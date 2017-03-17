(function(){
	
	angular.module('udd.ebook.search')
	.controller('SearchController',SearchController);
	
	SearchController.$inject = ['Restangular','$uibModalInstance','Ebook','$http','Notification'];
	
	function SearchController(Restangular,$uibModalInstance,Ebook,$http,Notification){
		
		var sec = this;
	
		

		sec.init = function(){



		}


		sec.cancel = function(){
			$uibModalInstance.dismiss();
		}



		sec.submitForm = function(){

		}

		sec.init();

	}





})();