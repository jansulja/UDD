(function(){
	
	angular.module('udd.ebook')
	.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','editModal','Notification','$http'];
	
	function EbookController(Restangular,editModal,Notification,$http){
		
		var ebc = this;
		
		ebc.init = function(){
			Restangular.all('ebook/list').getList()
			.then(function(ebooks) {
		  
		  ebc.ebooks = ebooks; 
		  if(ebooks.length==0){
		  	Notification('Ebook repository is empty.');
		  }
		  
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
				
				Notification.success({message: 'Ebook deleted', delay: 5000});
				ebc.init();
			},function(error){
				Notification.error({message: 'Ebook delete failed', delay: 3000});
			});

			

		}

		ebc.init();
		
	}
	
	
	
	
	
})();