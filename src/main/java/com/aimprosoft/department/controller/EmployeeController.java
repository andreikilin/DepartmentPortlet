package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.model.EmployeeForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.util.DateUtil;
import com.aimprosoft.department.util.DepartmentPropertyUtil;
import com.aimprosoft.department.util.EmployeePropertyUtil;
import com.aimprosoft.department.validator.EmployeeFormValidator;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("view")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeFormValidator employeeFormValidator;

    @Autowired
    DateUtil dateUtil;

    @Autowired
    EmployeePropertyUtil employeePropertyUtil;

    @Autowired
    DepartmentPropertyUtil departmentPropertyUtil;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Employee.class, employeePropertyUtil);
        binder.registerCustomEditor(Department.class, departmentPropertyUtil);
    }

    @ResourceMapping(value = "getEmplList")
    public void getEmployeeList(ResourceRequest request, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, employeeService.list());
    }



//    @RenderMapping(params = "identifier=listEmpl")
//    public String listEmployees(ModelMap model) {
//        model.put("employeeList", employeeService.list());
//        return "listEmployees";
//    }
//
//    @RenderMapping(params = "identifier=editEmpl")
//    public String editEmployeeForm(ModelMap model, @ModelAttribute EmployeeForm employeeForm, String url,
//                                   @RequestParam("employeeId") Employee employee, RenderRequest request) {
//
//        model.put("dayList", dateUtil.getDayList());
//        model.put("monthMap", dateUtil.getMonthMap());
//        model.put("yearList", dateUtil.getYearList());
//        model.put("departmentList", departmentService.list());
//        model.put("beforeUrl", url == null ? "" : url);
//
//        Errors errors = (Errors) model.get("errors");
//        if(errors != null) {
//              model.put("org.springframework.validation.BindingResult.employeeForm", errors);
//        } else {
//            if (employee != null) {
//                employeeForm.loadEmployee(employee);
//            }
//        }
//        return "employeeFormTag";
//    }
//
//    @ActionMapping(params = "identifier=saveEmpl")
//    public void saveEmployeeForm(ActionResponse response, String beforeUrl, ModelMap model,
//                                 Integer employeeId, EmployeeForm employeeForm, BindingResult result) throws IOException {
//
//            employeeFormValidator.validate(employeeForm, result);
//            if(result.hasErrors()) {
//                model.put("errors", result);
//                response.setRenderParameter("identifier", "editEmpl");
//            }else {
//                Employee employee = employeeForm.saveEmployee();
//                if(employeeId != null && employee.getId().equals(employeeId)) {
//                    employeeService.update(employee);
//                }else {
//                    employeeService.add(employee);
//                }
//                response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
//            }
//
//    }
//
//    @ActionMapping(params = "identifier=delEmpl")
//    public void deleteEmployee(ActionResponse response, @RequestParam("employeeId") Employee employee, String beforeUrl) {
//        employeeService.delete(employee);
//        response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
//
//    }

}
