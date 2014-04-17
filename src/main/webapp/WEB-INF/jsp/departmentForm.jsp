<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<portlet:actionURL var="saveDepartment">
    <portlet:param name="identifier" value="saveDep"/>
    <portlet:param name="departmentId" value="${departmentId}"/>
    <portlet:param name="beforeUrl" value="${beforeUrl}"/>
</portlet:actionURL>
<script src="<c:url value="/js/jquery-1.11.0.js"/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // Alle inputs
        $('input').each(function () {
            var pnamespace = '<portlet:namespace/>';
            $(this).attr('id', pnamespace + this.id);
            $(this).attr('name', pnamespace + this.name);
        });
});
</script>

<form:form method="POST" action="${saveDepartment}" commandName="departmentForm">
    <table id="myTable">
        <tr>
            <td>Department Name: </td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
            <%--<td><input type="button" value="Save" onclick="addContact()"></td>--%>
        </tr>

        <%--<script type="text/javascript">--%>
            <%--function addContact(){--%>
                <%--var myTable = document.getElementById("myTable");--%>
                <%--var newTR = document.createElement("tr");--%>
                <%--var newName = document.createElement("td");--%>
                <%--newName.innerHTML = document.forms[0].name.value;--%>
                <%--newTR.appendChild(newName);--%>
                <%--myTable.appendChild(newTR);--%>
                <%--document.forms[0].reset()--%>
            <%--}--%>
        <%--</script>--%>
    </table>

</form:form>