<?php
updateData($_REQUEST['name'],  $_REQUEST['inputType'], $_REQUEST["basicFacts"], $_REQUEST["control"], $_REQUEST["diagnostics"], $_REQUEST["_id"] );
function updateData($symptom, $type, $basicFacts, $control, $diagnostics, $id)
{
	try {

		$dbh = new PDO("sqlite:projectDB3.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$stmt = $dbh->prepare("UPDATE glossary SET symptom= :symptom, type= :type,  basicFacts = :basicFacts, control = :control, diagnostics = :diagnostics WHERE _id= :id");
		$stmt->bindParam(':symptom', $symptom, PDO::PARAM_STR);
		$stmt->bindParam(':id', $id, PDO::PARAM_STR);
		$stmt->bindParam(':type', $type, PDO::PARAM_STR);
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