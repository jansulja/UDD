/**
* ResultsController Controller
*
* Description
*/
(function(){

	angular.module('udd.ebook.results').controller('ResultsController',ResultsController); 

	ResultsController.$inject = ['Restangular','ResultsService','Ebook','Notification','$http','LoginService'];

 	function ResultsController(Restangular,ResultsService,Ebook,Notification,$http,LoginService) {
		var rec = this;

		rec.results = ResultsService.getResults();
		rec.currentUser = LoginService.getCurrentUser();
		console.log(results);

		rec.download = function(result){

			// Ebook.get("download/" + result.filename).then(function (response) {
			// 	/* body... */
			// 	Notification.success({message: 'yes', delay: 3000});
			// 	rec.saveData(response.data,result.filename);
			// },function (response) {
			// 	/* body... */
			// });

			$http({
			    method: 'GET',
			    url: 'ebook/download/' + result.filename,
			    //responseType: 'arraybuffer'
			})
			.then(function(response) {
				rec.saveData()(response,result.filename);
			
			},function(response) {
				
				
			});			

			
		}

		rec.saveData = function(){
			 var a = document.createElement("a");
		    document.body.appendChild(a);
		    a.style = "display: none";
		    return function (data, fileName) {
		        var json = JSON.stringify(data),
		            blob = new Blob([json], {type: "application/pdf"}),
		            url = window.URL.createObjectURL(blob);
		        a.href = url;
		        a.download = fileName;
		        a.click();
		        window.URL.revokeObjectURL(url);
		    };
		}

	}  

})(); 