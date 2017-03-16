(function(){
	
	angular.module('udd.ebook')
	.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','editModal','Notification'];
	
	function EbookController(Restangular,editModal,Notification){
		
		var ebc = this;
		
		ebc.init = function(){
			Restangular.all('ebook/list').getList()  // GET: /ebooks
			.then(function(ebooks) {
		  // returns a list of users
		  ebc.ebooks = ebooks; // first Restangular obj in list: { id: 123 }
		  
		})
		}

		
		
		
		ebc.edit = function(ebook){
			
			editModal.edit(ebook).then(function(data) {
						Notification.success({message: 'done', delay: 3000});
			});
			
		}

		ebc.new = function(){
			
			editModal.open().then(function(data) {
				ebc.init();
			});
			
		}

		ebc.init();
		
	}
	
	
	
	
	
})();