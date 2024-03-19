document.getElementById('contactform').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(this);

    fetch('/submit-contact', {
        method: 'POST',
        body: formData,
    })
    .then(response => response.json())
    .then(data => {
        if(data.success) {
            // Show popup message
            showPopupMessage(data.message);
        }
    })
    .catch(error => console.error('Error:', error));
});

function showPopupMessage(message) {
    const popup = document.createElement('div');
    popup.style.position = 'fixed';
    popup.style.left = '50%';
    popup.style.top = '50%';
    popup.style.transform = 'translate(-50%, -50%)';
    popup.style.backgroundColor = 'white';
    popup.style.padding = '20px';
    popup.style.zIndex = '1000';
    popup.style.border = '1px solid #ccc';
    popup.style.borderRadius = '5px';
    popup.style.boxShadow = '0 4px 6px rgba(0,0,0,0.1)';
    
    const messageP = document.createElement('p');
    messageP.textContent = message;
    popup.appendChild(messageP);
    
    // Countdown timer
    const countdown = document.createElement('p');
    let timeLeft = 10;
    countdown.textContent = `Closing in ${timeLeft} seconds...`;
    popup.appendChild(countdown);

    const intervalId = setInterval(() => {
        timeLeft--;
        countdown.textContent = `Closing in ${timeLeft} seconds...`;
        if (timeLeft <= 0) {
            clearInterval(intervalId);
            document.body.removeChild(popup);
        }
    }, 1000);

    // OK button to close the popup
    const okButton = document.createElement('button');
    okButton.textContent = 'OK';
    okButton.onclick = function() {
        clearInterval(intervalId); // Stop the countdown
        document.body.removeChild(popup);
    };
    popup.appendChild(okButton);

    document.body.appendChild(popup);
}
