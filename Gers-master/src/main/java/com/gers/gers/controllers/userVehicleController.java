package com.gers.gers.controllers;

import com.gers.gers.models.Vehicle;
import com.gers.gers.models.userVehicle;
import  com.gers.gers.service.user.userService;
import com.gers.gers.service.vehicle.vehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.gers.gers.service.uservehicle.userVehicleService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class userVehicleController
{
    @Autowired
    private  userVehicleService userVehicleService;

    private userService userService;
    @Autowired
    private vehicleService vehicleService;
    @GetMapping("/new_user_vehicle")
    public String newUserVehicle(Authentication authentication, Model model)
    {
        String name = authentication.getName();
       // userService.findUserId();
        List<Vehicle> vehicle = vehicleService.showAllVehicles();
        // System.out.println(spares.toString());
        userVehicle userVehicle = new userVehicle();
        model.addAttribute("userVehicle", userVehicle);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("name",name);
        return "User/vehicles/newUserVehicle";
    }

    @GetMapping("/all_user_vehicles")
    public String showUserVehicles(Authentication authentication,Model model){
        String name = authentication.getName();
        List<userVehicle> userVehicle = userVehicleService.showUsersVehicle(name);
        // System.out.println(spares.toString());
        model.addAttribute("userVehicle", userVehicle);
        return "User/vehicles/allUserVehicle";
    }

    @GetMapping("/get_user_vehicle/{id}")
    public String getVehicle(@PathVariable("id") Long id, Model model){
        userVehicle userVehicle  =userVehicleService.getVehicle(id);
        model.addAttribute("userVehicle",userVehicle);
        return "User/vehicles/editUserVehicle";
    }


    @PostMapping("/createUserVehicle")
    public String StoreVehicle(@ModelAttribute("userVehicle") userVehicle userVehicle, BindingResult result, RedirectAttributes redirAttrs){

        userVehicleService.createNew(userVehicle);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/all_user_vehicles";
        }
        redirAttrs.addFlashAttribute("message", "New Vehicle Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/all_user_vehicles";
    }

    @PostMapping("/update_user_vehicle/{id}")
    public String updateVehicle(@PathVariable Long id,@ModelAttribute("userVehicle") userVehicle userVehicle,Model model,BindingResult result,
                                RedirectAttributes redirAttrs){
        //get data
        userVehicle existingVehicle = userVehicleService.getVehicle(id);
        existingVehicle.setVehicleType(userVehicle.getVehicleType());
        existingVehicle.setVehiclePlate(userVehicle.getVehiclePlate());
        existingVehicle.setVehicleMake(userVehicle.getVehicleMake());
        existingVehicle.setVehicleEngine(userVehicle.getVehicleEngine());
        existingVehicle.setVehicleColor(userVehicle.getVehicleColor());
        //update data
        userVehicleService.updateVehicle(userVehicle);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_user_vehicles";
        }
        redirAttrs.addFlashAttribute("message", " Vehicle Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_user_vehicles";

    }

    @GetMapping("delete_user_vehicle/{id}")
    public String deleteVehicle(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        userVehicleService.deleteVehicle(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_user_vehicles";
        }
        redirAttrs.addFlashAttribute("message", " Vehicle Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_user_vehicles";
    }
}
