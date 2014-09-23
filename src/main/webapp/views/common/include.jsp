<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="https://gitsea.com/tag/view" prefix="portal" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String path=request.getContextPath();
%>
<c:set var="ctx"  value="${pageContext.request.contextPath}"/>
<!-- <script src="http://lib.sinaapp.com/js/jquery/1.7.2/jquery.min.js"></script> -->
<%-- <script src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script src="<%=path %>/js/md5-min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/jquery-ui-1.10.0.custom.min.js"></script>
<script src="<%=path %>/js/jquery.ztree.all-3.5.js"></script>
<script src="<%=path %>/js/jquery.fancybox.js?v=2.1.5"></script>
<script src="<%=path %>/js/jquery-ui-timepicker-addon.js"></script> --%>
<script langeuage="javascript">
function doPagnation(obj){
	var page=$(obj).attr("title");
	$("#filter_form").append("<input type='hidden' name='currentPage' value='"+page+"'/>");
	doFilter();
}
function doFilter(){
	$("#filter_form").submit();
}

function goUrl(url){
	window.location.href=url;
}

function goAdd(){
	var url='<%=path%>/'+baseUrl+'/add';
	goUrl(url);
	
}

function editItem(itemKey){
	var url='<%=path%>/'+baseUrl+'/edit?id='+itemKey;
	goUrl(url);
}

function detail(itemKey){
	var url='<%=path%>/'+baseUrl+'/detail?id='+itemKey;
	
	goUrl(url);
}

function doSave(){
	if(validate()){
		$("#edit_form").submit();
	}
}

function doCancel(){
	var url='<%=path%>/'+baseUrl+'/list';
	goUrl(url);
}

function setMessage(index,message){
	if($("#"+index+"\\.errors").length==0){
		$("#"+index).after("<span class=\"validate-error\" id=\""+index+".errors\">"+message+"</span>");
	}else{
		$("#"+index+"\\.errors").html(message);
	}
}

function removeMessage(index){
	$("#"+index+"\\.errors").remove();
}
</script>

