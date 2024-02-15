document.addEventListener('DOMContentLoaded', () => {
    const menuButtons = document.querySelectorAll('nav#menu button');
    menuButtons.forEach(button => {
        button.addEventListener('click', function() {
            const target = this.getAttribute('data-target');

            if (target === "quit") {
                console.log("Quit action triggered");

                return;
            }
            updateContent(target);
        });
    });
});

function updateContent(target) {
    const content = document.getElementById('content');
    if (target === "view-all-data") {
        fetch('/data/all')
        .then(response => response.json())
        .then(data => {
            let htmlContent = `<h2>All Data</h2>`;
            data.forEach(row => {
                htmlContent += `<p>${JSON.stringify(row)}</p>`;
            });
            content.innerHTML = htmlContent;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            content.innerHTML = `<p>Error loading data.</p>`;
        });
    } else {
        content.innerHTML = `<h2>${target.replace(/-/g, '')}</h2><p>Feature not implemented yet.</p>`;
    }
}