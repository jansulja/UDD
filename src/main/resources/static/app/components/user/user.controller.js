/**
* UserController Controller
*
* Description
*/
(function(){

	angular.module('udd.user').controller('UserController',UserController); 

	UserController.$inject = ['Restangular','editUserModal','Notification','User'];

	function UserController(Restangular,editUserModal,Notification,User) {
		
		var usc = this;
		
		usc.init = function(){

 			Restangular.all('user').getList()  
 			.then(function(users) {
		  
		  usc.users = users; 
		  console.log(users);
		})

 		}

 		usc.delete = function(user){
 			User.customDELETE(user.userId)
                          .then(function(res){
                               Notification.success({message: 'Removed', delay: 3000});
                               usc.init();
                           }, 
                           function errorCallback() {
                               Notification.error({message: 'Error removing user', delay: 3000});
                           } 
                          );
 		}

 		usc.create = function(){

 			editUserModal.open().then(function(data){
 				usc.init();
 			});

 		}

 		usc.edit = function(user){
 			editUserModal.edit(user).then(function(data){
 				usc.init();
 			});
 		}

 		usc.init();

 		usc.getUserType = function(user){

 			var retVal;
 			var type = user.type;
 			if(type==='administrator'){
 				retVal = 'admin';
 			}else if (type==='pretplatnik') {
 				if(user.category){
 					retVal = 'sub_cat';
 				}else{
 					retVal = 'sub';
 				}
 			}

 			return retVal;
 		}
 	}  

 })(); 