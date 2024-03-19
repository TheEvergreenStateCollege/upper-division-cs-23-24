<?php
header("Content-Type: application/json");

$data = array(
    "message" => "Hello, this is your API endpoint!",
    "timestamp" => date('Y-m-d H:i:s')
);

echo json_encode($data);
?>

