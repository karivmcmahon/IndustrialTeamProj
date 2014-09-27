<?php
selectAll();
function selectAll()
{
	try
	{
		$dbh = new PDO("sqlite:projectDB3.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$stmt = $dbh->prepare("SELECT symptom,type from glossary");
		$stmt->execute();
        return $stmt;
		
		$dbh = null;
	}
	catch(PDOException $e)
	{
		echo $e->getMessage();
	}
}

?>