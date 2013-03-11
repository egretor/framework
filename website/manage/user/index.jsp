<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="allow">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="UTF-8">
<title>Framework</title>
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/claro/claro.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/nihilo/nihilo.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/soria/soria.css" />
<link rel="stylesheet"
	href="resource/javascript/dojo/dijit/themes/tundra/tundra.css" />
<style type="text/css">
</style>
<script type="text/javascript"
	src="resource/javascript/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript">
	dojoConfig = {
		parseOnLoad : true
	};
</script>
<script type="text/javascript"
	src="resource/javascript/dojo/dojo/dojo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body class="claro">
	<s:property value="test" />
</body>
	</html>
</s:if>