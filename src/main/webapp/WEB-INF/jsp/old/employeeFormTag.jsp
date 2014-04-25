<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<portlet:defineObjects />

<portlet:actionURL var="employeeAction">
    <portlet:param name="employeeId" value="${employeeForm.id}"/>
    <portlet:param name="identifier" value="saveEmpl"/>
    <portlet:param name="beforeUrl" value="${beforeUrl}"/>
</portlet:actionURL>

<script src="<c:url value="/js/jquery-1.11.0.js"/>"></script>
<script type="text/javascript" >
    $(document).ready(function() {
        // Alle inputs
        $('input').each(function() {
            var pnamespace = '<portlet:namespace/>';
            $(this).attr('id', pnamespace + this.id);
            $(this).attr('name', pnamespace + this.name);
        });
        // alle selects
        $('select').each(function() {
            var pnamespace = '<portlet:namespace/>';
            $(this).attr('id', pnamespace + this.id);
            $(this).attr('name', pnamespace + this.name);
        });
    });
</script>
<form:form method="POST" action="${employeeAction}" commandName="employeeForm">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName"/></td>
            <td><span class="error"><form:errors path="firstName"/></span></td>
            <form:hidden path="id"/>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName"/></td>
            <td><span class="error"><form:errors path="lastName"/></span></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email"/></td>
            <td><span class="error"><form:errors path="email"/></span></td>
        </tr>
        <tr>
            <td>Identifical number:</td>
            <td><form:input path="inn"/></td>
            <td><span class="error"><form:errors path="inn"/></span></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td>
                <form:select path="day">
                    <form:option value="0" label="--"/>
                    <form:options items="${dayList}"/>
                </form:select>
            </td>
            <td>
                <form:select path="month">
                    <form:option value="0" label="--"/>
                    <form:options items="${monthMap}"/>
                </form:select>
            </td>
            <td>
                <form:select path="year">
                    <form:option value="0" label="--"/>
                    <form:options items="${yearList}"/>
                </form:select>
            </td>
            <td><span class="error"><form:errors path="birthday"/></span></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td>
                <form:select size="1" path="department" id="departmentId">
                    <form:option value="0" label="Select"/>
                    <form:options items="${departmentList}" itemLabel="name" itemValue="id"/>
                </form:select>
            </td>
            <td><span class="error"><form:errors path="department"/></span></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>

</form:form>