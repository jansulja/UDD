(function(){
	
	angular.module('udd.ebook.search')
	.controller('SearchController',SearchController);
	
	SearchController.$inject = ['localStorageService','usSpinnerService','Restangular','$uibModalInstance','Ebook','$http','Notification'];
	
	function SearchController(localStorageService,usSpinnerService,Restangular,$uibModalInstance,Ebook,$http,Notification){
		
		var sec = this;
	
		

		sec.init = function(){

			sec.occurances = ['MUST','MUST NOT','SHOULD'];

			sec.searchModel = {

				fields : [

					{
						field:"title",
						value:"",
						occur:"MUST",
						type:"Regular"
					},
					{
						field:"author",
						value:"",
						occur:"MUST",
						type:"Regular"
					},
					{
						field:"keyword",
						value:"",
						occur:"MUST",
						type:"Regular"
					},
					{
						field:"language",
						value:"",
						occur:"MUST",
						type:"Regular"
					},
					{
						field:"text",
						value:"",
						occur:"MUST",
						type:"Regular"
					},


				]

				

			}
			var model = localStorageService.get('searchModel');
			if(model){
				sec.searchModel = model;
			}
			

		}


		sec.cancel = function(){
			$uibModalInstance.dismiss();
		}



		sec.submitForm = function(){

			// $http({
			//     method: 'POST',
			//     url: 'search',
			//     data: sec.searchModel,
			// })
			// .then(function(response) {
				
			// 	Notification.success({message: 'OK', delay: 3000});
			// 	$uibModalInstance.close(response.data);
			// },function(response) {
				
			// 	Notification.error({message: 'ERROR', delay: 3000});
			// });	
			
			localStorageService.set('searchModel',sec.searchModel);

			for(var i=0;i<sec.searchModel.fields.length;i++){
				if(!sec.searchModel.fields[i].value){
					sec.searchModel.fields[i].value="";
				}
				if(sec.searchModel.fields[i].value.startsWith('"')){
					//Notification.success({message: 'Phrase', delay: 3000});
					sec.searchModel.fields[i].value = sec.searchModel.fields[i].value.replace(/"/g,'');
					sec.searchModel.fields[i].type = "Phrase";
				}else if(sec.searchModel.fields[i].value.endsWith('~')){
					sec.searchModel.fields[i].value = sec.searchModel.fields[i].value.replace('~','');
					sec.searchModel.fields[i].type = "Fuzzy";
				}else {
						sec.searchModel.fields[i].type = "Regular";
				}
			}
			
			usSpinnerService.spin('spinner-search');

			Restangular.all('search').post(sec.searchModel).then(function(response){
				Notification.success({message: 'Finished', delay: 3000});
				console.log(response);
				usSpinnerService.stop('spinner-search');
				$uibModalInstance.close(response);

			},function(){
				usSpinnerService.stop('spinner-search');
				Notification.error({message: 'ERROR', delay: 3000});
			});

		}

		sec.init();

	}





})();