function GridService(module, serviceName) {
    module.factory(serviceName, ['$http', '$q', 'params', function ($http, $q, params) {
        
        var SEARCH_URL = params.searchUrl;
        var UPDATE_PAGE_URL = params.updateListUrl;
        var UPDATE_MODEL_URL = params.updateSelected;
        var DELETE_URL = params.deleteSelected;

        var srvFactory = {
            search: searchList,
            updateAll: updateList,
            update: updateSelected,
            remove: deleteSelected
        };

        return srvFactory;

        function searchList() {
            var deferred = $q.defer();
            $http.get(SEARCH_URL)
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function updateList(models) {
            var deferred = $q.defer();
            $http({
                url: UPDATE_PAGE_URL,
                method: "PUT",
                data: JSON.stringify(models)
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function updateSelected(model) {
            var deferred = $q.defer();
            $http({
                url: UPDATE_MODEL_URL,
                method: "POST",
                data: JSON.stringify(model)
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function deleteSelected(MediaID) {
            var deferred = $q.defer();
            $http({
                url: DELETE_URL,
                method: "DELETE",
                params: { MediaID: MediaID}
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }
    }]);
}