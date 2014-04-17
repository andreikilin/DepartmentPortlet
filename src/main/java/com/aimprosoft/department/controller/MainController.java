package com.aimprosoft.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("VIEW")
public class MainController {


    @RenderMapping
    public String defaultRenderPage() {
        return "index";
    }

    @ActionMapping
    public void defaultActionPage(ActionResponse response) {
        response.setRenderParameter("identifier", "");
    }

//    @RenderMapping(params = "identifier=error")
//    public String errorPage(RenderRequest request, RenderResponse response) {
//        request.setAttribute("errorTrace", request.getParameter("errorMessage"));
//        return "errorPage";
//    }
}
