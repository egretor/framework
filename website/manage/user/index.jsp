<%@page import="com.opensymphony.xwork2.ActionSupport"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.io.FileFilter"%>
<%@page import="java.net.URI"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="allow">
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
			// alert(window.parent.abc);
		});
	</script>
	abc123
	<br />
	<br />
	<s:property value="manageUsers.size" />

	<br />999999999999999
	<br />
	<s:iterator id="manageUser" value="manageUsers">
		<s:property value="#manageUser.uuid" />
		<br />
		<s:property value="#manageUser.insertUserId" />
		<br />
		<s:property value="#manageUser.insertTimeText" />
		<br />
		<s:property value="#manageUser.updateUserId" />
		<br />
		<s:property value="#manageUser.updateTime" />
		<br />
		<s:property value="#manageUser.remark" />
		<br />
		<s:property value="#manageUser.name" />
		<br />
		<s:property value="#manageUser.account" />
		<br />
		<s:property value="#manageUser.password" />
		<br />
		<s:property value="#manageUser.prerogative" />
		<br />
	</s:iterator>
	<%
		Package[] ps = Package.getPackages();
			for (int i = 0; i < ps.length; i++) {
				if (ps[i].getName().indexOf("unknown.") == 0) {

					String url = ps[i].getName().replace('.', '/');
					Enumeration<URL> urls = Thread.currentThread()
							.getContextClassLoader().getResources(url);

					while (urls.hasMoreElements()) {
						URL u = urls.nextElement();

						File file = new File(new URI(u.toExternalForm()));
						File[] classFiles = file.listFiles();
						for (int j = 0; j < classFiles.length; j++) {
							if (classFiles[j].getName().indexOf(".class") >= 0) {
								int cll = classFiles[j].getName().length();
								int end = cll - ".class".length();

								String clazzN = classFiles[j].getName();
								String clazzName = clazzN.substring(0, end);

								String fullName = ps[i].getName() + "."
										+ clazzName;
								if (Class.forName(fullName).getSuperclass()
										.equals(ActionSupport.class)) {
									out.print(fullName);
									out.print("<br />");
								}
							}
						}
					}
				}
			}
	%>
</body>
	</html>
</s:if>