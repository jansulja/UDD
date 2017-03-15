/**
* Ebook Service
*
* Description
*/
(function(){

	angular.module('udd.ebook.edit').service('Ebook',Ebook); 

	Ebook.$inject = ['Restangular'];

 	function Ebook(Restangular) {
		return Restangular.all('ebook');
	}  

})(); 