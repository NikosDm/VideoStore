function PlatformCtr(data) {
	var self = this;
	self.platformID = data.platformID ? data.platformID : 0;
	self.platformDescription = data.platformDescription ? data.platformDescription : '';
	self.developers = data.developers ? data.developers : '';
	self.IsDescriptionValid = self.platformDescription != '';
	self.IsDevelopersValid = self.developers != '';
	self.IsValid = self.IsDescriptionValid && self.IsDevelopersValid;
    self.updateStatus = function() {
        self.IsDescriptionValid = self.platformDescription != '' && self.platformDescription != undefined;
        self.IsDevelopersValid = self.developers != '' && self.developers != undefined;
        self.IsValid = self.IsDescriptionValid && self.IsDevelopersValid;
    };
	return self;
}

function MovieCategoryCtr(data) {
	var self = this;
	self.movieCategoryID = data.movieCategoryID ? data.movieCategoryID : 0;
	self.movieCategoryName = data.movieCategoryName ? data.movieCategoryName : '';
	self.IsValid = self.movieCategoryName != '';
    self.updateStatus = function() {
        self.IsValid = self.movieCategoryName != '' && self.movieCategoryName != undefined;
    };
	return self;
}

function MovieLanguageCtr(data) {
	var self = this;
	self.movieLanguageID = data.movieLanguageID ? data.movieLanguageID : 0;
	self.movieLanguageName = data.movieLanguageName ? data.movieLanguageName : '';
	self.IsValid = self.movieLanguageName != '';
    self.updateStatus = function() {
        self.IsValid = self.movieLanguageName != '' && self.movieLanguageName != undefined;
    };
	return self;
}

function MusicGenreCtr(data) {
	var self = this;
	self.genreID = data.genreID ? data.genreID : 0;
	self.genreDescription = data.genreDescription ? data.genreDescription : '';
	self.IsValid = self.genreDescription != '';
	self.updateStatus = function() {
		self.IsValid = self.genreDescription != '' && self.genreDescription != undefined;
	};
	return self;
}

function StoreUserRoleCtr(data) {
	var self = this;
	self.roleID = data.roleID ? data.roleID : 0;
	self.roleName = data.roleName ? data.roleName : '';
	self.IsValid = self.roleName != '';
    self.updateStatus = function() {
        self.IsValid = self.roleName != '' && self.roleName != undefined;
    };
	return self;
}