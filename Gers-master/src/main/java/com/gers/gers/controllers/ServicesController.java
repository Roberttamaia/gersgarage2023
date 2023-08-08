package com.gers.gers.controllers;

import com.gers.gers.models.Employee;
import com.gers.gers.models.gerServiceModel;
import com.gers.gers.service.gerservices.gerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ServicesController {

    @Autowired
    private gerservice gerservice;
    @GetMapping("/new_service")
    public String newService(Model model)
    {
        gerServiceModel service = new gerServiceModel();
        model.addAttribute("service", service);
        return "Admin/services/newService";
    }

    @GetMapping("/all_services")
    public String showServices(Model model){
        List<gerServiceModel> service = gerservice.showAllServices();
        // System.out.println(spares.toString());
        model.addAttribute("service", service);
        return "Admin/services/allServices";
    }

    @GetMapping("/get_service/{id}")
    public String getServices(@PathVariable("id") Long id, Model model){
        gerServiceModel service  =gerservice.getService(id);
        model.addAttribute("service",service);
        return "Admin/services/editService";
    }


    @PostMapping("/createService")
    public String StoreService(@ModelAttribute("service") gerServiceModel service, BindingResult result, RedirectAttributes redirAttrs){
        gerservice.createService(service);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_service";
        }
        redirAttrs.addFlashAttribute("message", "New Service Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/new_service";
    }

    @PostMapping("/update_service/{id}")
    public String updateService(@PathVariable Long id,@ModelAttribute("service") gerServiceModel service,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data
        gerServiceModel existingService = gerservice.getService(id);
        existingService.setServiceName(service.getServiceName());
        existingService.setServicePrice(service.getServicePrice());
        existingService.setServiceIndex(service.getServiceIndex());

        //update data
        gerservice.updateService(service);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_services";
        }
        redirAttrs.addFlashAttribute("message", " Service Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_services";

    }

    @GetMapping("delete_service/{id}")
    public String deleteService(@PathVariable Long id,@ModelAttribute("service") gerServiceModel service,BindingResult result,RedirectAttributes redirAttrs){
        gerservice.deleteService(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_services";
        }
        redirAttrs.addFlashAttribute("message", " Service Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_services";
    }

}
