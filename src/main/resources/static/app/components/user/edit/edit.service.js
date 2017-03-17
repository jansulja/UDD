/**
* User Service
*
* Description
*/
(function(){

	angular.module('udd.user.edit').service('User',User); 

	User.$inject = ['Restangular'];

 	function User(Restangular) {
		return Restangular.all('user');
	}  

})(); 