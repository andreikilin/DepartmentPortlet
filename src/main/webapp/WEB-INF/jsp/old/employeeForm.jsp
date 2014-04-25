<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<portlet:defineObjects/>

<portlet:actionURL var="employeeAction">
    <portlet:param name="employeeId" value="${employeeForm.id}"/>
    <portlet:param name="identifier" value="saveEmpl"/>
    <portlet:param name="beforeUrl" value="${beforeUrl}"/>
</portlet:actionURL>


<form action="${employeeAction}" method="post">
    <table align="center">
        <tr>
            <td>First Name: </td>
            <td>
                <input name="<portlet:namespace/>firstName" value="${employeeForm.firstName}"/>
                <input hidden="hidden" name="<portlet:namespace/>id" value="${employeeForm.id}"/>

            </td>
            <c:if test="${errors['firstName'] ne null}">
                <td><p><c:out value="${errors['firstName']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td>Last Name: </td>
            <td><input name="<portlet:namespace/>lastName" value="${employeeForm.lastName}"/></td>
            <c:if test="${errors['lastName'] ne null}">
                <td><p><c:out value="${errors['lastName']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="email" name="<portlet:namespace/>email" value="${employeeForm.email}"/></td>
            <c:if test="${errors['email'] ne null}">
                <td><p><c:out value="${errors['email']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td>Identifical number: </td>
            <td><input name="<portlet:namespace/>inn" value="${employeeForm.inn}"/></td>
            <c:if test="${errors['inn'] ne null}">
                <td><p><c:out value="${errors['inn']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td>Birthday: </td>
            <td>
                <select name="<portlet:namespace/>day">
                    <option value="0">--</option>
                    <c:forEach items="${dayList}" var="day">
                        <c:choose>
                            <c:when test="${employeeForm.day eq day}">
                                <option value="${day}" selected="selected">${day}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${day}">${day}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="<portlet:namespace/>month">
                    <option value="0">--</option>
                    <c:forEach items="${monthMap}" var="month">
                        <c:choose>
                            <c:when test="${employeeForm.month eq month.key}">
                                <option value="${month.key}" selected="selected">${month.value}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${month.key}">${month.value}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="<portlet:namespace/>year">
                    <option value="0">--</option>
                    <c:forEach items="${yearList}" var="year">
                        <c:choose>
                            <c:when test="${employeeForm.year eq year}">
                                <option value="${year}" selected="selected">${year}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${year}">${year}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </td>
            <c:if test="${errors['birthday'] ne null}">
                <td><p><c:out value="${errors['birthday']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td>Department:</td>
            <td>
                    <select size="1" name="<portlet:namespace/>department" id="departmentId">
                    <option value="0">Select</option>
                    <c:forEach items="${departmentList}" var="department">
                        <c:choose>
                            <c:when test="${employeeForm.department.id eq department.id}">
                                <option value="${department.id}" selected="selected">${department.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${department.id}">${department.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <c:if test="${errors['department'] ne null}">
                <td><p><c:out value="${errors['department']}"/></p></td>
            </c:if>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>

</body>

