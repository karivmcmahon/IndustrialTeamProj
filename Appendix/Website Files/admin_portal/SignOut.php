<?php
session_start();
$_SESSION["loggedIn"] = false;
header("Location: https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/admin.php");

?>