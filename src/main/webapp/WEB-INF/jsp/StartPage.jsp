<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<portlet:defineObjects/>
<portlet:resourceURL var="resourceUrl"/>

<portlet:resourceURL id="getDepList" var="getDepList"/>
<a href="${getDepList}">Get Department List</a>

<portlet:resourceURL id="getEmplByDepList" var="getEmplByDepList">
    <portlet:param name="departmentId" value="3"/>
</portlet:resourceURL>
<a href="${getEmplByDepList}">Get Employees list by department</a>

<script src="<c:url value="/js/jquery-1.11.0.js"/>"></script>
<script src="<c:url value="/js/jquery-validate.js"/>"></script>
<script src="<c:url value="/js/class-extend.js"/>"></script>
<script src="<c:url value="/js/form.js"/>"></script>
<script src="<c:url value="/js/department.js"/>"></script>
<script src="<c:url value="/js/employee.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>

<script language="JavaScript">
    $(document).ready(function() {
        var resourceUrl = "${resourceUrl}";
        var pageController = new PageController(resourceUrl, "<portlet:namespace/>");

    });
</script>

<div class="DepartmentPortlet"></div>