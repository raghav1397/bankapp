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
$username = $_POST["username"];
$password = $_POST["password"];
$mysql_qry = "select * from employee_data where username like '$username' and password like '$password';";


$result = mysqli_query($conn,$mysql_qry);
if(mysqli_num_rows($result) > 0) {

$response = array();

while($row = mysqli_fetch_array($result))
{
    array_push($response,array("name" => $row[1],"surname" => $row[2],"age" => $row[3],"username" => $row[4],"password" => $row[5],"amount" => $row[6],"email"=>$row[7]));

}

echo json_encode(array("server_response"=>$response));

}
else {
echo "login not success";
}


?>