function ProfileCtr(data) {
    var self = this;
    self.userID = data.userID ? data.userID : 0;
    self.username = data.username ? data.username : '';
    self.password = data.password ? data.password : '';
    self.email = data.email ? data.email : '';
    self.firstName = data.firstName ? data.firstName : '';
    self.lastName = data.lastName ? data.lastName : '';
    self.userImageBase64 = data.userImageBase64 ? data.userImageBase64 : SetUserProfileImage();
    return self;
}