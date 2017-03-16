/**
* Category Service
*
* Description
*/
(function(){

	angular.module('udd.category.edit').service('Category',Category); 

	Category.$inject = ['Restangular'];

 	function Category(Restangular) {
		return Restangular.all('category');
	}  

})(); 