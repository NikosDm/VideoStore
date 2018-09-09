function ProfileService(module, ServiceName){
    module.factory(ServiceName, ['$http', '$q', 'params', function ($http, $q, params) {

        var GET_USER_URL = params.searchUserURL;
        var UPDATE_USER_URL = params.updateUserURL;

        var srvFactory = {
            search: searchUser,
            update: updateUser
        };

        return srvFactory;

        function searchUser() {
            var deferred = $q.defer();
            $http({
                url: GET_USER_URL,
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

        function updateUser(model) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: UPDATE_USER_URL,
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