(function() {
	'use strict';

	angular
		.module('udd.category.edit')
		.factory('editCategoryModal', editCategoryModal);

	editCategoryModal.$inject = ['$uibModal', 'Category'];
	function editCategoryModal($uibModal, Category) {
		return {
			open: openCategoryModal,
			edit: editCategoryModal
		};

		function openCategoryModal() {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/category/edit/edit.html',
				controller: 'EditCategoryController',
				controllerAs: 'ecc',
				resolve:{
					items: function(){
						return {					
							status: 'new'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(newCategory) {
				//return User.post(newUser);
				return newCategory;
			});
		}
		
		function editCategoryModal(category){
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/category/edit/edit.html',
				controller: 'EditCategoryController',
				controllerAs: 'ecc',
				resolve:{
					items: function(){
						return {
							category: category,
							status: 'edit'
						}
					}
				}
			});
			
			return modalInstance.result.then(function(editCategory) {
				//return editUser.put();
				return editCategory;
			});
		}
	}
})();