<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "employee101";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$username1 ="raghav";
$name = "a";
$amountf =100;
$amountt = 200;
$mysql_qry2 = "update employee_data set amount='$amountt' where username like '$name';";
$mysql_qry3 = "update employee_data set amount='$amountf' where username like '$username1';";
$result2 = mysqli_query($conn,$mysql_qry2);
$result3 = mysqli_query($conn,$mysql_qry3);
?>