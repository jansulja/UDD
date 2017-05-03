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
 			epc.passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
 			epc.passwordPatternErrorText = "Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet and 1 Number";
 		}

 		epc.cancel = function(){
			$uibModalInstance.dismiss();
		}

 		epc.submitForm =function(){

 			if(epc.passwordModel.newPassword!==epc.passwordModel.confirmPassword){
 				Notification.error({message: 'New password and confirm password does not match!', delay: 3000});
 			}else{
 				User.customPOST(epc.passwordModel, "changePassword").then(function(data){

 				Notification.success({message: 'Password Changed', delay: 3000});
 				$uibModalInstance.close();
 				

 			},function(data){

 				Notification.error({message: data.data.message, delay: 3000});
 			});
 			}


 			

 		}

 		epc.init();


	}  

})(); 