<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:renderURL var="listDepartments" windowState="maximized">
    <portlet:param name="identifier" value="listDep"/>
</portlet:renderURL>

<portlet:renderURL var="listEmployees" windowState="maximized">
    <portlet:param name="identifier" value="listEmpl"/>
</portlet:renderURL>

<portlet:renderURL var="addEmployee" windowState="maximized">
    <portlet:param name="identifier" value="editEmpl"/>
</portlet:renderURL>

<portlet:renderURL var="addDepartment" windowState="maximized">
    <portlet:param name="identifier" value="editDep"/>
</portlet:renderURL>

<a href="${listDepartments}"><button>Departments</button></a>
<a href="${listEmployees}"><button>Employees</button></a>
<a href="${addEmployee}"><button>New employee</button></a>
<a href="${addDepartment}"><button>New department</button></a>
