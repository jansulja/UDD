(function(){
	
	angular.module('udd.ebook')
	.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','editModal','Notification','$http','LoginService','Category','usSpinnerService','$q'];
	
	function EbookController(Restangular,editModal,Notification,$http,LoginService,Category,usSpinnerService,$q){
		
		var ebc = this;
		
		ebc.init = function(){

			ebc.showTable = true;
			ebc.abort = $q.defer();


			Category.getList()  
			.then(function(categories) {
				ebc.categories = categories; 
			});


			ebc.currentUser = LoginService.getCurrentUser();

			
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



		ebc.getEbooksForCategory = function(name){
			usSpinnerService.spin('spinner-1');
			ebc.showTable=false;

			ebc.abort.resolve();
			ebc.abort = $q.defer();

			Restangular.all('ebook/list/'+name).withHttpConfig({timeout: ebc.abort.promise}).getList()
			.then(function(ebooks) {

				ebc.ebooks = ebooks; 
				usSpinnerService.stop('spinner-1');
				ebc.showTable=true;
				if(ebooks.length==0){
					Notification('Ebook repository is empty.');
				}

			});


		}


		ebc.init();
		
	}
	
	
	
	
	
})();