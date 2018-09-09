function MediaController(module, ControllerName, ServiceName) {
    module.controller(ControllerName, ['$scope', '$timeout', 'type', 'mediaID', ServiceName, function($scope, $timeout, type, mediaID, Service){

        var self = $scope;

        self.SelectedModel = new MediaCtr({});

        self.Loaders = {
            mediaLoader: true,
            saveLoader: false
        };

        self.searchSelectedMedia = function(){
            self.Loaders.mediaLoader = true;
            $timeout(function(){
                if (mediaID != 0){
                    Service.search(mediaID).then(
                        function (response) {
                            self.SelectedModel = SetMediaData(response.data);
                            self.Loaders.mediaLoader = false;
                            $timeout(function() {
                                if (RefreshSelectors != undefined){
                                    RefreshSelectors();
                                }
                            });
                        },
                        function (errResponse) {
                            toastr.error('Could not load Media', 'ERROR');
                            self.Loaders.mediaLoader = false;
                        }
                    );
                }
                else {
                    self.SelectedModel = SetMediaData(null);
                    self.Loaders.mediaLoader = false;
                }
            });
        };

        self.updateSelectedMedia = function() {
            self.Loaders.saveLoader = true;
            $timeout(function() {
                if (self.MediaForm.$valid){
                    if (CheckIfMediaImageIsDefault(self.SelectedModel.mediaImageBase64, type)){
                        self.SelectedModel.mediaImageBase64 = '';
                    }
                    Service.update(self.SelectedModel)
                        .then(
                            function (response) {
                                self.SelectedModel = SetMediaData(response.data);
                                self.Loaders.saveLoader = false;
                                toastr.success('Media was saved successfully', 'SUCCESS');
                            },
                            function (errResponse) {
                                toastr.error('Could not load Media', 'ERROR');
                                self.Loaders.saveLoader = false;
                            }
                        );
                }
                else {
                    toastr.error('Form is invalid', 'ERROR');
                    self.Loaders.saveLoader = false;
                }
            });
        };

        function SetMediaData(data){
            if (data != null){
                switch (type){
                    case 1:
                        //VideoGame
                        return $.extend(new MediaCtr(data, type), { videoGameMedia: data.videoGameMedia,
                            manufacturer: data.manufacturer,
                            publisher: data.publisher,
                            developers: data.developers,
                            platforms: data.platforms });
                    case 2:
                        //Movie
                        return $.extend(new MediaCtr(data, type), { director: data.director,
                            movieLanguages: data.movieLanguages,
                            movieCategories: data.movieCategories });
                    case 3:
                        //Music Disk
                        return $.extend(new MediaCtr(data, type), { musician: data.musician,
                            musicGenres: data.musicGenres });
                    default:
                        return $.extend(new MediaCtr(data, type), {});
                }
            }
            else{
                switch (type){
                    case 1:
                        //VideoGame
                        return $.extend(new MediaCtr({}, type), { videoGameMedia: '',
                            manufacturer: '',
                            publisher: '',
                            developers: '',
                            platforms: [] });
                    case 2:
                        //Movie
                        return $.extend(new MediaCtr({}, type), { director: '', movieLanguages: [], movieCategories: [] });
                    case 3:
                        //Music Disk
                        return $.extend(new MediaCtr({}, type), { musician: '',
                            musicGenres: [] });
                    default:
                        return $.extend(new MediaCtr({}, type), {});
                }
            }
        }

        self.searchSelectedMedia();
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