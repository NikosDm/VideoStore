function SearchMediaController(module, ControllerName, ServiceName) {
    module.controller(ControllerName, ['$scope', '$timeout', '$window', 'searchParam', ServiceName, function($scope, $timeout, $window, searchParam, Service){

        var self = $scope;
        self.MediaList = [];
        self.mediasLoader = true;

        self.searchMedia = function(){
            self.mediasLoader = true;
            $timeout(function(){
                Service.search(searchParam).then(
                    function (response) {
                        self.MediaList = $.map(response.data, function(mediaData) {
                            return new SearchMediaCtr(mediaData);
                        });
                        self.mediasLoader = false;
                    },
                    function (errResponse) {
                        self.mediasLoader = false;
                    }
                );
            });
        };

        self.ViewDetails = function(type, MediaID){
            switch (type) {
                case 1:
                    //VideoGame
                    $window.location.href = '/videoGame/edit?VideoGameID=' + MediaID;
                    break;
                case 2:
                    //Movie
                    $window.location.href = '/movie/edit?MovieID=' + MediaID;
                    break;
                case 3:
                    //Music Disk
                    $window.location.href = '/musicDisk/edit?MusicDiskID=' + MediaID;
                    break;
                default:
                    break;
            }
        };

        self.DisplayMediaType = function(type){
            switch (type) {
                case 1:
                    return 'Video Game';
                case 2:
                    return 'Movie';
                case 3:
                    return 'Music Disk';
                default:
                    return '';
            }
        };

        self.SetReleaseDateWithFormat = function(data){
            return 'Released on: ' +  moment(data).format("MMMM Do YYYY");
        };

        self.searchMedia();
    }]);
}