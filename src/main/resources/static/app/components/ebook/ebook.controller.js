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

			Restangular.all('ebook/list').getList().then(function(ebooks){

				var ebookCount = {};

				for (var i = ebooks.length - 1; i >= 0; i--) {
					var name = ebooks[i].category.name;
					if(ebookCount[name]){
						ebookCount[name]++;
					}else{
						ebookCount[name] = 1;
					}
				}

				ebc.ebookCount = ebookCount;

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

			removeModal.open("Are you sure?").then(function(data) {
				if(data){

					$http.delete("ebook/delete/"+id)
			.then(function(success){
				
				Notification.success({message: 'Ebook deleted', delay: 5000});
				var index = findIndexOfEbook(ebc.ebooks,id);
				ebc.ebookCount[ebc.ebooks[index].category.name]--;
				ebc.ebooks.splice(index, 1);
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

		ebc.getCount = function(category){
			var count = 0;
			if(ebc.ebookCount){
				count = ebc.ebookCount[category.name];
				if(!count){
					count = 0;
				}
			}

			
			return count;
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