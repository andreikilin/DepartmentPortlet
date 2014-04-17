package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.model.DepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.util.DepartmentPropertyUtil;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.*;

@Controller
@RequestMapping("view")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentFormValidator departmentFormValidator;

    @Autowired
    DepartmentPropertyUtil departmentPropertyUtil;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Department.class, departmentPropertyUtil);
    }

    @RenderMapping(params = "identifier=editDep")
    public String editDepartmentForm(ModelMap model, @ModelAttribute DepartmentForm departmentForm, String url, Integer departmentId) {
        model.put("beforeUrl", url == null ? "" : url);
        Errors errors = (Errors) model.get("errors");
        if(errors != null) {
            model.put("org.springframework.validation.BindingResult.departmentForm", errors);
        } else {
            if(departmentId != null) {
                departmentForm.loadDepartment(departmentService.getById(departmentId));
            }
        }
        return "departmentForm";
    }

    @RenderMapping(params = "identifier=listDep")
    public String listDepartments(ModelMap model) {
        model.put("departmentList", departmentService.list());
        return "listDepartments";
    }

    /*
    Department loaded via InitBinder
     */
    @RenderMapping(params = "identifier=addToDep")
    public String employeeToDepartment(ModelMap model, Department department, String url) {
        if (department != null ) {
            Department departmentNone = departmentService.getByName("None");
            model.put("department", department);
            model.put("employeeList", employeeService.listByDepartment(departmentNone));
            model.put("action", "Add");
            model.put("beforeUrl", url == null ? "" : url);
        }
        return "employeeToDepartment";
    }

    @RenderMapping(params = "identifier=listDepEmpl")
    public String listDepartmentEmployees(ModelMap model, @RequestParam("departmentId") Department department, String url) {
        if (department != null ) {
            model.put("department", department);
            model.put("employeeList", employeeService.listByDepartment(department));
            model.put("action", "Delete");
            model.put("beforeUrl", url == null ? "" : url);
        }
        return "employeeToDepartment";
    }

    @RenderMapping(params = "identifier=askDelDep")
    public String askDeleteDepartment(ModelMap model, @RequestParam("departmentId") Department department, String url) {
        model.put("action", "delDep");
        model.put("department", department);
        model.put("beforeUrl", url);
        model.put("isEmpty", employeeService.listByDepartment(department).isEmpty());
        return "askDeleteDepartment";
    }

    @ActionMapping(params = "identifier=emplToDep")
    public void employeeToDepartment(ActionResponse response, Integer[] employeeId, Integer departmentId,
                                     String action, String beforeUrl)  {
        Department department;
        try {
            switch (action) {
                case "Add":
                    department = departmentService.getById(departmentId);
                    break;
                case "Delete":
                    department = departmentService.getByName("None");
                    break;
                default:
                    throw new NullPointerException("No action set");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("No action set");
        }
        for (Integer anEmployeeId : employeeId) {
            Employee employee = employeeService.getById(anEmployeeId);
            employee.setDepartment(department);
            employeeService.update(employee);
        }
        response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);

    }

    @ActionMapping(params = "identifier=saveDep")
    public void saveDepartmentForm(ActionResponse response, String beforeUrl, ModelMap model,
                                   Integer departmentId, DepartmentForm departmentForm, BindingResult result) {
        departmentFormValidator.validate(departmentForm,result);
        if(result.hasErrors()) {
            model.put("errors", result);
            response.setRenderParameter("identifier", "editDep");
        } else {
            Department department = departmentForm.saveDepartment();
            if(departmentId != null && department.getId().equals(departmentId)) {
                departmentService.update(department);
            } else {
                departmentService.add(department);
            }
            response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
        }
    }

    @ActionMapping(params = "identifier=delDep")
    public void deleteDepartment(ActionResponse response, @RequestParam("departmentId") Department department, String beforeUrl) {
        departmentService.delete(department);
        response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
    }

//    @ExceptionHandler(Exception.class)
//    public String handle(Exception e, PortletRequest portletRequest) {
//        portletRequest.setAttribute("errorName", e.getClass().getSimpleName());
//        portletRequest.setAttribute("errorMessage", e.getMessage());
//        return "errorPage";
//    }

}
