(function(){
	
	angular.module('udd.ebook.search')
	.controller('SearchController',SearchController);
	
	SearchController.$inject = ['Restangular','$uibModalInstance','Ebook','$http','Notification'];
	
	function SearchController(Restangular,$uibModalInstance,Ebook,$http,Notification){
		
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
			

		}


		sec.cancel = function(){
			$uibModalInstance.dismiss();
		}



		sec.submitForm = function(){

			Restangular.all('search').post(sec.searchModel).then(function(){
				Notification.success({message: 'OK', delay: 3000});
			},function(){
				Notification.error({message: 'ERROR', delay: 3000});
			});

		}

		sec.init();

	}





})();