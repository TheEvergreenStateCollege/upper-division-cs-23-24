var tabLinks = document.getElementById("tab-links");

function openTab(tabname) {
    var tabLinks = document.querySelectorAll(".tab-links");
    var tabContents = document.querySelectorAll(".tab-contents");

    tabLinks.forEach(function(link) {
        link.classList.remove('active-link');
    });

    tabContents.forEach(function(content) {
        content.classList.remove('active-tab');
    });

    event.currentTarget.classList.add('active-link');
    document.getElementById(tabname).classList.add("active-tab");
}

var sidebarMenu = document.getElementById("sidebar-menu");

function openMenu() {
    sidebarMenu.style.right = "0";
}
function closeMenu() {
    sidebarMenu.style.right = "-200px";
}

const scriptURL = 'https://script.google.com/macros/s/AKfycbwmglj7kuKYNiA_TrnQ028FhVuSSxxbvVBb-wfXiQMNsz0T5KIJeBs28u-QMEG7fVB_/exec'
const form = document.forms['submit-to-google-sheet']
const mgs = document.getElementById("msg")

form.addEventListener('submit', e => {
    e.preventDefault()
    fetch(scriptURL, { method: 'POST', body: new FormData(form)})
        .then(response => {
            msg.innerHTML = "Message sent successfully!"
            setTimeout(function() {
                msg.innerHTML = ""                
            }, 5000)
            form.reset() 
        })
        .catch(error => console.error('Error!', error.message))
    })