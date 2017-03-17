(function(){
	
	angular.module('udd.core')
		.controller('CoreController',CoreController);
	
	CoreController.$inject = ['LoginService','$state','searchModal'];
	
	function CoreController(LoginService,$state,searchModal){
		
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

		coc.search = function(){
			searchModal.open().then(function(data) {
				
			});
		}
		
		coc.init();
		
	}
	
})();