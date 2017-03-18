/**
* uddResults Directive
*
* Description
*/
(function(){

	angular.module('udd.ebook.results').directive('uddResults',uddResults); 

	uddResults.$inject = ['Restangular','ResultsService'];

 	function uddResults(Restangular,ResultsService) {
		return {
			// name: '',
			// priority: 1,
			// terminal: true,
			// scope: {}, // {} = isolate, true = child, false/undefined = no change
			 controller: function($scope, $element, $attrs, $transclude) {

		

			 },
			// require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
			restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
			// template: '<h3>{{result.author}}</h3>',
			 templateUrl: 'app/components/ebook/results/result.html',
			 replace: true,
			// transclude: true,
			// compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
			link: function($scope, iElm, iAttrs, controller) {
							
			}
		};

	}  

})(); 