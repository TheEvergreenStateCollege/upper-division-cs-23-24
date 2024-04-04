// Get the cart icon and the cart number element
const cartIcon = document.querySelector('.cart button');
const cartNum = document.getElementById('cartNum');

// Initialize cart count and cart items array
let cartCount = 0;
const cartItems = [];

const isLoggedIn = localStorage.getItem('isLoggedIn');
            
// Convert the value to a boolean
const isLoggedInBool = isLoggedIn === 'true';

// Add event listener to all "Add to Cart" buttons
const addToCartButtons = document.querySelectorAll('.products button');
addToCartButtons.forEach((button, index) => {
    button.addEventListener('click', async (event) => {
        // Check if the user is already logged in, if false, alert the user and ask if they want to log in
        if (!isLoggedInBool) {
            // Pass the condition directly to confirm() function
            if (confirm('You are not logged in. Redirecting to logIn.html')) {
                window.location.href = 'logIn.html';
            }
            else {
                window.location.href = 'index.html';
            }
        } else {

            // Retrieve the bearer token from local storage
            const token = localStorage.getItem('token');

            // Continue with the wishlist functionality
            // Increment cart count
            cartCount++;

            // Update cart number
            cartNum.textContent = cartCount;

            // Add product to cart
            const product = {
                name: button.parentElement.querySelector('h3').textContent,
                description: button.parentElement.querySelector('p').textContent,
                image: button.parentElement.querySelector('img').getAttribute('src')
            };
            cartItems.push(product);

            // Get the parent element (div) of the button
            var uuidDiv = event.target.closest('[data-uuid]');

            // Check if the parent element exists
            if (uuidDiv) {
                // Get the UUID from the data-uuid attribute of the parent element
                var uuid = uuidDiv.getAttribute('data-uuid');

                // Add product to cart database version
                const response = await fetch('/api/cart', {
                    method: 'post',
                    headers: {
                        'Accept': 'application/json',
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${token}`
                    },
                    body: JSON.stringify({"name": button.parentElement.querySelector('h3').textContent, "body": button.parentElement.querySelector('p').textContent, "productId": uuid}),
                });
                if (response.status === 401) {
                    const { message } = await response.json();
                    alert(message);
                    return;
                }
            } else {
                // Handle the case when the parent element is not found
                console.error('Parent element not found');
            }

            // Save cart items to localStorage (optional)
            localStorage.setItem('cartItems', JSON.stringify(cartItems));
            
        }
    });
});

// Add event listener to cart icon to navigate to cart.html
cartIcon.addEventListener('click', () => {
    if (cartItems.length != 0) {
        // Update cart number
        cartCount = cartItems.length;
        cartNum.textContent = cartCount;
    }
    // Redirect to cart.html
    window.location.href = 'cart.html';
});

