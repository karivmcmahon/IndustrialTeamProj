<?php
selectAll();
function selectAll() {
	try {
		$dbh = new PDO("sqlite:projectDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$stmt = $dbh->prepare("SELECT symptom, type, imageid, imageid2, imageid3, imageid4, imageid5 from glossary");
		$stmt->execute();
        return $stmt;
		$dbh = null;
	}
	catch(PDOException $e) {
		echo $e->getMessage();
	}
}
?>