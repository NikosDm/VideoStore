function SearchMediaCtr(data) {
    //debugger;
    var self = this;
    self.mediaID = data.mediaID ? data.mediaID : 0;
    self.mediaCode = data.mediaCode ? data.mediaCode : '';
    self.mediaTitle = data.mediaTitle ? data.mediaTitle : '';
    self.mediaDescription = data.mediaDescription ? data.mediaDescription : '';
    self.releaseDate = data.releaseDate ? new Date(data.releaseDate) : '';
    self.price = data.price ? data.price : 0.0;
    self.mediaType = data.mediaType ? data.mediaType : 0;
    self.mediaImageBase64 = data.mediaImageBase64 ? data.mediaImageBase64 : SetMediaDefaultImage(self.mediaType);
    return self;
}
