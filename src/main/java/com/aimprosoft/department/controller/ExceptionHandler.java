package com.aimprosoft.department.controller;

import org.springframework.core.Ordered;
import org.springframework.web.portlet.HandlerExceptionResolver;
import org.springframework.web.portlet.ModelAndView;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;


public class ExceptionHandler implements HandlerExceptionResolver, Ordered {
    @Override
    public ModelAndView resolveException(RenderRequest request, RenderResponse response, Object o, Exception e) {
        ModelAndView view = new ModelAndView("errorPage");
        view.addObject("errorName", e.getClass().getSimpleName());
        view.addObject("errorMessage", e.getMessage());
        return view;
    }

    @Override
    public ModelAndView resolveException(ResourceRequest resourceRequest, ResourceResponse resourceResponse, Object o, Exception e) {
        return null;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
