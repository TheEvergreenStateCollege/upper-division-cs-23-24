
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
const currentAudio = document.getElementById('current-audio');
var paused = true;
var musicList = [];

function playAudio() {
    if (paused) {
        audio.play();
    } else {
        audio.pause();
    }
}

audio.onplay = (event) => {
    paused = false;
    playIcon.innerHTML = 'pause';
}

audio.onpause = (event) => {
    paused = true;
    playIcon.innerHTML = 'play_arrow';
}

volumeSlider.addEventListener('input', (event) => {
    const value = event.target.value;
    let volumeFraction = value / 100;
    let logVolume = Math.pow(volumeFraction, 2);
    audio.volume = logVolume;
});

function seekAudio() {
    var seekTo = audio.duration * (musicTracker.value / 100);
    audio.currentTime = seekTo;
    audio.play();
}

audio.ontimeupdate = function() {
    if (isNaN(audio.duration) || audio.duration === 0) { //prevents invalid values from making the range become the default value (50)
        return;
    }
    var progress = (audio.currentTime / audio.duration) * 100;
    musicTracker.value = progress;
};

async function getMusicData() {
    const res = await fetch('/musicdata');
    musicList = await res.json();
    const playlist = document.getElementById('playlist');
    for (let song of musicList) {
        let playlistElement = document.createElement('li');
        let songButton = document.createElement('button');
        songButton.onclick = function() {playSong(song.filename); };
        songButton.innerHTML = song.title;
        playlistElement.append(songButton);
        playlist.append(playlistElement);
    }
}

function playSong(filename) {
    audio.pause();
    currentAudio.src = '/audio/' + filename;
    currentAudio.load();
}

window.onload = (event) => {
    getMusicData();
};