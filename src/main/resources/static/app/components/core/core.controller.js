(function(){
	
	angular.module('udd.core')
		.controller('CoreController',CoreController);
	
	CoreController.$inject = ['LoginService','$state'];
	
	function CoreController(LoginService,$state){
		
		var coc = this;
		coc.currentUser ={firstname:undefined,lastname:undefined};
		
		coc.init = function(){
			LoginService.updateCurrentUser();
			coc.currentUser.firstname = LoginService.getCurrentUser().firstname;
			coc.currentUser.lastname = LoginService.getCurrentUser().lastname;
		}
		
		coc.logout = function(){
			LoginService.logout(function(){
				$state.go('login');
			});
			
		}
		
		coc.init();
		
	}
	
})();