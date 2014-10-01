<!DOCTYPE html>
<?php
session_start();
if ($_SESSION["loggedIn"] != true)
{
	header("Location: https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/admin.php");
}
if($_SESSION["success"] == false)
{
	$sucess = false;
}
?>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Portal</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/insert.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div class="container">
  				<form action="SignOut.php" method="post">
  		<h1 class="text-center">The James Hutton Institute Diagnosis Tool <small>Admin Portal</small>
		<button type="submit" class="btn  btn-lg; background-color: Transparent;">
			<span class="glyphicon glyphicon-off"></span> 
		</button>
		
		</h1>
		</form>
  		<div class="col-sm-3 left-container">
  	    	<ul class="nav nav-stacked custom">
  				<li class="active"><a href="view.php">View Current Data<span class="glyphicon glyphicon-chevron-right pull-right glyph"></span></a></li>
  				<li><a href="insert.php">Insert New Data<span class="glyphicon glyphicon-chevron-right pull-right glyph"></span></a></li>
			</ul>
		</div>
		
		<div class="col-sm-9 right-container" style="border:1px solid grey;">
			<h3>View Current Potato Diseases</h3>
    		<table class="table table-hover" action="selectData.php" method="get" >
        		<thead>
            		<tr>
                		<th>Disease</th>
                		<th>Type</th>
            		</tr>
        		</thead>
        		<tbody>
						<?php
						include 'selectData.php';
						$stmt = selectAll();
						while($row = $stmt->fetch())
						{ ?>
						<tr href="detail.php" class="clickableRow">
							<td > <?php echo $row['symptom']; ?> </td>
							<td> <?php echo $row['type']; ?> </td>
							<td><span class="glyphicon glyphicon-chevron-right pull-right glyph"></span></td>
						</tr>
						<?php 
						} 
						?>			
        		</tbody>
    		</table>
		</div>
	</div>
	
	<div class="footer container">
    	<p>&copy; The James Hutton Institute</p>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
        <script> 
    	jQuery(document).ready(function($) {
      		$(".clickableRow").click(function() {

				var value = $(this).find('td:first').text();
				var myurl = 'detail.php?data='+value;
				window.location.replace(myurl); 
      		});
		});
    </script>
  </body>
</html>
	