(function(){
	
	angular.module('udd.core')
		.controller('CoreController',CoreController);
	
	CoreController.$inject = ['LoginService','$state'];
	
	function CoreController(LoginService,$state){
		
		var coc = this;
		coc.currentUser ={firstname:undefined,lastname:undefined};
		
		coc.init = function(){
			LoginService.updateCurrentUser(
					
				function(firstname,lastname){
					coc.currentUser.firstname = firstname;
					coc.currentUser.lastname = lastname;
				}
			
			);
			
		}
		
		coc.logout = function(){
			LoginService.logout(function(){
				$state.go('login');
			});
			
		}

		coc.login = function(){
			
				$state.go('login');
			
			
		}
		
		coc.init();
		
	}
	
})();