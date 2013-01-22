<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${url.css}/bootstrap.min.css">
    <script type="text/javascript" src="${url.js}/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${url.js}/jquery.tablesorter1.min.js"></script>
    <script type="text/javascript" src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>

	<style>
	  body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
	
	  textarea#result {
			width : 500px;
			height : 300px;
	 }
		
	</style>

	<script>
		var url_base = "${url.base}";
		var url_api = url_base;
		
		
		$(function() {
			$('#rtoken').val('CrqkNfpEZezFQdrr4Hpy6voh1cZ5OzSsPw8iy_yPxlQXkcOrkABRw-j1QwrGg_sExDPkh_03MN7XhBEzPUItdA==');
		
		});
	</script>
</head>
<body>


  <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="${url.base}/test/">Msgr Api</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">ApiList <b class="caret"></b></a>
              <ul class="dropdown-menu">
              	<li class="nav-header">Member</li>
                <li><a href="${url.base}/test/register">register</a></li>
                <li><a href="${url.base}/test/waitroom">waitroom</a></li>
                <li><a href="${url.base}/test/dash">dash</a></li>
                <li><a href="${url.base}/test/read">read</a></li>
                <li><a href="${url.base}/test/write">write</a></li>
                <li><a href="${url.base}/test/myroom">myroom</a></li>
                <li><a href="${url.base}/test/prove">prove</a></li>
                <li><a href="${url.base}/test/image">image</a></li>
                <li><a href="${url.base}/test/friends">friends</a></li>
                <li><a href="${url.base}/test/friend_add">friends add/delete</a></li>
                <li class="divider"></li>
              </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span6">
