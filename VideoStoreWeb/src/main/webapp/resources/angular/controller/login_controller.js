function LoginController(module, controllerName, serviceName) {
    module.controller(controllerName, ['$scope', '$timeout', '$window', serviceName, function ($scope, $timeout, $window, Service) {
        
        var self = $scope;

        self.LoginModel = {
            username: '',
            password: ''
        };

        self.RegisterModel = {
            username: '',
            password: '',
            firstName: '',
            lastName: '',
            email: ''
        };

        self.ShowLoader = false;
        self.DisableButton = false;

        self.LoginUser = function () {
            self.ShowLoader = true;
            self.DisableButton = true;
            $timeout(function(){
                if (self.LoginForm.$valid) {
                    if (self.LoginModel.password == undefined) {
                        self.LoginModel.password = self.LoginForm.LoginPassword.$viewValue;
                    }
                    var loginData = "LoginUsername="+self.LoginModel.username+"&LoginPassword="+self.LoginModel.password+"&submit=Login";
                    Service.loginUser(loginData)
                        .then(
                            function (successResponse) {
                                if (successResponse.status == 200){
                                    $window.location.href = '/home';
                                }
                                else {
                                    self.ShowLoader = false;
                                    self.DisableButton = false;
                                }
                            },
                            function (errResponse) {
                                debugger;
                                $window.location.href = '/login-error';
                            }
                        );
                }
                else {
                    self.ShowLoader = false;
                    self.DisableButton = false;
                }
            });
        };

        self.RegisterUser = function () {
            self.ShowLoader = true;
            self.DisableButton = true;
            $timeout(function(){
                if (self.RegisterForm.$valid) {
                    if (self.RegisterModel.Password == undefined) {
                        self.RegisterModel.Password = self.RegisterForm.RegisterPassword.$viewValue;
                    }
                    Service.registerUser(self.RegisterModel)
                        .then(
                            function (successResponse) {
                                if (successResponse.status == 200){
                                    $window.location.href = '/home';
                                }
                                else {
                                    /*toastr message*/
                                    self.ShowLoader = false;
                                    self.DisableButton = false;
                                }
                            },
                            function (errResponse) {
                                self.ShowLoader = false;
                                self.DisableButton = false;
                            }
                        );
                }
                else {
                    self.ShowLoader = false;
                    self.DisableButton = false;
                }
            });
        };

    }]);
}