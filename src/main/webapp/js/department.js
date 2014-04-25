var DepartmentForm = Form.extend({
    init: function(resourceUrl, pnamespace, employees) {
        this.resourceUrl = resourceUrl;
        this.pnamespace = pnamespace;
        this.employees = employees;
        this.table = this.__createTable();
        this.table.appendTo('.DepartmentPortlet');
        this.form = this.__createForm();
        var params = {};
        params[this.pnamespace+'id'] = $.proxy(function () {
            return this.form.find('input[name="id"]').val();
        }, this);
        params[this.pnamespace+'name'] = $.proxy(function () {
            return this.form.find('input[name="name"]').val();
        }, this);
        this.form.validate({
            context: this,
//            onkeyup: false,
            rules: {
                name: {
                    required: true,
                    minlength: 3,
                    maxlength: 16,
                    remote: {
                        url: this.__resourceMap("department.do"),
                        type: "GET",
                        data: params
                    }
                }
            },
            messages: {
                name: {
                    required: "Field is required",
                    length: "Length must be more 3 and less 16 characters",
                    remote: "This name is already taken."
                }
            },
            submitHandler: $.proxy(function() {
                this.__saveData();
                this.__refreshForm();
            },this)
        });

    },
    __resourceMap: function (url) {
        var string = this.resourceUrl;
        return string + "&p_p_resource_id=" + url;
    },

    __createTable: function() {
        var table = $('<table id="departmentTable" border="1" align="center"/>');
        table.on('click', 'button.addToDepartment', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
//            this.employees.listEmployeesByDepartment(department);
        }, this));
        table.on('click', 'button.listEmployees', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.employees.listEmployeesByDepartment(department);
        }, this));
        table.on('click', 'button.deleteDepartment', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.removeDepartment(department);
        }, this));
        table.on('click', 'button.editDepartment', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            var department = tr.data('context').department;
            this.editDepartment(department);
        }, this));
        table.on('click', 'button.closeForm', $.proxy(function(e) {
            var button = $(e.target);
            var tr =button.parents('tr');
            tr.remove();
        }, this));
        return table;
    },

    __putDepartments: function () {
        this.table.children().remove();
        $.ajax({
            url: this.__resourceMap("getDepList"),
            dataType: 'json',
            context: this
        }).done(function (departments) {
            for (var i = 0; i < departments.length; i++) {
                var tr = $('<tr/>')
                    .data('context', {department: departments[i]})
                    .append($('<td/>').html(departments[i].name))
                    .append($('<td/>')
                        .append($('<button class="addToDepartment"/>').html('Add')))
                    .append($('<td/>')
                        .append($('<button class="editDepartment"/>').html('Edit')))
                    .append($('<td/>')
                        .append($('<button class="listEmployees"/>').html('Employees')))
                    .append($('<td/>')
                        .append($('<button class="deleteDepartment"/>').html('Delete')));
                this.table.append(tr);
            }
//            this.table.appendTo('.DepartmentPortlet');
        });
    },

    __createForm: function () {
        var form = $('<form id="addDepartment"/>');
        form.empty();
        form
            .append($('<table/>')
                .append($('<tr/>')
                    .append($('<td/>').html("Department Name"))
                    .append($('<td/>')
                        .append($('<input name="name"/>'))
                        .append($('<input hidden="true" name="id"/>')))
                    .append($('<input type="submit" class="submit" value="Save"/>'))));

        return form;
    },

    __putForm: function() {
        this.table
            .append($('<tr/>')
                .append($('<td/>').append(this.form))
                .append($('<td/>').append($('<button class="closeForm"/>').html("Cancel"))));

    },

    __refreshForm: function() {
        this.form.detach();
    },

    __saveData: function() {
        var url = "";
        switch(this.action) {
            case "add":
                url = "depNew";
                break;
            case "edit":
                url = "depEdit";
                break;
            default :
                break;
        }
        var params = {};
        params[this.pnamespace+'id'] = $.proxy(function () {
            return this.form.find('input[name="id"]').val();
        }, this);
        params[this.pnamespace+'name'] = $.proxy(function () {
            return this.form.find('input[name="name"]').val();
        }, this);
        $.ajax({
            type: "POST",
            url: this.__resourceMap(url),
            context: this,
            dataType: 'json',
            data: params,
            success: $.proxy(function (response) {
                if (response.length != 0) {
                    var message = "";
                    for (var i = 0; i < response.length; i++) {
                        message += response[i] + '\n';
                    }
                    alert(message);
                } else {
//                            alert("Success");
//                    this.form.empty();
                    this.action="";
                    this.__putDepartments();
                }
            }, this)
        });
    },

    showDepartments: function() {
        this.__putDepartments();
    },

    addDepartment: function() {
//        this.form.appendTo('.DepartmentPortlet');
        this.__putForm();
        this.action = "add";
    },

    editDepartment: function(department) {
//        this.form.appendTo('.DepartmentPortlet');
//        this.form = this.__createForm();
        this.form.find('input[name="id"]').attr("value", department.id);
        this.form.find('input[name="name"]').attr("value", department.name);

        this.__putForm();
        this.action = "edit";
    },

    removeDepartment: function(department) {
        $('depIsNotEmpty').remove();
        $.ajax( {
            type: "GET",
            url: "/department/getListByDepartment",
            data: {departmentId: department.id},
            context: this,
            dataType: 'json'
        }).done(function (data) {
            if(data.length != 0) {
                $('<h4 align="center" id="depIsNotEmpty"/>').html("\""+ department.name + "\" is not empty")
                    .appendTo('.DepartmentPortlet');
            } else {
                $.ajax( {
                    type: "POST",
                    url: "/department/delete",
                    context:this,
                    data: {departmentId: department.id}
                }).done(function(response) {
//                    alert(response);
                    this.__putDepartments();
                });
            }
        });
    }

});
