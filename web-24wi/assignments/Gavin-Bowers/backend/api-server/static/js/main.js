
var audio = document.getElementById('current-audio');
var musicTracker = document.getElementById('music-tracker');
var paused = true;

function playAudio() {
    audio.play();
}

function seekAudio() {
    var seekTo = audio.duration * (musicTracker.value / 100);
    audio.currentTime = seekTo;
}

audio.ontimeupdate = function() {
    var progress = (audio.currentTime / audio.duration) * 100;
    musicTracker.value = progress;
};

//The onloadedmetadata function actually refers to default metadata like duration, but it's a good time to handle custom metadata too
audio.onloadedmetadata = function() { 
    
};

fetch('/audio/test.mp3').then(response => {
    const title = response.headers.get('X-Audio-Title');
    const artist = response.headers.get('X-Audio-Artist');

    document.getElementById('current-title').innerHTML = title;
    document.getElementById('current-artist').innerHTML = artist;
});

document.getElementById('audio-source').src = '/audio/test.mp3';
