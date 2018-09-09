function ProfileController(module, ControllerName, ServiceName){
    module.controller(ControllerName, ['$scope', '$timeout', ServiceName, function($scope, $timeout, Service){

        var self = $scope;
        self.SelectedUser = new ProfileCtr({});
        self.UserLoader = true;
        self.SaveLoader = false;

        self.searchSearchUser = function(){
            self.UserLoader = true;
            $timeout(function(){
                Service.search().then(
                    function (response) {
                        self.SelectedUser = new ProfileCtr(response.data);
                        self.UserLoader = false;
                    },
                    function () {
                        toastr.error('Could not load Media', 'ERROR');
                        self.UserLoader = false;
                    }
                );
            });
        };

        self.updateUserDetails = function() {
            self.SaveLoader = true;
            $timeout(function() {
                if (self.UserForm.$valid){
                    Service.update(self.SelectedUser)
                        .then(
                            function (response) {
                                self.SelectedUser = new ProfileCtr(response.data);
                                self.SaveLoader = false;
                                toastr.success('User Details was saved successfully', 'SUCCESS');
                            },
                            function () {
                                toastr.error('Could not save User Details', 'ERROR');
                                self.SaveLoader = false;
                            }
                        );
                }
                else {
                    toastr.error('Form is invalid', 'ERROR');
                    self.UserLoader = false;
                }
            });
        };

        self.searchSearchUser();
    }]);

    module.directive("fileRead", [
        function() {
            return {
                scope: {
                    fileRead: "="
                },
                link: function(scope, element, attributes) {
                    element.bind("change", function(changeEvent) {
                        var reader = new FileReader();
                        reader.onload = function(loadEvent) {
                            scope.$apply(function() {
                                scope.fileRead = loadEvent.target.result;
                            });
                        };
                        reader.readAsDataURL(changeEvent.target.files[0]);
                    });
                }
            }
        }
    ]);
}