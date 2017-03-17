(function() {
	'use strict';

	angular
		.module('udd.ebook.search')
		.factory('searchModal', searchModal);

	searchModal.$inject = ['$uibModal', 'Ebook'];
	function searchModal($uibModal, Ebook) {
		return {
			open: openSearchModal,
		};

		function openSearchModal() {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/ebook/search/search.html',
				controller: 'SearchController',
				controllerAs: 'sec',
				windowClass: 'app-modal-window'
			});
			
			return modalInstance.result.then(function(searchModel) {
				
				return searchModel;
			});
		}
		
		
	}
})();