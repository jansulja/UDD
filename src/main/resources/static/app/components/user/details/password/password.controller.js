/**
* EditPasswordController Controller
*
* Description
*/
(function(){

	angular.module('udd.user.details.password').controller('EditPasswordController',EditPasswordController); 

	EditPasswordController.$inject = ['Restangular','User','Notification','$uibModalInstance'];

 	function EditPasswordController(Restangular,User,Notification,$uibModalInstance) {
		

 		var epc = this;

 		epc.init = function(){

 			epc.passwordModel = {};

 		}

 		epc.cancel = function(){
			$uibModalInstance.dismiss();
		}

 		epc.submitForm =function(){

 			User.customPOST(epc.passwordModel, "changePassword").then(function(data){

 				Notification.success({message: 'Password Changed', delay: 3000});
 				$uibModalInstance.close();

 			},function(data){

 				Notification.error({message: 'Password change error', delay: 3000});
 			});

 		}

 		epc.init();


	}  

})(); 