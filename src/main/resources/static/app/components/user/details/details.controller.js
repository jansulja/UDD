/**
* UserDetailsController Controller
*
* Description
*/
(function(){

	angular.module('udd.user.details').controller('UserDetailsController',UserDetailsController); 

	UserDetailsController.$inject = ['User','LoginService','Notification','Restangular','editUserModal','editPasswordModal','$state'];

	function UserDetailsController(User,LoginService,Notification,Restangular,editUserModal,editPasswordModal,$state) {
		
		var udc = this;

		udc.init = function(){

			Restangular.one("login/current").get().then(function(user){
				udc.user = user;
				
			});
		}

		udc.edit = function(){

			editUserModal.editProfile(udc.user).then(function(user){

			});

		}

		udc.changePassword = function(){
			editPasswordModal.edit(udc.user).then(function(user){
				$state.go('login');
			});
		}

		udc.init();

		

	}  

})(); 