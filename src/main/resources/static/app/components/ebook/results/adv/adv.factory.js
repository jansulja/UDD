(function() {
	'use strict';

	angular
		.module('udd.ebook.results.adv')
		.factory('advModal', advModal);

	advModal.$inject = ['$uibModal'];
	function advModal($uibModal) {
		return {
			open: openAdvModal,
		};

		function openAdvModal() {
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/components/ebook/results/adv/adv.html',
				controller: 'AdvController',
				controllerAs: 'adc'
			});
			
			return modalInstance.result.then(function(advModel) {
				
				return advModel;
			});
		}
		
		
	}
})();