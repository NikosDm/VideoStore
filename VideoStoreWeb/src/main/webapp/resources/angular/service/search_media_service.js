function SearchMediaService(module, ServiceName){
    module.factory(ServiceName, ['$http', '$q', 'params', function ($http, $q, params) {

        var SEARCH_URL = params.searchUrl;

        var srvFactory = {
            search: searchMedia
        };

        return srvFactory;

        function searchMedia(param) {
            var deferred = $q.defer();
            $http({
                url: SEARCH_URL,
                method: "GET",
                params: { searchParam: param }
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
    }]);
}