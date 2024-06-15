
const audio = document.getElementById('current-audio');
const musicTracker = document.getElementById('music-tracker');
const volumeSlider = document.getElementById('volume-slider');
const playIcon = document.getElementById('play-icon');
const currentAudio = document.getElementById('current-audio');
const currentTitle = document.getElementById('current-title');
const currentArtist = document.getElementById('current-artist');
const playlistTag = document.getElementById('playlist');

const endpoint = "https://gavin-bowers.arcology.builders/"

const TRACKER_SIZE = 5000;

var musiclists;
var playlist = [];
var savedPlaylist = [];
var unshuffledPlaylist = [];
var playlistIndex = 0;

var editMode = false;
var browsingMusic = false;

var repeat = false;
var shuffleOn = false;
var musicStarted = false;

var dragStart;
var dragEnd;

function showMusicCategory(category) {
    if (!browsingMusic) {
        if (shuffleOn) {
            savedPlaylist = unshuffledPlaylist;
        }
        savedPlaylist = playlist.slice();
    }
    shuffleOn = false;
    document.getElementById('shuffle-icon').innerHTML = 'shuffle';

    browsingMusic = true;
    playlist = musiclists[category].slice();
    playlistIndex = 0;
    displayPlaylist();
    playSong();
    audio.pause();
    audio.currentTime = 0;
    musicTracker.value = 0;
    
}

function showMyPlaylist() {
    if (browsingMusic) {
        shuffleOn = false;
        document.getElementById('shuffle-icon').innerHTML = 'shuffle';
        browsingMusic = false;
        playlist = savedPlaylist.slice();
        playlistIndex = 0;
        displayPlaylist();
        playSong();
        audio.pause();
        audio.currentTime = 0;
        musicTracker.value = 0;
    }
}

function toggleEditMode() {
    if (editMode) {
        editMode = false;
    } else {
        editMode = true;
    }
    displayPlaylist();
}

function removeSong(index) {
    playlist.splice(index, 1);
    if (index < playlistIndex) {
        playlistIndex--;
    } else if (index == playlistIndex) {
        playSong();
    }
    displayPlaylist();
}

function addSong(index) {
    savedPlaylist.push(playlist.slice(index, index+1)[0]);
    displayPlaylist();
}

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

