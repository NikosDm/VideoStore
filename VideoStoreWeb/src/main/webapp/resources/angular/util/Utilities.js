function SetMediaDefaultImage(type){
    switch(type){
        case 1:
            //VideoGame
            return '/resources/img/default_video_game.png';
        case 2:
            //Movie
            return '/resources/img/default_movie.jpg';
        case 3:
            //Music Disk
            return '/resources/img/default_music_disk.jpg';
        default:
            return '';
    }
}

function SetUserProfileImage() {
    return '/resources/img/empty_user_img.png';
}

function CheckIfMediaImageIsDefault(mediaImage, type){
    switch(type){
        case 1:
            //VideoGame
            return mediaImage === '/resources/img/default_video_game.png';
        case 2:
            //Movie
            return mediaImage === '/resources/img/default_movie.jpg';
        case 3:
            //Music Disk
            return mediaImage === '/resources/img/default_music_disk.jpg';
        default:
            return '';
    }
}
