<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	background-color: #cccccc;
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<script type="text/javascript"
		src="../resource/javascript/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		alert(window.parent.abc);
	});
	</script>
	abc123
	<br />
	<br />
	<s:property value="maUsers.size" />

	<s:iterator id="maUser" value="maUsers">
		<s:property value="#maUser.uuid" />
		<br />
	</s:iterator>
	<br />
	<br />
	<s:iterator id="maUser" value="maUsers">
		<s:property value="#maUser.uuid" />
		<br />
	</s:iterator>
	<br />
	<br />
	<s:iterator id="maUser" value="maUsers">
		<s:property value="#maUser.uuid" />
		<br />
	</s:iterator>
	<br />
	<br />
	<s:iterator id="maUser" value="maUsers">
		<s:property value="#maUser.uuid" />
		<br />
	</s:iterator>
	<br />
	<br />
	<s:iterator id="maUser" value="maUsers">
		<s:property value="#maUser.uuid" />
		<br />
	</s:iterator>
</body>
</html>