<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects/>


List all employees.

<table align="center" border="1">
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Inn</td>
        <td>Email</td>
        <td>Birthday</td>
        <td>Department</td>
        <td>Action</td>
    </tr>
    <c:forEach items="${employeeList}" var="employee">
    <portlet:actionURL var="delete">
        <portlet:param name="employeeId" value="${employee.id}"/>
        <portlet:param name="identifier" value="delEmpl"/>
        <portlet:param name="beforeUrl" value="listEmpl"/>
    </portlet:actionURL>
    <portlet:renderURL var="edit">
        <portlet:param name="employeeId" value="${employee.id}"/>
        <portlet:param name="identifier" value="editEmpl"/>
        <portlet:param name="url" value="listEmpl"/>
    </portlet:renderURL>
    <tr>
      <td>${employee.firstName}</td>
      <td>${employee.lastName}</td>
      <td>${employee.inn}</td>
      <td>${employee.email}</td>
      <td>${employee.birthday}</td>
      <td>${employee.department.name}</td>
      <td>
          <a href="${edit}"><button>Edit</button></a>
          <a href="${delete}"><button>Delete</button></a>
      </td>
    </tr>

    </c:forEach>
</table>

