
var audio = document.getElementById('current-audio');
var musicTracker = document.getElementById('music-tracker');
var paused = true;

function playAudio() {
    if (paused) {
        audio.play();
        paused = false;
    } else {
        audio.pause();
        paused = true;
    }
}

function seekAudio() {
    var seekTo = audio.duration * (seekSlider.value / 100);
    audio.currentTime = seekTo;
}

audio.ontimeupdate = function() {
    var progress = (audio.currentTime / audio.duration) * 100;
    seekSlider.value = progress;
};
