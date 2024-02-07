/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: "all",
    content: [
        // Include all Rust, HTML and CSS files in the src directory
        "./src/**/*.{rs,html,css}",
        // Include all HTML files in the output (dist) directory
        "./dist/**/*.html",
    ],
    theme: {
        extend: {},
    },
    plugins: [],
}

