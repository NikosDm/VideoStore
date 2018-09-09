function MediaService(module, ServiceName){
    module.factory(ServiceName, ['$http', '$q', 'params', function ($http, $q, params) {

        var GET_MODEL_URL = params.searchUrl;
        var UPDATE_MODEL_URL = params.updateSelected;

        var srvFactory = {
            search: searchMedia,
            update: updateMedia
        };

        return srvFactory;

        function searchMedia(MediaID) {
            var deferred = $q.defer();
            $http({
                url: GET_MODEL_URL,
                method: "GET",
                params: { MediaID: MediaID}
            })
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

        function updateMedia(model) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: UPDATE_MODEL_URL,
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
    }]);
}