var PageController = Class.extend({
    init: function(resourceUrl, pnamespace) {
        this.resourceUrl = resourceUrl;
        this.pnamespace = pnamespace;
        $('.DepartmentPortlet')
            .append($('<h2/>').html("Department management"))
            .append($('<div id="actions"/>'));

        var employees = new EmployeeForm(this.resourceUrl, this.pnamespace);
        var departments = new DepartmentForm(this.resourceUrl, this.pnamespace, employees);

        var actions = $('#actions');
        actions.on('click', 'button.viewDepartments', function() {
            departments.showDepartments();
        });
        actions.on('click', 'button.viewAddDepartment', function (){
            departments.addDepartment();
        });
        actions.on('click', 'button.viewAddEmployee', function() {
            employees.addEmployee();
        });
        actions.on('click', 'button.viewAllEmployees', function() {
            employees.listEmployees();
        });

        $(actions)
            .append($('<button class="viewDepartments"/>').html("List departments"))
            .append($('<button class="viewAddDepartment"/>').html("Add department"))
            .append($('<button class="viewAddEmployee"/>').html("Add employee"))
            .append($('<button class="viewAllEmployees"/>').html("All employees"));

    }

});
