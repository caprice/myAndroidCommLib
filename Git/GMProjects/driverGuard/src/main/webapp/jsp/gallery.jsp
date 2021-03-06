<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8">
	<title>Free HTML5 Bootstrap Admin Template</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
</head>

<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.html"> <img alt="Charisma Logo" src="../img/logo20.png" /> <span>Charisma</span></a>
				
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone"> Change Theme / Skin</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> admin</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a href="login.html">Logout</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->
				
				<div class="top-nav nav-collapse">
					<ul class="nav">
						<li><a href="#">Visit Site</a></li>
						<li>
							<form class="navbar-search pull-left">
								<input placeholder="Search" class="search-query span2" name="query" type="text">
							</form>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">Main</li>
						<li><a class="ajax-link" href="index.html"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>
						<li><a class="ajax-link" href="ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>
						<li><a class="ajax-link" href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>
						<li><a class="ajax-link" href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>
						<li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>
						<li><a class="ajax-link" href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
						<li class="nav-header hidden-tablet">Sample Section</li>
						<li><a class="ajax-link" href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
						<li><a class="ajax-link" href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
						<li><a class="ajax-link" href="grid.html"><i class="icon-th"></i><span class="hidden-tablet"> Grid</span></a></li>
						<li><a class="ajax-link" href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
						<li><a href="tour.html"><i class="icon-globe"></i><span class="hidden-tablet"> Tour</span></a></li>
						<li><a class="ajax-link" href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
						<li><a href="error.html"><i class="icon-ban-circle"></i><span class="hidden-tablet"> Error Page</span></a></li>
						<li><a href="login.html"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
					</ul>
					<label id="for-is-ajax" class="hidden-tablet" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Gallery</a>
					</li>
				</ul>
			</div>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i> Gallery</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<p class="center">
							<button id="toggle-fullscreen" class="btn btn-large btn-primary visible-desktop" data-toggle="button">Toggle Fullscreen</button>
						</p>
						<br/>
						<ul class="thumbnails gallery">
														<li id="image-1" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/1.jpg)" title="Sample Image 1" href="../img/gallery/1.jpg"><img class="grayscale" src="../img/gallery/thumbs/1.jpg" alt="Sample Image 1"></a>
							</li>
														<li id="image-2" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/2.jpg)" title="Sample Image 2" href="../img/gallery/2.jpg"><img class="grayscale" src="../img/gallery/thumbs/2.jpg" alt="Sample Image 2"></a>
							</li>
														<li id="image-3" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/3.jpg)" title="Sample Image 3" href="../img/gallery/3.jpg"><img class="grayscale" src="../img/gallery/thumbs/3.jpg" alt="Sample Image 3"></a>
							</li>
														<li id="image-4" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/4.jpg)" title="Sample Image 4" href="../img/gallery/4.jpg"><img class="grayscale" src="../img/gallery/thumbs/4.jpg" alt="Sample Image 4"></a>
							</li>
														<li id="image-5" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/5.jpg)" title="Sample Image 5" href="../img/gallery/5.jpg"><img class="grayscale" src="../img/gallery/thumbs/5.jpg" alt="Sample Image 5"></a>
							</li>
														<li id="image-6" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/6.jpg)" title="Sample Image 6" href="../img/gallery/6.jpg"><img class="grayscale" src="../img/gallery/thumbs/6.jpg" alt="Sample Image 6"></a>
							</li>
														<li id="image-7" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/7.jpg)" title="Sample Image 7" href="../img/gallery/7.jpg"><img class="grayscale" src="../img/gallery/thumbs/7.jpg" alt="Sample Image 7"></a>
							</li>
														<li id="image-8" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/8.jpg)" title="Sample Image 8" href="../img/gallery/8.jpg"><img class="grayscale" src="../img/gallery/thumbs/8.jpg" alt="Sample Image 8"></a>
							</li>
														<li id="image-9" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/9.jpg)" title="Sample Image 9" href="../img/gallery/9.jpg"><img class="grayscale" src="../img/gallery/thumbs/9.jpg" alt="Sample Image 9"></a>
							</li>
														<li id="image-10" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/10.jpg)" title="Sample Image 10" href="../img/gallery/10.jpg"><img class="grayscale" src="../img/gallery/thumbs/10.jpg" alt="Sample Image 10"></a>
							</li>
														<li id="image-11" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/11.jpg)" title="Sample Image 11" href="../img/gallery/11.jpg"><img class="grayscale" src="../img/gallery/thumbs/11.jpg" alt="Sample Image 11"></a>
							</li>
														<li id="image-12" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/12.jpg)" title="Sample Image 12" href="../img/gallery/12.jpg"><img class="grayscale" src="../img/gallery/thumbs/12.jpg" alt="Sample Image 12"></a>
							</li>
														<li id="image-13" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/13.jpg)" title="Sample Image 13" href="../img/gallery/13.jpg"><img class="grayscale" src="../img/gallery/thumbs/13.jpg" alt="Sample Image 13"></a>
							</li>
														<li id="image-14" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/14.jpg)" title="Sample Image 14" href="../img/gallery/14.jpg"><img class="grayscale" src="../img/gallery/thumbs/14.jpg" alt="Sample Image 14"></a>
							</li>
														<li id="image-15" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/15.jpg)" title="Sample Image 15" href="../img/gallery/15.jpg"><img class="grayscale" src="../img/gallery/thumbs/15.jpg" alt="Sample Image 15"></a>
							</li>
														<li id="image-16" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/16.jpg)" title="Sample Image 16" href="../img/gallery/16.jpg"><img class="grayscale" src="../img/gallery/thumbs/16.jpg" alt="Sample Image 16"></a>
							</li>
														<li id="image-17" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/17.jpg)" title="Sample Image 17" href="../img/gallery/17.jpg"><img class="grayscale" src="../img/gallery/thumbs/17.jpg" alt="Sample Image 17"></a>
							</li>
														<li id="image-18" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/18.jpg)" title="Sample Image 18" href="../img/gallery/18.jpg"><img class="grayscale" src="../img/gallery/thumbs/18.jpg" alt="Sample Image 18"></a>
							</li>
														<li id="image-19" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/19.jpg)" title="Sample Image 19" href="../img/gallery/19.jpg"><img class="grayscale" src="../img/gallery/thumbs/19.jpg" alt="Sample Image 19"></a>
							</li>
														<li id="image-20" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/20.jpg)" title="Sample Image 20" href="../img/gallery/20.jpg"><img class="grayscale" src="../img/gallery/thumbs/20.jpg" alt="Sample Image 20"></a>
							</li>
														<li id="image-21" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/21.jpg)" title="Sample Image 21" href="../img/gallery/21.jpg"><img class="grayscale" src="../img/gallery/thumbs/21.jpg" alt="Sample Image 21"></a>
							</li>
														<li id="image-22" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/22.jpg)" title="Sample Image 22" href="../img/gallery/22.jpg"><img class="grayscale" src="../img/gallery/thumbs/22.jpg" alt="Sample Image 22"></a>
							</li>
														<li id="image-23" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/23.jpg)" title="Sample Image 23" href="../img/gallery/23.jpg"><img class="grayscale" src="../img/gallery/thumbs/23.jpg" alt="Sample Image 23"></a>
							</li>
														<li id="image-24" class="thumbnail">
								<a style="background:url(img/gallery/thumbs/24.jpg)" title="Sample Image 24" href="../img/gallery/24.jpg"><img class="grayscale" src="../img/gallery/thumbs/24.jpg" alt="Sample Image 24"></a>
							</li>
													</ul>
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
    
					<!-- content ends -->
			</div><!--/#content.span10-->
				</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>Settings</h3>
			</div>
			<div class="modal-body">
				<p>Here settings can be configured...</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary">Save changes</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
			<p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

</body>
</html>
