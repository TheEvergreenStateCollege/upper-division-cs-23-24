// import * as wasm from "hello-wasm-pack";
// wasm.greet("Rust!");

import { Universe, Cell } from "wasm-game-of-life";
import { memory } from "wasm-game-of-life/wasm_game_of_life_bg";

const CELL_SIZE = 5; // px
const GRID_COLOR = "#CCCCCC";
const DEAD_COLOR = "#FFFFFF";
const ALIVE_COLOR = "#000000";

// Construct the universe, and get its width and height.
const universe = Universe.new();
const width = universe.width();
const height = universe.height();

// Give the canvas room for all of our cells and a 1px border
// around each of them.
const canvas = document.getElementById("game-of-life-canvas");
canvas.height = (CELL_SIZE + 1) * height + 1;
canvas.width = (CELL_SIZE + 1) * width + 1;

const ctx = canvas.getContext('2d');

let animationId = null;
let ticksPerFrame = 1; // Default ticks per animation frame
let randomizer = Math.random(); // Used to randomize the animation to an initial state
let previousAnimationId = null;
let randomize = document.getElementById("randomize");
let deadOrAlive = document.getElementById("doa");

const renderLoop = () => {  
  for (let i = 0; i < ticksPerFrame; i++) {
    universe.tick();
  }

  drawGrid();
  drawCells();

  animationId = requestAnimationFrame(renderLoop);

  if (randomizer > 0.5) {
    previousAnimationId = animationId;
  }

  fps.render();
};

// Event listener for the randomize button
// In theory, this should be working
// but the game is rendering too fast it makes the animation looks like it doesn't workking
randomize.addEventListener("click", event => {
  requestAnimationFrame(previousAnimationId);
  drawGrid();
  drawCells();
});

// Event listener for the scroller input
const speedScroller = document.getElementById("scroller");
speedScroller.addEventListener("input", event => {
  ticksPerFrame = parseInt(event.target.value);
});

const isPaused = () => {
  return animationId === null;
};

const playPauseButton = document.getElementById("play-pause");

const play = () => {
  playPauseButton.textContent = "⏸";
  renderLoop();
};

const pause = () => {
  playPauseButton.textContent = "▶";
  cancelAnimationFrame(animationId);
  animationId = null;
};

playPauseButton.addEventListener("click", event => {
  if (isPaused()) {
    play();
  } else {
    pause();
  }
});

// Event listener for the doa button
// I decided to make it toggle between all alive and all dead instead because why not
// but apparently it doesn't work for some reason
deadOrAlive.addEventListener("click", event => {
  if (!isPaused()) {
    pause();
  }

  for (let row = 0; row < height; row++) {
    for (let col = 0; col < width; col++) {
      const idx = getIndex(row, col);

      if (cells[idx] === Cell.Alive) {
        universe.toggle_cell(row, col);
      }
      else if (cells[idx] === Cell.Dead) {
        universe.toggle_cell(row, col);
      }
    }
  }

  drawGrid();
  drawCells();
});

const drawGrid = () => {
    ctx.beginPath();
    ctx.strokeStyle = GRID_COLOR;
  
    // Vertical lines.
    for (let i = 0; i <= width; i++) {
      ctx.moveTo(i * (CELL_SIZE + 1) + 1, 0);
      ctx.lineTo(i * (CELL_SIZE + 1) + 1, (CELL_SIZE + 1) * height + 1);
    }
  
    // Horizontal lines.
    for (let j = 0; j <= height; j++) {
      ctx.moveTo(0,                           j * (CELL_SIZE + 1) + 1);
      ctx.lineTo((CELL_SIZE + 1) * width + 1, j * (CELL_SIZE + 1) + 1);
    }
  
    ctx.stroke();
  };

  const getIndex = (row, column) => {
    return row * width + column;
  };
  
  const drawCells = () => {
    const cellsPtr = universe.cells();
    const cells = new Uint8Array(memory.buffer, cellsPtr, width * height);
  
    ctx.beginPath();
  
    // Legacy code
    // for (let row = 0; row < height; row++) {
    //   for (let col = 0; col < width; col++) {
    //     const idx = getIndex(row, col);
  
    //     ctx.fillStyle = cells[idx] === Cell.Dead
    //       ? DEAD_COLOR
    //       : ALIVE_COLOR;
  
    //     ctx.fillRect(
    //       col * (CELL_SIZE + 1) + 1,
    //       row * (CELL_SIZE + 1) + 1,
    //       CELL_SIZE,
    //       CELL_SIZE
    //     );
    //   }
    // }

    // Alive cells.
    ctx.fillStyle = ALIVE_COLOR;
    for (let row = 0; row < height; row++) {
      for (let col = 0; col < width; col++) {
        const idx = getIndex(row, col);
        if (cells[idx] !== Cell.Alive) {
          continue;
        }

        ctx.fillRect(
          col * (CELL_SIZE + 1) + 1,
          row * (CELL_SIZE + 1) + 1,
          CELL_SIZE,
          CELL_SIZE
        );
      }
    }

    // Dead cells.
    ctx.fillStyle = DEAD_COLOR;
    for (let row = 0; row < height; row++) {
      for (let col = 0; col < width; col++) {
        const idx = getIndex(row, col);
        if (cells[idx] !== Cell.Dead) {
          continue;
        }

        ctx.fillRect(
          col * (CELL_SIZE + 1) + 1,
          row * (CELL_SIZE + 1) + 1,
          CELL_SIZE,
          CELL_SIZE
        );
      }
    }
  
    ctx.stroke();
};

canvas.addEventListener("click", event => {
  const boundingRect = canvas.getBoundingClientRect();

  const scaleX = canvas.width / boundingRect.width;
  const scaleY = canvas.height / boundingRect.height;

  const canvasLeft = (event.clientX - boundingRect.left) * scaleX;
  const canvasTop = (event.clientY - boundingRect.top) * scaleY;

  const row = Math.min(Math.floor(canvasTop / (CELL_SIZE + 1)), height - 1);
  const col = Math.min(Math.floor(canvasLeft / (CELL_SIZE + 1)), width - 1);

  universe.toggle_cell(row, col);

  drawGrid();
  drawCells();
});

/*==============================================================================*/

// Benchmark section
const fps = new class {
  constructor() {
    this.fps = document.getElementById("fps");
    this.frames = [];
    this.lastFrameTimeStamp = performance.now();
  }

  render() {
    // Convert the delta time since the last frame render into a measure
    // of frames per second.
    const now = performance.now();
    const delta = now - this.lastFrameTimeStamp;
    this.lastFrameTimeStamp = now;
    const fps = 1 / delta * 1000;

    // Save only the latest 100 timings.
    this.frames.push(fps);
    if (this.frames.length > 100) {
      this.frames.shift();
    }

    // Find the max, min, and mean of our 100 latest timings.
    let min = Infinity;
    let max = -Infinity;
    let sum = 0;
    for (let i = 0; i < this.frames.length; i++) {
      sum += this.frames[i];
      min = Math.min(this.frames[i], min);
      max = Math.max(this.frames[i], max);
    }
    let mean = sum / this.frames.length;

    // Render the statistics.
    this.fps.textContent = `
Frames per Second:
         latest = ${Math.round(fps)}
avg of last 100 = ${Math.round(mean)}
min of last 100 = ${Math.round(min)}
max of last 100 = ${Math.round(max)}
`.trim();
  }
};

drawGrid();
drawCells();
play();