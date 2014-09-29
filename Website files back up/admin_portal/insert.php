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
  				<li><a href="view.php">View Current Data<span class="glyphicon glyphicon-chevron-right pull-right glyph"></span></a></li>
  				<li class="active"><a href="insert.php">Insert New Data<span class="glyphicon glyphicon-chevron-right pull-right glyph"></span></a></li>
			</ul>
		</div>
		
		<div class="col-sm-9 right-container" style="border:1px solid grey;">
			<h3>Insert New Potato Disease</h3>
		
    		<form role="form" action="insertData.php" method="post">
   				<div class="form-group">
      				<label for="inputSymptom">Disease</label>
      				<input type="text" class="form-control" name="name" placeholder="Enter disease...">
   				</div>
   				
				<div class="form-group">
					<label for="inputType">Type:</label>
					<select name="inputType" class="form-control">
						<option value="Tuber">Tuber</option>
						<option value="Leaf">Leaf</option>
						<option value="Insects + Pests">Insects + Pests</option>
					</select>
				</div>

   				<div class="form-group">
      				<label for="inputfile">File input: Image 1</label>
      				<input type="file" name="inputfile" >
   				</div>
   				
   				<div class="form-group">
      				<label for="inputfile">File input: Image 2</label>
      				<input type="file" name="inputfile2">
   				</div>
   				
   				<div class="form-group">
      				<label for="inputfile">File input: Image 3</label>
      				<input type="file" name="inputfile3">
   				</div>
   				
   				<div class="form-group">
      				<label for="inputfile">File input: Image 4</label>
      				<input type="file" name="inputfile4">
   				</div>
   				
   				<div class="form-group">
      				<label for="inputfile">File input: Image 5</label>
      				<input type="file" name="inputfile5">
   				</div>
   				
   				<div class="form-group">
      				<label for="inputfile">File input: Image 6</label>
      				<input type="file" name="inputfile6">
   				</div>
   				
   				<div class="form-group">
    				<label for="inputBasicFacts">Basic Facts</label>
    				<textarea class="form-control" rows="3" placeholder="Enter some basic facts..." name="basicFacts"></textarea>
 				</div>
 				
 				<div class="form-group">
    				<label for="inputControl">Control</label>
    				<textarea class="form-control" rows="3" placeholder="Enter some control methods..." name="control"></textarea>
 				</div>
 				
 				<div class="form-group">
    				<label for="inputBasicFacts">Diagnostics</label>
    				<textarea class="form-control" rows="3" placeholder="Enter some diagnostics..." name="diagnostics"></textarea>
 				</div>
   				
   				<button type="submit" class="btn btn-lg btn-primary btn-block submit-btn">Submit</button>
			</form>
		</div>
	</div>
	
	<div class="footer container">
    	<p>&copy; The James Hutton Institute</p>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
	