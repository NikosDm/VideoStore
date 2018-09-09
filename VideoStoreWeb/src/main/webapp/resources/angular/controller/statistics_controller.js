function StatisticsController(module, ControllerName, ServiceName) {
    module.controller(ControllerName, ['$scope', '$timeout', ServiceName, function($scope, $timeout, Service){

        var self = $scope;
        self.MediaStatistics = null;
        self.UserRoleStatistics = null;
        self.statisticsLoader = true;

        self.searchStatistics = function(){
            self.statisticsLoader = true;
            $timeout(function(){
                Service.search().then(
                    function (response) {
                        self.MediaStatistics = MediaStatisticsCtr(response.data.mediaStatistics);
                        self.UserRoleStatistics = UserRoleStatisticsCtr(response.data.userRoleStatistics);
                        self.statisticsLoader = false;
                    },
                    function (errResponse) {
                        self.statisticsLoader = false;
                    }
                );
            });
        };

        self.searchStatistics();
    }]);

    module.directive("canvasPie", [
        function() {
            return {
                restrict: 'A',
                scope: {
                    canvasPie: '='
                },
                link: function(scope, element, attributes) {
                    element.CanvasJSChart(scope.canvasPie);
                }
            }
        }
    ]);
}