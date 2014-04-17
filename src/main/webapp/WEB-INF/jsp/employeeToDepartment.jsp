<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:renderURL var="redirectURL">
    <portlet:param name="identifier" value="error"/>
</portlet:renderURL>

<c:choose>
    <c:when test="${!employeeList.isEmpty()}">
        <portlet:actionURL var="movingEmployees">
            <portlet:param name="identifier" value="emplToDep"/>
            <portlet:param name="beforeUrl" value="${beforeUrl}"/>
            <portlet:param name="action" value="${action}"/>
            <portlet:param name="redirectURL" value="${redirectURL}"/>
        </portlet:actionURL>
        <h3 align="center">Add employees to department "${department.name}"</h3>
        <form method="POST" action="${movingEmployees}">
            <table align="center" border="1">
                <tr>
                    <td></td>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Inn</td>
                    <td>Email</td>
                    <td>Birthday</td>
                </tr>
                <c:forEach items="${employeeList}" var="employee" >
                    <tr>
                        <td><input type="checkbox"  name="<portlet:namespace/>employeeId" value="${employee.id}"></td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.inn}</td>
                        <td>${employee.email}</td>
                        <td>${employee.birthday}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td><input hidden="hidden" name="<portlet:namespace/>departmentId" value="${department.id}"></td>
                    <c:choose>
                        <c:when test="${action ne null}">
                            <td><input type="submit" value="${action}"></td>
                        </c:when>
                        <c:when test="${action eq null}">
                            <td><input type="submit" value="Action"></td>
                        </c:when>
                    </c:choose>
                </tr>
            </table>
        </form>
    </c:when>
    <c:when test="${employeeList.isEmpty()}">
        <h2>No free employee</h2>
    </c:when>
</c:choose>
