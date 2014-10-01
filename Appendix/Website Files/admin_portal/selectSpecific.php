<?php
function selectSpecific($name) {
	try {
		$dbh = new PDO("sqlite:projectDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$stmt = $dbh->prepare("SELECT * FROM glossary WHERE symptom = :symptom");
		$stmt->bindParam(':symptom', $name, PDO::PARAM_STR);
		$stmt->execute();
		$result = $stmt->fetchAll();
		return $result;
		$dbh = null;
	}
	catch(PDOException $e) {
		echo $e->getMessage();
	}
}
?>