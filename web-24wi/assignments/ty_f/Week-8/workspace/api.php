<?php
header("Content-Type: application/json");

$data = array(
    "message" => "Hello, this is your API endpoint!",
    "timestamp" => date('Y-m-d H:i:s')
);

echo json_encode($data);
?>
<?php
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Handle POST request for signup
    $username = $_POST['username'];
    $email = $_POST['email'];
    
    // You can perform validation, database operations, etc. here
    // For now, just return the received data
    $data = array(
        "message" => "Signup successful!",
        "username" => $username,
        "email" => $email,
        "timestamp" => date('Y-m-d H:i:s')
    );
} else if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    // Handle GET request for signup
    $data = array(
        "message" => "This is the signup page.",
        "timestamp" => date('Y-m-d H:i:s')
    );
} else {
    // Handle other request methods
    http_response_code(405); // Method Not Allowed
    $data = array("error" => "Method Not Allowed");
}

echo json_encode($data);
?>

