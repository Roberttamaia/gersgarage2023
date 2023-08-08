package com.gers.gers.controllers;


import com.gers.gers.models.Vehicle;
import com.gers.gers.service.vehicle.vehicleService;
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
public class VehicleController {
    @Autowired
    private vehicleService vehicleService;
    @GetMapping("/new_vehicle")
    public String newVehicle(Model model)
    {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "Admin/vehicles/newVehicle";
    }

    @GetMapping("/all_vehicles")
    public String showVehicles(Model model){
        List<Vehicle> vehicle = vehicleService.showAllVehicles();
        // System.out.println(spares.toString());
        model.addAttribute("vehicle", vehicle);
        return "Admin/vehicles/allVehicles";
    }

    @GetMapping("/get_vehicle/{id}")
    public String getVehicle(@PathVariable("id") Long id, Model model){
        Vehicle vehicle  =vehicleService.getOneVehicle(id);
        model.addAttribute("vehicle",vehicle);
        return "Admin/vehicles/editVehicle";
    }


    @PostMapping("/createVehicle")
    public String StoreVehicle(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, RedirectAttributes redirAttrs){
       vehicleService.createNew(vehicle);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_vehicle";
        }
        redirAttrs.addFlashAttribute("message", "New Vehicle Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/new_vehicle";
    }

    @PostMapping("/update_vehicle/{id}")
    public String updateVehicle(@PathVariable Long id,@ModelAttribute("vehicle") Vehicle vehicle,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data

        Vehicle existingVehicle = vehicleService.getOneVehicle(id);
        existingVehicle.setVehicleName(vehicle.getVehicleName());
        //update data
        vehicleService.updateVehicle(vehicle);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_vehicle";
        }
        redirAttrs.addFlashAttribute("message", " Vehicle Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_vehicle";

    }

    @GetMapping("delete_vehicle/{id}")
    public String deleteSpare(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        vehicleService.deleteVehicle(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_vehicles";
        }
        redirAttrs.addFlashAttribute("message", " Vehicle Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_vehicles";
    }

}
