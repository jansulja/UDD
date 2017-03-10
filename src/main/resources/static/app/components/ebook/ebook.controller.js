(function(){
	
	angular.module('udd.ebook')
		.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular'];
	
	function EbookController(Restangular){
		
		var ebc = this;
		
		Restangular.all('ebooks').getList()  // GET: /ebooks
		.then(function(ebooks) {
		  // returns a list of users
		  ebc.ebooks = ebooks; // first Restangular obj in list: { id: 123 }
		  console.log(ebooks);
		})
		
		
	}
	
	
	
	
	
})();