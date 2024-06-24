document.addEventListener('DOMContentLoaded', function() {
    console.log('JavaScript is loaded and running');
    var toggleButton = document.getElementById('darkModeToggle');
    toggleButton.addEventListener('click', function() {
        console.log('Button clicked');
        document.body.classList.toggle('dark-mode');
    });
});