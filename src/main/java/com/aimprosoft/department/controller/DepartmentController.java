package com.aimprosoft.department.controller;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.model.DepartmentForm;
import com.aimprosoft.department.service.DepartmentService;
import com.aimprosoft.department.service.EmployeeService;
import com.aimprosoft.department.util.DepartmentPropertyUtil;
import com.aimprosoft.department.validator.DepartmentFormValidator;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

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

    @ResourceMapping(value = "getDepList")
    public void getDepartmentList(ResourceRequest request, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, departmentService.list());

    }
    @ResourceMapping(value = "getEmplByDepList")
    public void getEmployeeByDepartmentList(@RequestParam("departmentId") Department department, ResourceRequest request, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, employeeService.listByDepartment(department));
    }

    @ResourceMapping(value = "department.do")
    public void validateDepartment(Integer id, String name, ResourceRequest request, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        if (id != null && id != 0) {
            Department depEdit = departmentService.getById(id);
            if (depEdit != null && depEdit.equals(departmentService.getByName(name))) {
                mapper.writeValue(out, true);
            }
        } else {
            mapper.writeValue(out, departmentService.getByName(name) == null);
        }
    }

    @ResourceMapping(value = "depNew")
    public void addDepartment(DepartmentForm departmentForm, BindingResult result, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        departmentFormValidator.validate(departmentForm,result);
        List<String> responseList = new LinkedList<>();
        if(result.hasErrors()) {
            responseList.add("Validation error:");
            for(int i = 0; i<result.getErrorCount(); i++) {
                FieldError error = (FieldError) result.getAllErrors().get(i);
                responseList.add(error.getField() + ": " + error.getDefaultMessage());
            }
            mapper.writeValue(out, responseList);
        }
        Department department = departmentForm.saveDepartment();
//        try{
            departmentService.add(department);
//        }catch (Exception e) {
//            logger.error(e.toString());
//            response.add("Service error:");
//            response.add(e.getMessage());
//            return response;
//        }
        mapper.writeValue(out, responseList);
    }

    @ResourceMapping(value = "depEdit")
    public void editDepartment(DepartmentForm departmentForm, BindingResult result, ResourceResponse response) throws IOException {
        OutputStream out = response.getPortletOutputStream();
        ObjectMapper mapper = new ObjectMapper();

        List<String> responseList = new LinkedList<>();
        Integer id = departmentForm.getId();
        if(id != null && id != 0 && departmentService.getById(id) != null) {
            Department department = departmentService.getById(id);
            departmentFormValidator.validate(departmentForm,result);

            if(result.hasErrors()) {
                responseList.add("Validation error:");
                for(int i = 0; i<result.getErrorCount(); i++) {
                    FieldError error = (FieldError) result.getAllErrors().get(i);
                    responseList.add(error.getField() + ": " + error.getDefaultMessage());
                }
                mapper.writeValue(out, responseList);
            }
            department = departmentForm.updateDepartment(department);
//            try{
                departmentService.update(department);
//            }catch (Exception e) {
//                logger.error(e.toString());
//                response.add("Service error:");
//                response.add(e.getMessage());
//                return responseList;
//            }
            mapper.writeValue(out, responseList);
        }
        responseList.add("Invalid request");
        mapper.writeValue(out, responseList);
    }

    @ResourceMapping(value = "depDelete")
    public void deleteDepartment(@RequestParam("departmentId") Department department, ResourceRequest request, ResourceResponse response) {

    }

//    @RenderMapping(params = "identifier=editDep")
//    public String editDepartmentForm(ModelMap model, @ModelAttribute DepartmentForm departmentForm, String url, Integer departmentId) {
//        model.put("beforeUrl", url == null ? "" : url);
//        Errors errors = (Errors) model.get("errors");
//        if(errors != null) {
//            model.put("org.springframework.validation.BindingResult.departmentForm", errors);
//        } else {
//            if(departmentId != null) {
//                departmentForm.loadDepartment(departmentService.getById(departmentId));
//            }
//        }
//        return "departmentForm";
//    }
//
//    @RenderMapping(params = "identifier=listDep")
//    public String listDepartments(ModelMap model) {
//        model.put("departmentList", departmentService.list());
//        return "listDepartments";
//    }
//
//    @RenderMapping(params = "identifier=addToDep")
//    public String employeeToDepartment(ModelMap model, @RequestParam("departmentId") Department department, String url) {
//        if (department != null ) {
//            Department departmentNone = departmentService.getByName("None");
//            model.put("department", department);
//            model.put("employeeList", employeeService.listByDepartment(departmentNone));
//            model.put("action", "Add");
//            model.put("beforeUrl", url == null ? "" : url);
//        }
//        return "employeeToDepartment";
//    }
//
//    @RenderMapping(params = "identifier=listDepEmpl")
//    public String listDepartmentEmployees(ModelMap model, @RequestParam("departmentId") Department department, String url) {
//        if (department != null ) {
//            model.put("department", department);
//            model.put("employeeList", employeeService.listByDepartment(department));
//            model.put("action", "Delete");
//            model.put("beforeUrl", url == null ? "" : url);
//        }
//        return "employeeToDepartment";
//    }
//
//    @RenderMapping(params = "identifier=askDelDep")
//    public String askDeleteDepartment(ModelMap model, @RequestParam("departmentId") Department department, String url) {
//        model.put("action", "delDep");
//        model.put("department", department);
//        model.put("beforeUrl", url);
//        model.put("isEmpty", employeeService.listByDepartment(department).isEmpty());
//        return "askDeleteDepartment";
//    }
//
//    @ActionMapping(params = "identifier=emplToDep")
//    public void employeeToDepartment(ActionResponse response, Integer[] employeeId, Integer departmentId,
//                                     String action, String beforeUrl)  {
//        Department department;
//        try {
//            switch (action) {
//                case "Add":
//                    department = departmentService.getById(departmentId);
//                    break;
//                case "Delete":
//                    department = departmentService.getByName("None");
//                    break;
//                default:
//                    throw new NullPointerException("No action set");
//            }
//        } catch (NullPointerException e) {
//            throw new NullPointerException("No action set");
//        }
//        for (Integer anEmployeeId : employeeId) {
//            Employee employee = employeeService.getById(anEmployeeId);
//            employee.setDepartment(department);
//            employeeService.update(employee);
//        }
//        response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
//
//    }
//
//    @ActionMapping(params = "identifier=saveDep")
//    public void saveDepartmentForm(ActionResponse response, String beforeUrl, ModelMap model,
//                                   Integer departmentId, DepartmentForm departmentForm, BindingResult result) {
//        departmentFormValidator.validate(departmentForm,result);
//        if(result.hasErrors()) {
//            model.put("errors", result);
//            response.setRenderParameter("identifier", "editDep");
//        } else {
//            Department department = departmentForm.saveDepartment();
//            if(departmentId != null && department.getId().equals(departmentId)) {
//                departmentService.update(department);
//            } else {
//                departmentService.add(department);
//            }
//            response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
//        }
//    }
//
//    @ActionMapping(params = "identifier=delDep")
//    public void deleteDepartment(ActionResponse response, @RequestParam("departmentId") Department department, String beforeUrl) {
//        departmentService.delete(department);
//        response.setRenderParameter("identifier", beforeUrl == null ? "" : beforeUrl);
//    }

//    @ExceptionHandler(Exception.class)
//    public String handle(Exception e, PortletRequest portletRequest) {
//        portletRequest.setAttribute("errorName", e.getClass().getSimpleName());
//        portletRequest.setAttribute("errorMessage", e.getMessage());
//        return "errorPage";
//    }

}
