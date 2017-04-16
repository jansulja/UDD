(function() {
	'use strict';

	angular
		.module('udd.ebook.edit')
		.factory('editModal', editModal);

	editModal.$inject = ['$uibModal', 'Ebook'];
	function editModal($uibModal, Ebook) {
		return {
			open: openEbookModal,
			edit: editEbookModal
		};

		function openEbookModal() {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/ebook/edit/edit.html',
				controller: 'EditController',
				controllerAs: 'edc',
				windowClass: 'app-modal-window',
				resolve:{
					items: function(){
						return {					
							status: 'new'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(newEbook) {
				//return User.post(newUser);
				return newEbook;
			});
		}
		
		function editEbookModal(ebook){
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/ebook/edit/edit.html',
				controller: 'EditController',
				controllerAs: 'edc',
				windowClass: 'app-modal-window',
				resolve:{
					items: function(){
						return {
							ebook: ebook,
							status: 'edit'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(editEbook) {
				//return editUser.put();
				return editEbook;
			});
		}
	}
})();