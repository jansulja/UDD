(function() {
	'use strict';

	angular
		.module('udd.user.details.password')
		.factory('editPasswordModal', editPasswordModal);

	editPasswordModal.$inject = ['$uibModal', 'User'];
	function editPasswordModal($uibModal, User) {
		return {
			edit: editPasswordModal
			
		};

		
		function editPasswordModal(user){
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/user/details/password/password.html',
				controller: 'EditPasswordController',
				controllerAs: 'epc',
				resolve:{
					items: function(){
						return {
							user: user
						}
					}
				}
			});
			
			return modalInstance.result.then(function(editUser) {
				return editUser;
			});
		}

	}
})();