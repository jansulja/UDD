(function(){
	
	angular.module('udd.ebook')
	.controller('EbookController',EbookController);
	
	EbookController.$inject = ['Restangular','removeModal','editModal','Notification','$http','LoginService','Category','usSpinnerService','$q','localStorageService'];
	
	function EbookController(Restangular,removeModal,editModal,Notification,$http,LoginService,Category,usSpinnerService,$q,localStorageService){
		
		var ebc = this;
		
		ebc.init = function(){

			ebc.showTable = false;
			ebc.abort = $q.defer();


			Category.getList()  
			.then(function(categories) {
				ebc.categories = categories; 
				ebc.selectedCategoryName = ebc.categories[1].name;

				var currentCategory = localStorageService.get('currentCategoryName');
				if(currentCategory){
					ebc.selectedCategoryName = currentCategory;
				}

				localStorageService.set('currentCategoryName',ebc.selectedCategoryName);
				ebc.getEbooksForCategory(ebc.selectedCategoryName);

			});

			

			ebc.currentUser = LoginService.getCurrentUser();

			
		}

		
		
		
		ebc.edit = function(ebook){
			
			editModal.edit(ebook).then(function(data) {

				// localStorageService.set('currentCategoryName',ebc.category.name);
				 ebc.init();
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

			removeModal.open().then(function(data) {
				if(data){

					$http.delete("ebook/delete/"+id)
			.then(function(success){
				
				Notification.success({message: 'Ebook deleted', delay: 5000});
				ebc.ebooks.splice(findIndexOfEbook(ebc.ebooks,id), 1);
				if(ebc.ebooks.length===0){
					ebc.init();
				}
			},function(error){
				Notification.error({message: 'Ebook delete failed', delay: 3000});
			});


				}
				

			});

			
			

		}

		ebc.download = function(filename){

			$http.get("ebook/download/"+filename)
			.then(function(success){
				
				
			},function(error){
				
			});

			

		}



		ebc.getEbooksForCategory = function(name){
			ebc.selectedCategoryName = name;
			localStorageService.set('currentCategoryName',ebc.selectedCategoryName);

			ebc.spinner = true;
			usSpinnerService.spin('spinner-1');


			ebc.showTable=false;

			ebc.abort.resolve();
			ebc.abort = $q.defer();

			Restangular.all('ebook/list/'+name).withHttpConfig({timeout: ebc.abort.promise}).getList()
			.then(function(ebooks) {

				ebc.ebooks = ebooks; 
				usSpinnerService.stop('spinner-1');
				ebc.spinner = false;
			
				if(ebooks.length==0){
					Notification('There are no books for the selected category');
				}else{
						ebc.showTable=true;
				}

			},function(data){

				ebc.ebooks = [];
				usSpinnerService.stop('spinner-1');
				ebc.spinner = false;
				Notification.error({message: 'Index not found', delay: 3000});
			});


		}

		ebc.getClass = function(name){

			if(name===ebc.selectedCategoryName){
				return "selected-category";
			}

		}

		ebc.getClassCategoryLink = function(){

			if(ebc.currentUser.type!=='administrator'){
				return "not-active";
			}


		}

		function findIndexOfEbook(array,ebookId){

			for (var i = 0; i < array.length; i++) {
				if(array[i].ebookId===ebookId){
					return i;
				}
			}

			return -1;

		}


		ebc.init();
		
	}
	
	
	
	
	
})();