
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
const currentAudio = document.getElementById('current-audio');
const currentTitle = document.getElementById('current-title');
const currentArtist = document.getElementById('current-artist');
const playlistTag = document.getElementById('playlist');

const endpoint = "https://gavin-bowers.arcology.builders/"

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
        playlistIndex++;
        playSong();
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
    const res = await fetch(endpoint + 'musicdata');
    musiclist = await res.json();
    for (let song of musiclist) {
        playlist.push(song);
    }
    let index = 0;
    for (let song of playlist) {
        let playlistElement = document.createElement('li');
        let songButton = document.createElement('button');
        let closureIndex = index; //Copies the index value for the following closure
        songButton.onclick = function() {playlistIndex = closureIndex; playSong();};
        songButton.innerHTML = song.title;
        playlistElement.append(songButton);
        playlistTag.append(playlistElement);
        index++;
    }
}

async function playSong() {
    let song = playlist[playlistIndex];
    console.log(playlistIndex);
    console.log(song);
    currentAudio.src = endpoint + 'audio/' + song.filename;
    currentArtist.innerHTML = song.artist;
    currentTitle.innerHTML = song.title;
    currentAudio.load();
    await audio.play();
}

//Authentication
async function makePostRequest(user, method) {
    try {
        const res = await fetch(endpoint + 'auth/' + method, {
            'method': 'POST',
            'headers': {
                'Content-Type': 'application/json',
            },
            'body': JSON.stringify(user),
        });
        const result = await res.json();
        console.log(method + ": " + result.ok);
    } catch (error) {
        console.error("Error:", error);
    }
}

async function handleAuthForm(event, method) {
    event.preventDefault()
    let usernameInput;
    let passwordInput;
    switch (method) {
        case 'login':
            usernameInput = document.getElementById("login-username").value;
            passwordInput = document.getElementById("login-password").value;
            break;
        case 'register':
            usernameInput = document.getElementById("register-username").value;
            passwordInput = document.getElementById("register-password").value;
            break;
    }
    const user = {
        username: usernameInput,
        password: passwordInput,
    }
    makePostRequest(user, method);
}

window.onload = (event) => {
    getMusicData();
    audio.volume = Math.pow(0.5, 2);
};