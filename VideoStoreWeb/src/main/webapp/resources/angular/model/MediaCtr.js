function MediaCtr(data, type){
    var self = this;
    debugger;
    self.mediaID = data.mediaID ? data.mediaID : 0;
    self.mediaCode = data.mediaCode ? data.mediaCode : '';
    self.mediaTitle = data.mediaTitle ? data.mediaTitle : '';
    self.mediaDescription = data.mediaDescription ? data.mediaDescription : '';
    self.releaseDate = data.releaseDate ? new Date(data.releaseDate) : '';
    self.mediaImageBase64 = data.mediaImageBase64 ? data.mediaImageBase64 : SetMediaDefaultImage(type);
    self.price = data.price ? data.price : 0.0;
    return self;
}