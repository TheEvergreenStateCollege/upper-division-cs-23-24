document.addEventListener("DOMContentLoaded", function() {
    // Get the query parameter from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query').toLowerCase();

    // Get all product elements
    const products = document.querySelectorAll('.products');

    // Loop through each product
    products.forEach(product => {
        // Get the product title
        const title = product.querySelector('h3').innerText.toLowerCase();

        // Check if the title contains the query
        if (title.includes(query)) {
            // Show the product if it matches the query
            product.style.display = 'block';
        } else {
            // Hide the product if it doesn't match the query
            product.style.display = 'none';
        }
    });
});

// Get the cart icon and the cart number element
const cartIcon = document.querySelector('.cart button');
const cartNum = document.getElementById('cartNum');

// Initialize cart count and cart items array
let cartCount = 0;
const cartItems = [];

// Add event listener to all "Add to Cart" buttons
const addToCartButtons = document.querySelectorAll('.products button');
addToCartButtons.forEach((button, index) => {
    button.addEventListener('click', () => {
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

        // Save cart items to localStorage (optional)
        localStorage.setItem('cartItems', JSON.stringify(cartItems));
    });
});

// Add event listener to cart icon to navigate to cart.html
cartIcon.addEventListener('click', () => {
    // Redirect to cart.html
    window.location.href = 'cart.html';
});

