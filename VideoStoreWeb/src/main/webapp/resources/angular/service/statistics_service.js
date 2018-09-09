function StatisticsService(module, ServiceName){
    module.factory(ServiceName, ['$http', '$q', function ($http, $q) {

        var SEARCH_URL = '/api/StatisticsApi/LoadStatistics';

        var srvFactory = {
            search: loadStatistics
        };

        return srvFactory;

        function loadStatistics() {
            var deferred = $q.defer();
            $http({
                url: SEARCH_URL,
                method: "GET"
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
