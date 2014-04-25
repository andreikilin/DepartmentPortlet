package com.aimprosoft.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionResponse;

@Controller
@RequestMapping("VIEW")
public class MainController {


    @RenderMapping
    public String defaultRenderPage() {
        return "StartPage";
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
