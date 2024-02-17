
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

window.onload = (event) => {
    let currentAudio = document.getElementById('current-audio');
    currentAudio.src = 'https://gavin-bowers.arcology.builders/audio/test.mp3';
    currentAudio.load();
};
