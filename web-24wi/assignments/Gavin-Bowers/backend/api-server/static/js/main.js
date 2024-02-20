
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
const currentAudio = document.getElementById('current-audio');
const currentTitle = document.getElementById('current-title');
const currentArtist = document.getElementById('current-artist');
const playlistTag = document.getElementById('playlist');
var paused = true;
var musiclist = [];
var playlist = [];
var playlistIndex = 0;

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

audio.onended = (event) => {
    if (playlistIndex < playlist.length - 1) {
        playSong(playlist[playlistIndex + 1] ,playlistIndex + 1);
    }
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
    musiclist = await res.json();
    let index = 0;
    for (let song of musiclist) {
        let playlistElement = document.createElement('li');
        let songButton = document.createElement('button');
        songButton.onclick = function() {playSong(song, index);};
        songButton.innerHTML = song.title;
        playlistElement.append(songButton);
        playlistTag.append(playlistElement);
        index += 1;
    }
}

async function playSong(song, index) {
    playlistIndex = index;
    currentAudio.src = '/audio/' + song.filename;
    currentArtist.innerHTML = song.artist;
    currentTitle.innerHTML = song.title;
    currentAudio.load();
    await audio.play();
}

window.onload = (event) => {
    getMusicData();
};