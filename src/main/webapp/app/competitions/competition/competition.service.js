(function() {
	'use strict';
	angular
		.module('danceFormApp')
		.factory('Competition', Competition);

	Competition.$inject = ['$resource', 'DateUtils'];

	function Competition($resource, DateUtils) {
		var resourceUrl = 'api/competitions/:id';

		return $resource(resourceUrl, {}, {
			'query': {method: 'GET', isArray: true},
			'get': {
				method: 'GET',
				transformResponse: function(data) {
					if(data) {
						data = angular.fromJson(data);
						data.date = DateUtils.convertLocalDateFromServer(data.date);
					}
					return data;
				}
			},
			'update': {
				method: 'PUT',
				transformRequest: function(data) {
					var copy = angular.copy(data);
					copy.date = DateUtils.convertLocalDateToServer(copy.date);
					return angular.toJson(copy);
				}
			},
			'save': {
				method: 'POST',
				transformRequest: function(data) {
					var copy = angular.copy(data);
					copy.date = DateUtils.convertLocalDateToServer(copy.date);
					return angular.toJson(copy);
				}
			}
		});
	}
})();
