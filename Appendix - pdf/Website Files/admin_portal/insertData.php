
<?php
session_start();
insert($_REQUEST['name'], $_REQUEST['inputType'], $_FILES['inputfile']['tmp_name'],$_FILES['inputfile2']['tmp_name'],$_FILES['inputfile3']['tmp_name'],$_FILES['inputfile4']['tmp_name'],$_FILES['inputfile5']['tmp_name'],$_FILES['inputfile6']['tmp_name'],$_REQUEST["basicFacts"], $_REQUEST["control"], $_REQUEST["diagnostics"]);
function insert( $symptom, $type, $imagepath, $imagepath2, $imagepath3 , $imagepath4, $imagepath5, $imagepath6, $basicFacts, $control, $diagnostics )
{
	try {
  
		$dbh = new PDO("sqlite:projectDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		
		$fp      = fopen($imagepath, 'r');
		$imageid = fread($fp,filesize($imagepath));
		fclose($fp);
		
		$fp      = fopen($imagepath2, 'r');
		$imageid2 = fread($fp,filesize($imagepath2));
		fclose($fp);
		
		$fp      = fopen($imagepath3, 'r');
		$imageid3 = fread($fp,filesize($imagepath3));
		fclose($fp);
		
		$fp      = fopen($imagepath4, 'r');
		$imageid4 = fread($fp,filesize($imagepath4));
		fclose($fp);
		
		$fp      = fopen($imagepath5, 'r');
		$imageid5 = fread($fp,filesize($imagepath5));
		fclose($fp);
		
		$fp      = fopen($imagepath6, 'r');
		$imageid6 = fread($fp,filesize($imagepath6));
		fclose($fp);
		
		
		//Get current date
		date_default_timezone_set("Europe/London");
		$timestamp = date('Y-m-d H:i:s');
		
		$stmt = $dbh->prepare("INSERT INTO glossary(symptom,type,imageid,imageid2,imageid3,imageid4,imageid5,imageid6,basicFacts,control,diagnostics,timestamp) VALUES (:symptom, :type, :imageid,:imageid2,:imageid3,:imageid4,:imageid5,:imageid6,:basicFacts,:control,:diagnostics, datetime(:timestamp))");
		
		$stmt->bindParam(':symptom', $symptom, PDO::PARAM_STR);
		$stmt->bindParam(':type', $type, PDO::PARAM_STR);
		$stmt->bindParam(':imageid', $imageid, PDO::PARAM_LOB);
		$stmt->bindParam(':imageid2', $imageid2, PDO::PARAM_LOB);
		$stmt->bindParam(':imageid3', $imageid3, PDO::PARAM_LOB);
		$stmt->bindParam(':imageid4', $imageid4, PDO::PARAM_LOB);
		$stmt->bindParam(':imageid5', $imageid5, PDO::PARAM_LOB);
		$stmt->bindParam(':imageid6', $imageid6, PDO::PARAM_LOB);
		$stmt->bindParam(':basicFacts', $basicFacts, PDO::PARAM_STR);
		$stmt->bindParam(':control', $control, PDO::PARAM_STR);
		$stmt->bindParam(':diagnostics', $diagnostics, PDO::PARAM_STR);
		$stmt->bindParam(':timestamp', $timestamp, PDO::PARAM_STR);
		
		$stmt->execute();
		header("Location: ".$_SERVER["HTTP_REFERER"]."?id=success");
		
		$dbh = null;
	} catch(PDOException $e) {
		header("Location: ".$_SERVER["HTTP_REFERER"]."?id=error");
	}
}	
?>

	