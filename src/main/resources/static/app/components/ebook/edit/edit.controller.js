(function(){
	
	angular.module('udd.ebook.edit')
	.controller('EditController',EditController);
	
	EditController.$inject = ['Restangular','$uibModalInstance','Ebook','items','$http','Notification'];
	
	function EditController(Restangular,$uibModalInstance,Ebook,items,$http,Notification){
		
		var edc = this;
		edc.title = "";
		edc.file = {};
		

		edc.init = function(){

			Restangular.all('category').getList()  // GET: /ebooks
			.then(function(categories) {
					  // returns a list of users
					  edc.categories = categories; // first Restangular obj in list: { id: 123 }

					})

					Restangular.all('language').getList()  // GET: /ebooks
					.then(function(languages) {
					  // returns a list of users
					  edc.languages = languages; // first Restangular obj in list: { id: 123 }

					})

					if(items.status === 'edit'){
						edc.title = "Edit";
						edc.ebook = items.ebook;
					}else{
						edc.title = "Create";

					}

					

				}


				edc.cancel = function(){
					$uibModalInstance.dismiss();
				}

				edc.upload = function(files){

					if(items.status === 'edit'){
						edc.editedUpload = true;
					}

					var fd = new FormData();
					fd.append("file", files[0]);
					edc.file = fd;
					if(edc.editedUpload){
						fd.append('ebookId', new Blob([JSON.stringify(edc.ebook.ebookId)], {
							type: "application/json"
						}));
					}

					$http.post("ebook/upload", fd, {
						withCredentials: true,
						headers: {'Content-Type': undefined },
						transformRequest: angular.identity
					})
					.then(function(success){
						var savedEbookId;
						if(edc.editedUpload){
							savedEbookId = edc.ebook.ebookId;
						}
						edc.ebook = success.data;

						edc.ebook.ebookId = savedEbookId;

						edc.ebook.publicationYear = 1999;
						edc.ebook.mime = "application/pdf";
						edc.ebook.filename = edc.ebook.title+".pdf";
						edc.ebook.language = edc.languages[0];
						edc.ebook.category = edc.categories[0];

					},function(error){
						Notification.error({message: 'Invalid file', delay: 3000});
					});

				}

				edc.submitForm = function(){

					if(items.status === 'edit'){
						
						var fd;

						if(edc.editedUpload){
							fd = edc.file;
						}else{
							fd = new FormData();
						}

						fd.append('ebook', new Blob([JSON.stringify(edc.ebook)], {
							type: "application/json"
						}));

						$http.post("ebook/edit", fd, {
							withCredentials: true,
							headers: {'Content-Type': undefined },
							transformRequest: angular.identity
						})
						.then(function(success){
							$uibModalInstance.close(edc.editedUpload);
							Notification.success({message: 'Updated', delay: 5000});
						},function(error){
							Notification.error({message: 'Error updating ebook', delay: 3000});
						});

					}else{

						edc.file.append('ebook', new Blob([JSON.stringify(edc.ebook)], {
							type: "application/json"
						}));

						$http.post("ebook/insert", edc.file, {
							withCredentials: true,
							headers: {'Content-Type': undefined },
							transformRequest: angular.identity
						})
						.then(function(success){
							$uibModalInstance.close(edc.ebook);
							Notification.success({message: 'Ebook ' + edc.ebook.title +' successfuly saved.', delay: 5000});
						},function(error){
							Notification.error({message: 'Invalid file', delay: 3000});
						});
					}
				}

				edc.init();

			}





		})();