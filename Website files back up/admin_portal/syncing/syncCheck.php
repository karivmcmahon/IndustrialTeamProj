<?php

	/*
	 * Check to see what has been updated.
	 */
	function checkTimeStamp() {
		try {
			$current = date('Y-m-d H:i:s');
			$dbh = new PDO("sqlite:../projectDB.sqlite");
			$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$stmt = $dbh->prepare("SELECT _id, symptom, type, imageid, imageid2, imageid3, imageid4, imageid5, imageid6, basicFacts, control, diagnostics, method, timestamp FROM glossary WHERE timestamp < datetime(:timestamp)");
			$stmt->bindParam(':timestamp', $current, PDO::PARAM_STR);
			$stmt->execute();	
        	return $stmt;
		} catch(PDOException $e) {
			echo $e->getMessage();
		}
	}
	
	/*
 	 * Method to update the timestamp with the current time.
 	 */
	function updateTimeStamp() {
		try {
			//$id = 1;
			$current = date('Y-m-d H:i:s');
			$dbh = new PDO("sqlite:../projectDB.sqlite");
			$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$stmt = $dbh->prepare("UPDATE glossary SET timestamp=datetime(:timestamp)");
			$stmt->bindParam(':timestamp', $current, PDO::PARAM_STR);
			//$stmt->bindParam(':id', $id, PDO::PARAM_STR);
			$stmt->execute();
		} catch(PDOException $e) {
			echo $e->getMessage();
		}
	}
	
	function main() {
		//$column_array = array('_id', 'type', 'imageid', 'imageid2', 'imageid3', 'imageid4', 'imageid5', 'imageid6', 'basicFacts', 'control', 'diagnostics', 'method', 'timestamp');
		$column_array = array('_id', 'type', 'basicFacts', 'control', 'diagnostics', 'method', 'timestamp');
		$table_array = array();
		$row_array = array('_id' => '', 'type' => '', 'imageid' => '', 'imageid2' => '', 'imageid3' => '', 'imageid4' => '', 
							'imageid5' => '', 'imageid6' => '', 'basicFacts' => '', 'control' => '', 'diagnostics' => '', 'method' => '', 'timestamp' => '',);
  		
  		$stmt = checkTimeStamp();
		while($row = $stmt->fetch()) {
			for($i = 0; $i < count($column_array); $i++) {		
				$row_array[$column_array[$i]] = $row[$column_array[$i]];
    			array_push($table_array, $row_array);
    			$row_array = null;
 			}
		}	
		echo json_encode($table_array);
		
		//for($i=0;$i<count($table_array);$i++){
//     		$c=0;
//     		foreach($table_array[$i] as $key=>$value){
//         		$c++;
//         		echo $key."=".$value;
//         		if($c<count($table_array[$i])) echo ",";
//     		}
//     		echo "<br>";
// 		}
	}
	
	main();
?>