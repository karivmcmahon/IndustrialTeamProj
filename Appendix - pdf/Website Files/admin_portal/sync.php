<?php 
$lastUpdated = $_POST['lastUpdated'];
$currentDate = $_POST['currentDate'];
updateApp($lastUpdated, $currentDate);
//updateApp("2014-10-01 15:10:10");
function updateApp($lastupdated, $currentDate) {
	try {
		header('Content-Type: application/json');
		$array = array();
		$dbh = new PDO("sqlite:projectDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);		
		$stmt = $dbh->prepare("SELECT * FROM glossary WHERE datetime(timestamp) > datetime(:lastUpdated);");
		$stmt->bindParam(':lastUpdated', $lastupdated, PDO::PARAM_STR);
		//$stmt->bindParam(':todaysDate', $currentDate, PDO::PARAM_STR);
		$stmt->execute();
		while($row = $stmt->fetch()) {
			$row_array['_id'] = $row['_id'];
			$row_array['symptom'] = $row['symptom'];
			$row_array['type'] = $row['type'];
			$row_array['basicFacts'] = $row['basicFacts'];
			$row_array['control'] = $row['control'];
			$row_array['diagnostics'] = $row['diagnostics'];
			$row_array['timestamp'] = $row['timestamp'];
			$row_array['imageid'] = base64_encode($row['imageid']);
			$row_array['imageid2'] = base64_encode($row['imageid2']);
			$row_array['imageid3'] = base64_encode($row['imageid3']);
			$row_array['imageid4'] = base64_encode($row['imageid4']);
			$row_array['imageid5'] = base64_encode($row['imageid5']);
			$row_array['imageid6'] = base64_encode($row['imageid6']);
			array_push($array,$row_array);
		}
		echo json_encode($array);
	}
	catch(PDOException $e) {
		echo $e->getMessage();
	}
}
?>