function toggleShuffle() {
    if (shuffleOn) {
        shuffleOn = false;
        document.getElementById('shuffle-icon').innerHTML = 'shuffle';
        playlist = [];
        for (let song of unshuffledPlaylist) {
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
}

function shufflePlaylist() {
    unshuffledPlaylist = playlist.slice();

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

function toggleMute() {
    if (audio.muted) {
        document.getElementById("volume-icon").innerHTML = "volume_up";
        audio.muted = false;
    } else {
        document.getElementById("volume-icon").innerHTML = "volume_off";
        audio.muted = true;
    }
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
    setVolume(event.target.value);
});

function setVolume(volume) {
    let volumeFraction = volume / 100;
    let logVolume = Math.pow(volumeFraction, 2);
    audio.volume = logVolume;
}

function seekAudio() {
    var seekTo = audio.duration * (musicTracker.value / TRACKER_SIZE);
    audio.currentTime = seekTo;
    audio.play();
}

audio.ontimeupdate = function() {
    if (isNaN(audio.duration) || audio.duration === 0) { //prevents invalid values from making the range become the default value (50)
        return;
    }
    var progress = (audio.currentTime / audio.duration) * TRACKER_SIZE;
    musicTracker.value = progress;
};

async function getMusicData() {
    const res = await fetch(endpoint + 'musicdata');
    musiclists = await res.json();
}

function displayPlaylist() {
    while (playlistTag.lastChild) {
        playlistTag.removeChild(playlistTag.lastChild);
    }
    let index = 0;
    for (let song of playlist) {
        let playlistElement = document.createElement('li');
        playlistElement.classList.add('playlist-element');
        playlistElement.classList.add('draggable');
        playlistElement.draggable = true;

        let songButton = document.createElement('button');
        songButton.className = "playlist-song"
        let closureIndex = index; //Copies the index value for the following closure
        songButton.onclick = function() {playlistIndex = closureIndex; playSong();};
        songButton.append(song.artist + ' — ' + song.title)

        if (index == playlistIndex) {
            songButton.style.background = "linear-gradient(90deg, #f39d72 0%, #e19d9d 23%, #edd492 100%)";
        } else {
            songButton.style.background = "linear-gradient(90deg, rgba(238,120,62,1) 0%, rgba(214,122,122,1) 23%, rgba(227,189,88,1) 100%)";
        }
        if (editMode) {
            if (browsingMusic) {
                let addButtom = document.createElement('button');
                addButtom.className = "add-button";
                let addIcon = document.createElement('span');
                
                let alreadyAdded = false;
                for (let savedSong of savedPlaylist) {
                    if (song.title === savedSong.title && song.artist === savedSong.artist) {
                        alreadyAdded = true;
                    }
                }
                if (alreadyAdded) {
                    addIcon.innerHTML = 'playlist_add_check';
                    addIcon.style.color = '#808080';
                } else {
                    addIcon.innerHTML = 'playlist_add';
                    addButtom.onclick = function() {
                        addSong(closureIndex);
                    };
                }
                addIcon.className = 'material-symbols-outlined';
                addButtom.append(addIcon);
                playlistElement.append(addButtom);
            } else {
                let removeButtom = document.createElement('button');
                removeButtom.className = "remove-button";
                let removeIcon = document.createElement('span');
                removeIcon.innerHTML = 'playlist_remove';
                removeIcon.className = 'material-symbols-outlined';
                removeButtom.append(removeIcon);
                removeButtom.onclick = function() {removeSong(closureIndex);};
                playlistElement.append(removeButtom);
            }
        }
        playlistElement.append(songButton);

        playlistElement.addEventListener('dragstart', () => {
            dragStart = closureIndex;
            playlistElement.classList.add('dragging');
        });
        playlistElement.addEventListener('dragend', e => {
            playlistElement.classList.remove('dragging');
            let movingSong = playlist.splice(dragStart, 1);
            if (dragEnd > dragStart) { //accounts for offset when song is moved after itself
                playlist.splice(dragEnd - 1, 0, movingSong[0]);
            } else {
                playlist.splice(dragEnd, 0, movingSong[0]);
            }
            if (dragStart < playlistIndex && dragEnd > playlistIndex) {
                playlistIndex--;
            } else if (dragStart > playlistIndex && dragEnd < playlistIndex) {
                playlistIndex++;
            } else if (dragStart == playlistIndex) {
                playlistIndex = dragEnd;
                if (dragEnd > dragStart) {
                    playlistIndex--;
                }
            }
            displayPlaylist();
        });
        playlistTag.append(playlistElement);
        index++;
    }
}



playlistTag.addEventListener('dragover', e => {
    e.preventDefault();
    let rect = e.currentTarget.getBoundingClientRect();
    let offset = e.clientY - rect.top;
    let newIndex = Math.floor(offset / 33 + 0.5);
    dragEnd = newIndex;
    for (child of playlistTag.children) {
        child.style.borderTop = 'none';
        child.style.paddingTop = '1.5px';
        child.style.paddingBottom = '1.5px';
    }
    playlistTag.children[newIndex].style.borderTop = '3px solid #f1f1f1';
    playlistTag.children[newIndex].style.paddingTop = '0px';
    playlistTag.children[newIndex - 1].style.paddingBottom = '0px';
});

async function playSong() {
    let song = playlist[playlistIndex];
    currentAudio.src = endpoint + 'audio/' + song.filename;
    currentArtist.innerHTML = song.artist;
    currentTitle.innerHTML = song.title;

    
    for (let elem of document.getElementsByClassName('playlist-song')) {
        elem.style.background = 
            "linear-gradient(90deg, rgba(238,120,62,1) 0%, rgba(214,122,122,1) 23%, rgba(227,189,88,1) 100%)";
    }
    [...document.getElementsByClassName('playlist-song')][playlistIndex].style.background = 
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
        console.log(res);
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