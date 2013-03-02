<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/claro/claro.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/nihilo/nihilo.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/soria/soria.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/tundra/tundra.css" />
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0;
	overflow: hidden;
}

#borderContainer {
	width: 100%;
	height: 100%;
}
</style>
<script type="text/javascript"
	src="resource/javascript/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript">
	dojoConfig = {
		parseOnLoad : true
	};
	var abc = 'Abc!@#';
</script>
<script type="text/javascript"
	src="resource/javascript/dojo/dojo/dojo.js"></script>
<script type="text/javascript">
	require([ "dojo/parser", "dijit/layout/ContentPane",
			"dijit/layout/BorderContainer" ]);
</script>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body class="claro">
	<div data-dojo-type="dijit/layout/BorderContainer"
		data-dojo-props="gutters:false, liveSplitters:true"
		id="borderContainer">
		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'top'" style="height: 90px;background-color:#339966"></div>
		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="splitter:true, region:'left'" minSize="160"
			maxSize="320" style="width: 160px;background-color:#e6e6e6">Hi, I'm leading pane</div>
		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="splitter:true, region:'center'">
			<iframe src="manage/maUser.action" style="width:100%; height:100%" frameborder="0"></iframe>
		</div>
		<div data-dojo-type="dijit/layout/ContentPane"
			data-dojo-props="region:'bottom'" style="height: 20px;background-color:#000000">Hi, I'm
			leading pane</div>
	</div>
</body>
</html>