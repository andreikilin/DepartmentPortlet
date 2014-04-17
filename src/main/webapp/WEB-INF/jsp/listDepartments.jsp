<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects/>


<c:choose>
    <c:when test="${!departmentList.isEmpty()}" >
        <table align="center">
            <c:forEach items="${departmentList}" var="department">
                <portlet:renderURL var="addToDepartment">
                    <portlet:param name="identifier" value="addToDep"/>
                    <portlet:param name="departmentId" value="${department.id}"/>
                    <portlet:param name="url" value="listDep"/>
                </portlet:renderURL>
                <portlet:renderURL var="editDepartment">
                    <portlet:param name="idenfitier" value="editDep"/>
                    <portlet:param name="departmentId" value="${department.id}"/>
                    <portlet:param name="url" value="listDep"/>
                </portlet:renderURL>
                <portlet:renderURL var="listEmployeesOfDepartment">
                    <portlet:param name="identifier" value="listDepEmpl"/>
                    <portlet:param name="departmentId" value="${department.id}"/>
                    <portlet:param name="url" value="listDep"/>
                </portlet:renderURL>
                <portlet:renderURL var="deleteDepartment">
                    <portlet:param name="identifier" value="askDelDep"/>
                    <portlet:param name="departmentId" value="${department.id}"/>
                    <portlet:param name="beforeUrl" value="listDep"/>
                </portlet:renderURL>
                <tr>
                    <td>${department.name}</td>
                    <td><a href="${addToDepartment}">           <button type="button">Add        </button></a></td>
                    <td><a href="${editDepartment}">            <button type="button">Edit       </button></a></td>
                    <td><a href="${listEmployeesOfDepartment}"> <button type="button">Employees  </button></a></td>
                    <td><a href="${deleteDepartment}">          <button type="button">Delete     </button></a></td>
                </tr>
            </c:forEach>
        </table>

    </c:when>
    <c:when test="${departmentList.isEmpty()}" >
        <h3 align="center">No departments</h3>
    </c:when>
</c:choose>