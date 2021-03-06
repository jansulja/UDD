(function() {
	'use strict';

	angular
		.module('udd.ebook.remove')
		.factory('removeModal', removeModal);

	removeModal.$inject = ['$uibModal', 'Ebook'];
	function removeModal($uibModal, Ebook) {
		return {
			open: openRemoveModal,
		};

		function openRemoveModal(text) {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/ebook/remove/remove.html',
				controller: 'RemoveController',
				controllerAs: 'rec',
				//windowClass: 'app-modal-window'
				resolve:{
					items: function(){
						return {
							text: text
						}
					}
				}
			});
			
			return modalInstance.result.then(function(removeModel) {
				
				return removeModel;
			});
		}
		
		
	}
})();