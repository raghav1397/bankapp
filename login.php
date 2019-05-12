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
  
	/*Get the id of the last visible item in the RecyclerView from the request and store it in a variable. For            the first request id will be zero.*/	
	$id = $_GET["id"];
	
	$sql= "Select * from movies_table where id between ($id+1) and ($id+10)";
	
	$result = mysqli_query($conn ,$sql);
	
	while ($row = mysqli_fetch_assoc($result)) {
		
		$array[] = $row;
		
	}
	header('Content-Type:Application/json');
	
	echo json_encode($array);
 
    mysqli_free_result($result);
 
    mysqli_close($conn);
  
 ?>