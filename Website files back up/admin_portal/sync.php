<?php 
updateApp("2014-09-29 20:23:26");
function updateApp( $updated )
{
	try
		{
		header('Content-Type: application/json');
		//echo $updated;
			$array = array();
			$dbh = new PDO("sqlite:projectDB2.sqlite");
			$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$stmt = $dbh->prepare("SELECT * FROM glossary WHERE timestamp = :lastUpdated");
			$stmt->bindParam(':lastUpdated', $updated, PDO::PARAM_STR);
			$stmt->execute();
			while($row = $stmt->fetch())
			{
				$row_array['_id'] = $row['_id'];
				$row_array['symptom'] = $row['symptom'];
				$row_array['type'] = $row['type'];
				$row_array['basicFacts'] = $row['basicFacts'];
				$row_array['control'] = $row['control'];
				$row_array['diagnostics'] = $row['diagnostics'];
				$row_array['timestamp'] = $row['timestamp'];
				array_push($array,$row_array);
			}
			echo json_encode($array);
			//return json_encode($array);  
		
		
			
		}
		catch(PDOException $e)
		{
			echo $e->getMessage();
		}
}
?>