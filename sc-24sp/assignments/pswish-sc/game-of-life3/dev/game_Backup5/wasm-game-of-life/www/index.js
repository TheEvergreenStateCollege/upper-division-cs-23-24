import { Universe, Cell } from "wasm-game-of-life";
import { memory } from "wasm-game-of-life/wasm_game_of_life_bg"

const CELL_SIZE = 5;
const GRID_COLOR = "#CCCCCC";
const DEAD_COLOR = "#FFFFFF";
const ALIVE_COLOR = "#000000";

const universe = Universe.new();
const width = universe.width()
const height = universe.height();

const canvas = document.getElementById("game-of-life-canvas");
canvas.height = (CELL_SIZE + 1) * height + 1;
canvas.width = (CELL_SIZE + 1) * width + 1;

const ctx = canvas.getContext("2d");

let animationID = null;

const renderLoop = () => {
  // debugger;
  fps.render(); // currently broken

  drawGrid();
  drawCells();
  
  universe.tick();

  animationID = requestAnimationFrame(renderLoop);
};

const isPaused = () => {
  return animationID === null;
}

const playPauseButton = document.getElementById("play-pause");

const play = () => {
  playPauseButton.textContent = "⏸";
  renderLoop();
};

const pause = () => {
  playPauseButton.textContent = "▶";
  cancelAnimationFrame(animationID);
  animationID = null;
};

playPauseButton.addEventListener("click", event => {
  if (isPaused()) {
    play();
  } else {
    pause();
  }
});


const drawGrid = () => {
  ctx.beginPath();
  ctx.strokeStyle = GRID_COLOR;


  // Vertical grid lines
  for (let i = 0; i < width; i++) {
    ctx.moveTo((CELL_SIZE + 1) * i + 1, 0);
    ctx.lineTo((CELL_SIZE + 1) * i + 1, (CELL_SIZE + 1) * height + 1);
  }

  // Horizontal gird lines
  for (let j = 0; j < height; j++) {
    ctx.moveTo(0, j * (CELL_SIZE + 1) + 1);
    ctx.lineTo((CELL_SIZE + 1) * width + 1, j * (CELL_SIZE + 1) + 1);
  }

  ctx.stroke();
};

const getIndex = (row, column) => {
  return row * width + column;
}


const drawCells = () => {
  const cellsPtr = universe.cells();
  const cells = new Uint8Array(memory.buffer, cellsPtr, width * height);

  ctx.beginPath();

  for (let row = 0; row < height; row++) {
    for (let col = 0; col < width; col++) {
      const idx = getIndex(row, col);

      ctx.fillStyle = cells[idx] === Cell.Dead
      ? DEAD_COLOR
      : ALIVE_COLOR;

      ctx.fillRect(
        col * (CELL_SIZE + 1) + 1,
        row * (CELL_SIZE + 1) + 1,
        CELL_SIZE,
        CELL_SIZE
      );
    }
  }
  ctx.stroke();
}

// new code block for flipping cell state onClick
canvas.addEventListener('click', event => {
  const boundingRect = canvas.getBoundingClientRect();
  
  const scaleX = canvas.width / boundingRect.width;
  const scaleY = canvas.height / boundingRect.height;

  const canvasLeft = (event.clientX - boundingRect.left) * scaleX;
  const canvasTop = (event.clientY - boundingRect.top) * scaleY;

  const row = Math.floor(canvasTop / (CELL_SIZE + 1), height -1);
  const col = Math.floor(canvasLeft / (CELL_SIZE + 1), width -1);

  universe.toggle_cell(row, col);

  drawGrid();
  drawCells();

});

const fps = new class {
  constructor() {
    this.fps = document.getElementById("fps");
    this.frames = [];
    this.lastFrameTimeStamp = performance.now();
}

render() {
  const now = performance.now();
  const delta = now - this.lastFrameTimeStamp;
  this.lastFrameTimeStamp = now;
  const fps = 1 / delta * 1000;

  this.frames.push(fps);
  if (this.frames.length > 100) {
    this.frames.shift();
  }

  let min = Infinity;
  let max = -Infinity;
  let sum = 0;
  for (let i = 0; i < this.frames.length; i++) {
    sum += this.frames[i];
    min = Math.min(this.frames[i], min);
    max = Math.max(this.frames[i], max);
  }
  let mean = sum / this.frames.length;

  this.fps.textContent = `
  Frames per second (FPS):
    latest = ${Math.round(fps)}
avg of last 100 = ${Math.round(mean)}
min of last 100 = ${Math.round(min)}
max of last 100 = ${Math.round(max)}
`.trim();
  }
};

// Starts the rendering, loop 0
drawGrid();
drawCells();

//requestAnimationFrame(renderLoop);
play();