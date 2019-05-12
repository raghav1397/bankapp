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
$username1 = $_POST["username1"];
$name = $_POST["name"];
$amountf = $_POST["amountf"];
$amountt = $_POST["amountt"];
$mysql_qry2 = "update employee_data set amount='$amountf' where username like '$username1';";
$mysql_qry3 = "update employee_data set amount='$amountt' where username like '$name';";
$result2 = mysqli_query($conn,$mysql_qry2);
$result3 = mysqli_query($conn,$mysql_qry3);
$mysql_qry = "select * from employee_data where username like '$username1';";
$result = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result) > 0) {

$response = array();

while($row = mysqli_fetch_array($result))
{
    array_push($response,array("name" => $row[1],"surname" => $row[2],"age" => $row[3],"username" => $row[4],"password" => $row[5],"amount" => $row[6]));

}

echo json_encode(array("server_response"=>$response));

}
else {
echo "Invalid To Account";
}


?>