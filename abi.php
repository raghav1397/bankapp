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
$name = $_POST["name"];
$surname = $_POST["surname"];
$age=$_POST["age"];
$username = $_POST["username"];
$password =$_POST["password"];
$sql = "INSERT INTO employee_data (name,surname,age,username,password)
VALUES ('$name ','$surname','$age','$username','$password')";
$mysql_qry = "select * from employee_data where username like '$username' and password like '$password';";
$result = mysqli_query($conn ,$mysql_qry);
if (mysqli_num_rows($result) > 0) {
    echo "Username already exists";
}
elseif ($conn->query($sql) === TRUE) {

    echo "created successfully";
}
?> 