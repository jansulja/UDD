(function(){
	
	angular.module('udd.login')
		.controller('LoginController',LoginController);
	
	LoginController.$inject = ['LoginService','$state','Notification'];
	
	function LoginController(LoginService,$state,Notification){
		
		var lic = this;
		
		lic.user = {username:"user1",password:"user1"};
	
		lic.login = function(){
			LoginService.login(lic.user,function(){
				$state.go('home');
			},function(errorMessage){
				Notification.error({message: errorMessage, delay: 3000});
			});
			
		}
	}
	
	
	
	
	
})();