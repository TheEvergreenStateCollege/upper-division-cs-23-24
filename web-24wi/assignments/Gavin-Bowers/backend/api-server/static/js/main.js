
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
const currentAudio = document.getElementById('current-audio');
const currentTitle = document.getElementById('current-title');
const currentArtist = document.getElementById('current-artist');
const playlistTag = document.getElementById('playlist');

const endpoint = "https://gavin-bowers.arcology.builders/"

var musiclist = [];
var playlist = [];
var playlistIndex = 0;
var repeat = false;
var shuffleOn = false;
var musicStarted = false;

function playAudio() {
    if (audio.paused) {
        if (!musicStarted) {
            playSong();
            musicStarted = true;
        }
        audio.play();
    } else {
        audio.pause();
    }
}

function previousSong() {
    if (playlistIndex > 0) {
        playlistIndex--;
        playSong();
    }
}

function nextSong() {
    if (repeat) {
        playSong();
    } else if (playlistIndex < playlist.length - 1) {
        playlistIndex++;
        playSong();
    }
}

function toggleRepeat() {
    if (repeat) {
        repeat = false;
        document.getElementById('loop-icon').innerHTML = 'repeat';
    } else {
        repeat = true;
        document.getElementById('loop-icon').innerHTML = 'repeat_on';
    }
}

function shuffle() {
    if (shuffleOn) {
        shuffleOn = false;
        document.getElementById('shuffle-icon').innerHTML = 'shuffle';
        playlist = [];
        for (let song of musiclist) {
            playlist.push(song);
        }
        playlistIndex = 0;
        let index = 0;
        for (let song of playlist) {
            if (currentTitle.textContent ===  song.title && 
                currentArtist.textContent === song.artist) {
                playlistIndex = index;
            }
            index++;
        }
    } else {
        shuffleOn = true;
        document.getElementById('shuffle-icon').innerHTML = 'shuffle_on';
        shufflePlaylist();
    }
    displayPlaylist();
    playlistTag.children[playlistIndex].children[0].style.background = 
    "linear-gradient(90deg, #f39d72 0%, #e19d9d 23%, #edd492 100%)";
}

function shufflePlaylist() {
    for (let i = playlist.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * i + 1);
        [playlist[i], playlist[j]] = [playlist[j], playlist[i]];
    }
    let index = 0;
        for (let song of playlist) {
            if (currentTitle.textContent ===  song.title && 
                currentArtist.textContent === song.artist) {
                playlistIndex = index;
            }
            index++;
        }
    [playlist[playlistIndex], playlist[0]] = [playlist[0], playlist[playlistIndex]];
    playlistIndex = 0;
}

audio.onplay = (event) => {
    playIcon.innerHTML = 'pause';
}

audio.onpause = (event) => {
    playIcon.innerHTML = 'play_arrow';
}

audio.onended = (event) => {
    nextSong();
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
    displayPlaylist();
}

function displayPlaylist() {
    while (playlistTag.lastChild) {
        playlistTag.removeChild(playlistTag.lastChild);
    }
    let index = 0;
    for (let song of playlist) {
        let playlistElement = document.createElement('li');
        let songButton = document.createElement('button');
        songButton.className = "playlist-song"
        let closureIndex = index; //Copies the index value for the following closure
        songButton.onclick = function() {playlistIndex = closureIndex; playSong();};
        songButton.innerHTML = song.artist + ' — ' + song.title;
        playlistElement.append(songButton);
        playlistTag.append(playlistElement);
        index++;
    }
}

async function playSong() {
    let song = playlist[playlistIndex];
    currentAudio.src = endpoint + 'audio/' + song.filename;
    currentArtist.innerHTML = song.artist;
    currentTitle.innerHTML = song.title;

    for (let child of playlistTag.children) {
        child.children[0].style.background = 
            "linear-gradient(90deg, rgba(238,120,62,1) 0%, rgba(214,122,122,1) 23%, rgba(227,189,88,1) 100%)";
    }
    playlistTag.children[playlistIndex].children[0].style.background = 
        "linear-gradient(90deg, #f39d72 0%, #e19d9d 23%, #edd492 100%)";
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
        console.log(method + ": " + result.ok + ": " + result.message);
    } catch (error) {
        console.error("Error:", error);
    }
}

 //This handles loggin in and registering by getting from the correct inputs
async function handleAuthForm(event, method) {
    event.preventDefault()
    const user = {
        username: document.getElementById(method + "-username").value,
        password: document.getElementById(method + "-password").value,
    }
    makePostRequest(user, method);
}

window.onload = (event) => {
    getMusicData();
    audio.volume = Math.pow(0.5, 2);
};