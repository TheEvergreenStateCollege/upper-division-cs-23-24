document.addEventListener('DOMContentLoaded',() => {

const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const loginButton = document.querySelector('.Login-Button');
const iconClose = document.querySelector('.icon-close');

registerLink.addEventListener('click' , () => {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click' , () => {
    wrapper.classList.remove('active');
});

loginButton.addEventListener('click' , () => {
    wrapper.classList.add('active-popup');
});

iconClose.addEventListener('click' , () => {
    wrapper.classList.remove('active-popup');
});


    const togglePasswordButton = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('login-password');

    togglePasswordButton.addEventListener('click',() =>{
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
    
        const icon = togglePasswordButton.querySelector('ion-icon');
        if(type === 'password') {
            icon.setAttribute('name', 'eye');
        } else {
            icon.setAttribute('name', 'eye-off');
        }
    });
})