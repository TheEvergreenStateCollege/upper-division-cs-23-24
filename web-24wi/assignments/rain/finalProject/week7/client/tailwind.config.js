/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors')
module.exports = {
  content: ["./src/**/*.{html,js,jsx,ts,tsx}", "index.html"],
  theme: {
    extend: {
      colors: {
        bargray: '#808080',
        panelgray: '#c0c0c0'

      }
    
      
    },
  },
  plugins: [],
};
