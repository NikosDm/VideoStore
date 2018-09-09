function GridController(module, controllerName, serviceName) {
    module.controller(controllerName, ['$scope', '$timeout', '$window', 'type', serviceName, function ($scope, $timeout, $window, type, Service) {

        var self = $scope;

        self.ModelList = [];

        self.SelectedDeleteMedia = {
            MediaID: 0,
            MediaDescription: ''
        };

        self.MediaListLoader = true;

        self.searchData = function () {
            self.MediaListLoader = true;
            $timeout(function(){
                Service.search()
                    .then(
                        function (response) {
                            self.ModelList = $.map(response.data, function(mediaData) {
                                return SetGridData(mediaData);
                            });
                            self.MediaListLoader = false;
                        },
                        function (errResponse) {
                            console.error('Error while fetching Users');
                            self.MediaListLoader = false;
                        }
                    );
            });
        };

        self.updateDataList = function (models) {
            Service.updateAll(models)
                .then(
                searchData,
                function (errResponse) {
                    console.error('Error while creating User');
                }
            );
        };

        self.updateData = function (model) {
            Service.update(model)
                .then(
                function (response) {
                    var responseData = response.data;
                    for (var i = 0; i < self.ModelList.length; i++) {
                        if (self.ModelList[i].mediaID == responseData.mediaID) {
                            self.ModelList[i] = SetGridData(responseData);
                            break;
                        }
                    }
                },
                function (errResponse) {
                    console.error('Error while updating User');
                }
            );
        };

        self.AddOrEditMedia = function (MediaID) {
            switch (type){
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

        self.deleteData = function (MediaID) {
            Service.remove(MediaID)
                .then(
                function (response) {
                    for (var i = 0; i < self.ModelList.length; i++) {
                        if (self.ModelList[i].mediaID == response.data) {
                            self.ModelList.splice(i, 1);
                            self.SelectedDeleteMedia.MediaDescription = '';
                            self.SelectedDeleteMedia.MediaID = 0;
                            break;
                        }
                    }
                },
                function (errResponse) {
                    console.error('Error while deleting Media');
                }
            );
        };

        self.InitializeDeleteModal = function (MediaID, MediaCode, MediaTitle){
            self.SelectedDeleteMedia.MediaDescription = "Are you sure you want to delete movie: <b>" + MediaCode + " - " + MediaTitle + "</b> ?" ;
            self.SelectedDeleteMedia.MediaID = MediaID;
        };

        function SetGridData(data){
            switch (type){
                case 1:
                    //VideoGame
                    return $.extend(new MediaCtr(data, type), { videoGameMedia: data.videoGameMedia,
                        manufacturer: data.manufacturer,
                        publisher: data.publisher,
                        developers: data.developers,
                        platformNames: data.platformNames,
                        platforms: data.platforms
                    });
                    break;
                case 2:
                    //Movie
                    return $.extend(new MediaCtr(data, type), { director: data.director,
                        languages: data.languages,
                        categories: data.categories,
                        movieLanguages: data.movieLanguages,
                        movieCategories: data.movieCategories
                    });
                    break;
                case 3:
                    //Music Disk
                    return $.extend(new MediaCtr(data, type), { musician: data.musician,
                        musicGenresNames: data.musicGenresNames,
                        musicGenres: data.musicGenres
                    });
                    break;
                default:
                    return $.extend(new MediaCtr(data, type), { });
                    break;
            }
        }

        self.SetDateFormat = function(data){
            return moment(data).format("DD/MM/YYYY");
        };

        self.searchData();
    }]);
}
