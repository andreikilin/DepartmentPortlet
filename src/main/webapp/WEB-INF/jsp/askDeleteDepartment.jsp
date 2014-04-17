<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<portlet:defineObjects/>

<portlet:actionURL var="deleteDepartment" windowState="maximized">
    <portlet:param name="identifier" value="${action}"/>
    <portlet:param name="departmentId" value="${department.id}"/>
    <portlet:param name="beforeUrl" value="${beforeUrl}"/>
</portlet:actionURL>

<c:choose>
    <c:when test="${isEmpty eq true}">
        <h3>Are you sure to delete "${department.name}" ?</h3>
        <a href="${deleteDepartment}"><button>Delete</button></a>
    </c:when>
    <c:when test="${isEmpty eq false}">
        Department "${department.name}" is not empty
    </c:when>
</c:choose>