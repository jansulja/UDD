(function(){
	
	angular.module('udd.ebook')
	.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','editModal','Notification','$http'];
	
	function EbookController(Restangular,editModal,Notification,$http){
		
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
						if(data){
							ebc.init();
						}
			});
			
		}

		ebc.new = function(){
			
			editModal.open().then(function(data) {
				ebc.init();
			});
			
		}

		ebc.delete = function(id){

			$http.delete("ebook/delete/"+id)
						.then(function(success){
							
							Notification.success({message: 'Edit successfull', delay: 5000});
						},function(error){
							Notification.error({message: 'Edit error', delay: 3000});
						});

			// $http('DELETE', 'ebook/delete/'+id, function(status, response){
			// 	// success
			// }, function(status, response){
			// 	// error
			// });

		}

		ebc.init();
		
	}
	
	
	
	
	
})();