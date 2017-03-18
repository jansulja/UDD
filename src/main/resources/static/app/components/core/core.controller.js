(function(){
	
	angular.module('udd.core')
		.controller('CoreController',CoreController);
	
	CoreController.$inject = ['LoginService','$state','searchModal','ResultsService'];
	
	function CoreController(LoginService,$state,searchModal,ResultsService){
		
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
				ResultsService.setResults(data);
				if($state.current.name==='results'){
					$state.reload();
				}else{
					$state.go('results');
				}
				
			});
		}
		
		coc.init();
		
	}
	
})();