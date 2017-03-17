(function() {
	'use strict';

	angular
		.module('udd.user.edit')
		.factory('editUserModal', editUserModal);

	editUserModal.$inject = ['$uibModal', 'User'];
	function editUserModal($uibModal, User) {
		return {
			open: openUserModal,
			edit: editUserModal,
			editProfile: editUserProfileModal
		};

		function openUserModal() {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/user/edit/edit.html',
				controller: 'EditUserController',
				controllerAs: 'euc',
				resolve:{
					items: function(){
						return {					
							status: 'new'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(newUser) {
				//return User.post(newUser);
				return newUser;
			});
		}
		
		function editUserModal(user){
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/user/edit/edit.html',
				controller: 'EditUserController',
				controllerAs: 'euc',
				resolve:{
					items: function(){
						return {
							user: user,
							status: 'edit'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(editUser) {
				//return editUser.put();
				return editUser;
			});
		}

		function editUserProfileModal(user){
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/user/edit/edit.html',
				controller: 'EditUserController',
				controllerAs: 'euc',
				resolve:{
					items: function(){
						return {
							user: user,
							status: 'editProfile'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(editUser) {
				//return editUser.put();
				return editUser;
			});
		}
	}
})();