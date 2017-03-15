(function(){
	
	angular.module('udd.ebook')
		.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','editModal'];
	
	function EbookController(Restangular,editModal){
		
		var ebc = this;
		
		Restangular.all('ebook/list').getList()  // GET: /ebooks
		.then(function(ebooks) {
		  // returns a list of users
		  ebc.ebooks = ebooks; // first Restangular obj in list: { id: 123 }
		  console.log(ebooks);
		})
		
		
		ebc.edit = function(ebook){
			
			editModal.edit(ebook).then(function(data) {
				
			});
			
		}
		
	}
	
	
	
	
	
})();