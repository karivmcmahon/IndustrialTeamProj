
<?php
session_start();
insert($_REQUEST['name'], $_REQUEST['inputType'], $_REQUEST['inputfile'], $_REQUEST["inputfile2"],$_REQUEST["inputfile3"],$_REQUEST["inputfile4"],$_REQUEST["inputfile5"],$_REQUEST["inputfile6"],$_REQUEST["basicFacts"], $_REQUEST["control"], $_REQUEST["diagnostics"]);
function insert( $symptom, $type, $imagepath, $imagepath2, $imagepath3 , $imagepath4, $imagepath5, $imagepath6, $basicFacts, $control, $diagnostics )
{
	try {

		$dbh = new PDO("sqlite:projectDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		
		$imageid = fopen($imagepath,'rb');
		$imageid2 = fopen($imagepath2,'rb');
		$imageid3 = fopen($imagepath3,'rb');
		$imageid4 = fopen($imagepath4,'rb');
		$imageid5 = fopen($imagepath5,'rb');
		$imageid6 = fopen($imagepath6,'rb');
		
		$stmt = $dbh->prepare("INSERT INTO glossary(symptom,type,imageid,imageid2,imageid3,imageid4,imageid5,imageid6,basicFacts,control,diagnostics) VALUES (:symptom, :type, :imageid,:imageid2,:imageid3,:imageid4,:imageid5,:imageid6,:basicFacts,:control,:diagnostics)");
		
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
		
		$stmt->execute();
		header("Location: ".$_SERVER["HTTP_REFERER"]."?id=success");
		
		$dbh = null;
	} catch(PDOException $e) {
		header("Location: ".$_SERVER["HTTP_REFERER"]."?id=error");
	}
}	
?>

	