(function() {
	'use strict';

	angular
		.module('danceFormApp')
		.controller('LocationDetailController', LocationDetailController);

	LocationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Location'];

	function LocationDetailController($scope, $rootScope, $stateParams, previousState, entity, Location) {
		var vm = this;

		vm.location = entity;
		vm.previousState = previousState.name;

		var unsubscribe = $rootScope.$on('danceFormApp:locationUpdate', function(event, result) {
			vm.location = result;
		});
		$scope.$on('$destroy', unsubscribe);
	}
})();
