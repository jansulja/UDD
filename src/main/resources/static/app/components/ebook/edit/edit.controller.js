(function(){
	
	angular.module('udd.ebook.edit')
		.controller('EditController',EditController);
	
	EditController.$inject = ['Restangular','$uibModalInstance','Ebook','items'];
	
	function EditController(Restangular,$uibModalInstance,Ebook,items){
		
		var edc = this;
		edc.ebook = { title: items.ebook.title,author: items.ebook.author};

		edc.cancel = function(){
			$uibModalInstance.dismiss();
		}
		
	}
	
	
	
	
	
})();