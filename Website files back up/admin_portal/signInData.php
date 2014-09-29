<?php
session_start();
signIn( $_REQUEST['email'], $_REQUEST['password'] );
function signIn( $email, $password )
{
	try
	{
		$dbh = new PDO("sqlite:loginDB.sqlite");
		$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		$stmt = $dbh->prepare("SELECT * FROM loginDetails WHERE username = :username and password = :password");
		$stmt->bindParam(':username', $email, PDO::PARAM_STR);
		$stmt->bindParam(':password', $password, PDO::PARAM_STR);
	    $stmt->execute();
		$result = $stmt->fetchAll();
		$count = count($result);
		if ( $count == 1)
		{
			$_SESSION["loggedIn"] = true;
			$_SESSION["username"] = $email;
			header("Location: https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/view.php");
			
		}
		else
		{
			$_SESSION["loggedIn"] = false;
			$_SESSION["username"] = null;
			header("Location: ".$_SERVER["HTTP_REFERER"]);
			
		}
		$dbh = null;
	}
	catch( PDOException $e )
	{
		$_SESSION["username"] = null;
		header("Location: ".$_SERVER["HTTP_REFERER"]);
		
	}
}
?>