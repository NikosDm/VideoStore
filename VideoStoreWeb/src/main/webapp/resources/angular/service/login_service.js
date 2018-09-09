function LoginService(module, NameService) {
    module.factory(NameService, ['$http', '$window', '$q', function ($http, $window, $q) {
        var REGISTER_URL = '/api/ProfileApi/registerUser';
        var LOGIN_USER = '/j_spring_security_check';

        var srvFactory = {
            registerUser: RegisterUser,
            loginUser: LoginUser
        };

        return srvFactory;

        function RegisterUser(registerUser) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: REGISTER_URL,
                data: JSON.stringify(registerUser)
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    console.error('Error Registering User');
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function LoginUser(loginData) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: LOGIN_USER,
                data: loginData,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    console.error('Error Login');
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }
    }]);
}