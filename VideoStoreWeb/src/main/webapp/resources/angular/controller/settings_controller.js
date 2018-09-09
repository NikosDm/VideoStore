/*DSADADS*/

function SettingsController(module, controllerName, serviceName) {
    module.controller(controllerName, ['$scope', '$timeout', serviceName, function ($scope, $timeout, Service) {

        var self = $scope;

		self.tab = 1;

		self.setTab = function(newTab){
			self.tab = newTab;
		};

		self.isSet = function(tabNum){
			return self.tab === tabNum;
		};

        self.Settings = {
			platforms: [],
			movieCategories: [],
			movieLanguages: [],
			musicGenres: [],
			userRoles: []
		};

        self.SelectedSettingDelete = {
            SettingID: 0,
            SettingDescription: ''
        };
		
        self.InitializeDeleteModal = function (SettingID, SettingDescription){
            self.SelectedSettingDelete.SettingDescription = "Are you sure you want to delete: <b>" + SettingDescription + "</b> ?" ;
            self.SelectedSettingDelete.SettingID = SettingID;
        };

        self.SettingsLoader = true;
		self.SaveValidation = false;

        self.loadSettings = function () {
			Service.fetchSettings()
				.then(
					function (response) {
						self.Settings.platforms = $.map(response.data.platforms, function(platform) {
							return new PlatformCtr(platform);
						});
						self.Settings.movieCategories = $.map(response.data.movieCategories, function(movieCategory) {
							return new MovieCategoryCtr(movieCategory);
						});
						self.Settings.movieLanguages = $.map(response.data.movieLanguages, function(movieLanguage) {
							return new MovieLanguageCtr(movieLanguage);
						});
						self.Settings.musicGenres = $.map(response.data.musicGenres, function(musicGenre) {
							return new MusicGenreCtr(musicGenre);
						});
						self.Settings.userRoles = $.map(response.data.userRoles, function(userRole) {
							return new StoreUserRoleCtr(userRole);
						});
						self.SettingsLoader = false;
					},
					function (errResponse) {
						self.SettingsLoader = false;
					}
				);
        };
		
		/* -- UPDATE SELECTED -- */
		
        self.updateSelectedPlatform = function (model) {
			self.SaveValidation = true;
			model.updateStatus();
			if (model.IsValid){
				$timeout(function() {
					Service.updateSettingData(model, '/api/PlatformApi/updatePlatform')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.platforms.forEach(function (platform) {
								if (platform.platformID == responseData.platformID) {
									platform.platformID = responseData.platformID;
									platform.platformDescription = responseData.platformDescription;
									platform.developers = responseData.developers;
								}
							});
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateSelectedMovieCategory = function (model) {
			self.SaveValidation = true;
			model.updateStatus();
            if (model.IsValid){
				$timeout(function() {
					Service.updateSettingData(model, '/api/MovieCategoryApi/updateMovieCategory')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.movieCategories.forEach(function (category) {
								if (category.movieCategoryID == responseData.movieCategoryID) {
									category.movieCategoryID = responseData.movieCategoryID;
									category.movieCategoryName = responseData.movieCategoryName;
								}
							});
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateSelectedMovieLanguage = function (model) {
			self.SaveValidation = true;
			model.updateStatus();
            if (model.IsValid){
				$timeout(function() {
					Service.updateSettingData(model, '/api/MovieLanguageApi/updateMovieLanguage')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.movieLanguages.forEach(function (language) {
								if (language.movieLanguageID == responseData.movieLanguageID) {
									language.movieLanguageID = responseData.movieLanguageID;
									language.movieLanguageName = responseData.movieLanguageName;
								}
							});
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateSelectedMusicGenre = function (model) {
			self.SaveValidation = true;
			model.updateStatus();
            if (model.IsValid){
				$timeout(function() {
					Service.updateSettingData(model, '/api/MusicGenreApi/updateMusicGenre')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.musicGenres.forEach(function (genre) {
								if (genre.genreID == responseData.genreID) {
									genre.genreID = responseData.genreID;
									genre.genreDescription = responseData.genreDescription;
								}
							});
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateSelectedUserRole = function (model) {
			self.SaveValidation = true;
			model.updateStatus();
            if (model.IsValid){
				$timeout(function() {
					Service.updateSettingData(model, '/api/ProfileApi/updateStoreUserRole')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.userRoles.forEach(function (userRole) {
								if (userRole.roleID == responseData.roleID) {
									userRole.roleID = responseData.roleID;
									userRole.roleName = responseData.roleName;
								}
							});
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
		/* -- / UPDATE SELECTED -- */
		
		/* -- UPDATE LIST -- */
		
		self.updatePlatformList = function (model) {
			self.SaveValidation = true;
			if (!IsListValid(model)){
				$timeout(function() {
					Service.updateSettingDataList(model, '/api/PlatformApi/updatePlatformList')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.platforms = $.map(responseData, function(platform) {
                                return new PlatformCtr(platform);
                            });
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateMovieCategoryList = function (model) {
			self.SaveValidation = true;
            if (!IsListValid(model)){
				$timeout(function() {
					Service.updateSettingDataList(model, '/api/MovieCategoryApi/updateMovieCategoryList')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.movieCategories = $.map(responseData, function(movieCategory) {
                                return new MovieCategoryCtr(movieCategory);
                            });
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateMovieLanguageList = function (model) {
			self.SaveValidation = true;
            if (!IsListValid(model)){
				$timeout(function() {
					Service.updateSettingDataList(model, '/api/MovieLanguageApi/updateMovieLanguageList')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.movieLanguages = $.map(responseData, function(movieLanguage) {
                                return new MovieLanguageCtr(movieLanguage);
                            });
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateMusicGenreList = function (model) {
			self.SaveValidation = true;
            if (!IsListValid(model)){
				$timeout(function() {
					Service.updateSettingDataList(model, '/api/MusicGenreApi/updateMusicGenreList')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.musicGenres = $.map(responseData, function(musicGenre) {
                                return new MusicGenreCtr(musicGenre);
                            });
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
        self.updateUserRoleList = function (model) {
			self.SaveValidation.platform = true;
            if (!IsListValid(model)){
				$timeout(function() {
					Service.updateSettingDataList(model, '/api/ProfileApi/updateStoreUserRoleList')
						.then(
						function (response) {
							var responseData = response.data;
							self.Settings.userRoles = $.map(responseData, function(userRole) {
                                return new StoreUserRoleCtr(userRole);
                            });
							self.SaveValidation = false;
							$timeout(function(){
								toastr.success('Save success');
							});
						},
						function (errResponse) {
							$timeout(function(){
								toastr.error('Save Failed');
							});
						}
					);
				});
			}
        };
		
		/* -- / UPDATE LIST -- */
		
		/* -- DELETE -- */
		
        self.deletePlatform = function (id) {
            Service.deleteSettingData(id, '/api/PlatformApi/deletePlatform')
                .then(
                function (response) {
                    for (var i = 0; i < self.Settings.platforms.length; i++) {
                        if (self.Settings.platforms[i].platformID == response.data) {
                            self.Settings.platforms.splice(i, 1);
                            self.SelectedSettingDelete.SettingDescription = '';
							self.SelectedSettingDelete.SettingID = 0;
							$timeout(function(){
								toastr.success('Delete success');
							});
                            break;
                        }
                    }
                },
                function (errResponse) {
					$timeout(function(){
						toastr.error('Delete Failed');
					});
                }
            );
        };
		
        self.deleteMovieCategory = function (id) {
            Service.deleteSettingData(id, '/api/MovieCategoryApi/deleteMovieCategory')
                .then(
                function (response) {
                    for (var i = 0; i < self.Settings.movieCategories.length; i++) {
                        if (self.Settings.movieCategories[i].movieCategoryID == response.data) {
                            self.Settings.movieCategories.splice(i, 1);
                            self.SelectedSettingDelete.SettingDescription = '';
							self.SelectedSettingDelete.SettingID = 0;
							$timeout(function(){
								toastr.success('Delete success');
							});
                            break;
                        }
                    }
                },
                function (errResponse) {
					$timeout(function(){
						toastr.error('Delete Failed');
					});

                }
            );
        };
		
        self.deleteMovieLanguage = function (id) {
            Service.deleteSettingData(id, '/api/MovieLanguageApi/deleteMovieLanguage')
                .then(
                function (response) {
                    for (var i = 0; i < self.Settings.movieLanguages.length; i++) {
                        if (self.Settings.movieLanguages[i].movieLanguageID == response.data) {
                            self.Settings.movieLanguages.splice(i, 1);
                            self.SelectedSettingDelete.SettingDescription = '';
							self.SelectedSettingDelete.SettingID = 0;
							$timeout(function(){
								toastr.success('Delete success');
							});
                            break;
                        }
                    }
                },
                function (errResponse) {
					$timeout(function(){
						toastr.error('Delete Failed');
					});
                }
            );
        };
		
        self.deleteMusicGenre = function (id) {
            Service.deleteSettingData(id, '/api/MusicGenreApi/deleteMusicGenre')
                .then(
                function (response) {
                    for (var i = 0; i < self.Settings.musicGenres.length; i++) {
                        if (self.Settings.musicGenres[i].genreID == response.data) {
                            self.Settings.musicGenres.splice(i, 1);
                            self.SelectedSettingDelete.SettingDescription = '';
							self.SelectedSettingDelete.SettingID = 0;
							$timeout(function(){
								toastr.success('Delete success');
							});
                            break;
                        }
                    }
                },
                function (errResponse) {
					$timeout(function(){
						toastr.error('Delete Failed');
					});
                }
            );
        };
		
        self.deleteStoreUserRole = function (id) {
            Service.deleteSettingData(id, '/api/ProfileApi/deleteStoreUserRole')
                .then(
                function (response) {
                    for (var i = 0; i < self.Settings.userRoles.length; i++) {
                        if (self.Settings.userRoles[i].roleID == response.data) {
                            self.Settings.userRoles.splice(i, 1);
                            self.SelectedSettingDelete.SettingDescription = '';
							self.SelectedSettingDelete.SettingID = 0;
							$timeout(function(){
								toastr.success('Delete success');
							});
                            break;
                        }
                    }
                },
                function (errResponse) {
					$timeout(function(){
						toastr.error('Delete Failed');
					});
                }
            );
        };
		
		/* -- / DELETE -- */
		
		/* -- ADD -- */
		
		self.addPlatform = function() {
			self.Settings.platforms.push(new PlatformCtr({}));
		};
		
		self.addMovieCategory = function() {
			self.Settings.movieCategories.push(new MovieCategoryCtr({}));
		};
		
		self.addMovieLanguage = function() {
			self.Settings.movieLanguages.push(new MovieLanguageCtr({}));
		};
		
		self.addMusicGenre = function() {
			self.Settings.musicGenres.push(new MusicGenreCtr({}));
		};
		
		self.addUserRole = function() {
			self.Settings.userRoles.push(new StoreUserRoleCtr({}));
		};
		
		/* --/ ADD -- */
		
		function IsListValid(model) {
			for (var i = 0; i < model.length; i++) {
				model[i].updateStatus();
				if (!model[i].IsValid)
					return false;
			}
			return true;
		}
		
        self.loadSettings();
    }]);
}
