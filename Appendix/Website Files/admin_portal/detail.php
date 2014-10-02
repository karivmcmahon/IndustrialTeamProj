<!DOCTYPE html>
<?php
session_start();
if ($_SESSION["loggedIn"] != true) {
	header("Location: https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/admin.php");
}
if($_SESSION["success"] == false) {
	$success = false;
}
else {
	$success = true;
} ?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin Portal</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/global.css" rel="stylesheet">

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
			</button></h1>
		</form>
  		<div class="col-sm-3 left-container">
  	    	<ul class="nav nav-stacked custom">
  				<li><a href="view.php"><span class="glyphicon glyphicon-chevron-left pull-left glyph-detail"></span>Back</a></li>
  			</ul>
		</div>
		
		<div class="col-sm-9 right-container" style="border:1px solid grey;">
			<h3>Detailed Look</h3>
			<?php
				include 'selectSpecific.php';
				$data = $_REQUEST['data'];
				$data = trim($data, " ");
				$stmt = selectSpecific($data);
				foreach( $stmt as $row ) { 
				?>
					<form role="form" action="updateData.php" method="post">
						<div class="form-group">
							<input type="hidden" name="_id" value="<?php echo $row['_id']; ?>" >
						</div>
						<div class="form-group">
							<label for="inputSymptom">Disease</label>
							<input type="text" class="form-control" name="name" value="<?php echo $row['symptom']; ?>">
						</div>
						
						<div class="form-group">
							<label for="inputType">Type:</label>
							<select name="inputType" class="form-control" id="drop">
								<option value="Tuber"  <?php echo ($row['type'] == "Tuber" ? "selected" : ""); ?>>Tuber</option>
								<option value="Leaf" <?php echo ($row['type'] == "Leaf" ? "selected" : ""); ?>>Leaf</option>
								<option value="Insects + Pests" <?php echo ($row['type'] == "Insects + Pests" ? "selected" : ""); ?>>Insects + Pests</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="inputBasicFacts">Basic Facts</label>
							<textarea class="form-control" rows="3"]  name="basicFacts"><?php echo $row['basicFacts']; ?></textarea>
						</div>
							
						<div class="form-group">
							<label for="inputControl">Control</label>
							<textarea class="form-control" rows="3"  name="control"><?php echo $row['control']; ?></textarea>
						</div>
				
						<div class="form-group">
							<label for="inputBasicFacts">Diagnostics</label>
							<textarea class="form-control" rows="3"  name="diagnostics"><?php echo $row['diagnostics']; ?></textarea>
						</div>
						<button type="submit" class="btn btn-lg btn-primary btn-block submit-btn">Update</button>
					</form>
					<?php
				} ?>			
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
		function setSelectedIndex(s, valsearch) {
			alert(valsearch);
			// Loop through all the items in drop down list
			for (i = 0; i< s.options.length; i++) {
				if (s.options[i].value == valsearch) {
					// Item is found. Set its property and exit
					s.options[i].selected = true;
					break;
				}
			}
			return;
		}
	</script>
</body>
</html>
	