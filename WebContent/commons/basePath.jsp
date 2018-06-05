<%
	//获取当前请求的应用上下文路径
	String path = request.getContextPath();
	//获取部署在服务器的当前目录根目录的URL
	String basePath=request.getScheme()+"://"+request.getServerName()+
			":"+request.getServerPort()+path+"/";
%>
<!-- base标签，用于定义当前页面html标签url的基准路径-->
<base href="<%=basePath %>">

