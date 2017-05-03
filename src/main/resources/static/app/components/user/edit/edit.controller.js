(function(){
	
	angular.module('udd.user.edit')
	.controller('EditUserController',EditUserController);
	
	EditUserController.$inject = ['Restangular','$uibModalInstance','User','items','$http','Notification','Category'];
	
	function EditUserController(Restangular,$uibModalInstance,User,items,$http,Notification,Category){
		
		var euc = this;
		
		

		euc.init = function(){

			euc.types = ['administrator','pretplatnik'];
			Category.getList()  
			.then(function(categories) {
					  
					  euc.categories = categories; 

			})

			if(items.status === 'edit'){
				euc.title = "Edit";
				euc.user = items.user;
				euc.status = 'edit';

			}else if(items.status === 'editProfile'){
				euc.title = "Edit";
				euc.user = items.user;
				euc.status = 'editProfile';
			}else{
				euc.title = "New";
				euc.user = {};
				euc.status = 'new';
			}

		}


		euc.cancel = function(){
			$uibModalInstance.dismiss();
		}


		euc.submitForm = function(){

			if(items.status === 'edit' || items.status === 'editProfile'){
				euc.user.route = "user";
				euc.user.put().then(function(){
					Notification.success({message: 'Updated', delay: 3000 });
					$uibModalInstance.close(euc.user);
				},function(){
					Notification.error({message: 'Error updating user', delay: 3000});
				});

			}else{

				User.post(euc.user).then(function(){
					if(euc.status === 'editProfile'){
						Notification.success({message: 'User Profile Updated', delay: 3000 });
					}else{
						Notification.success({message: 'User Created', delay: 3000 });
					}

					
					$uibModalInstance.close(euc.user);
				},function(){
					Notification.error({message: 'Error creating user', delay: 3000});
				});
				
			}
		}

		euc.init();

	}





})();