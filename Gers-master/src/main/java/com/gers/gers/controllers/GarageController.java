package com.gers.gers.controllers;
import com.gers.gers.models.Booking;
import com.gers.gers.models.Garage;
import com.gers.gers.service.garage.GarageService;
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
public class GarageController {

    @Autowired
    private GarageService garageService;
    @GetMapping("/new_settings")
    public String newSetting(Model model)
    {


        Garage garage = new Garage();
        model.addAttribute("garage", garage);
        return "Admin/garage/newGarage";
    }

    @GetMapping("/all_settings")
    public String showSettings(Model model){
        List<Garage> garage = garageService.showAll();
        // System.out.println(spares.toString());
        model.addAttribute("garage", garage);
        return "Admin/garage/allGarage";
    }



    @PostMapping("/createSetting")
    public String StoreSetting(@ModelAttribute("garage") Garage garage, BindingResult result, RedirectAttributes redirAttrs){
       //System.out.println(garage);
        garageService.create(garage);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_settings";
        }
        redirAttrs.addFlashAttribute("message", "Settings Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/all_settings";
    }

    @PostMapping("/update_setting/{id}")
    public String updateSettings(@PathVariable Long id,@ModelAttribute("garage") Garage garage,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data

        Garage existingGarage = garageService.getOne(id);
        existingGarage.setNumberOfServicesPerDay(garage.getNumberOfServicesPerDay());
        existingGarage.setNumberOfRepairPerEmployee(garage.getNumberOfRepairPerEmployee());

        //update data
        garageService.update(garage);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_settings";
        }
        redirAttrs.addFlashAttribute("message", " Settings Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_settings";

    }
    @GetMapping("/get_setting/{id}")
    public String getBooking(@PathVariable("id") Long id, Model model){
        Garage garage  =garageService.getOne(id);
        model.addAttribute("garage",garage);
        return "Admin/garage/editGarage";
    }


    @GetMapping("delete_setting/{id}")
    public String deleteSettings(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        garageService.delete(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_settings";
        }
        redirAttrs.addFlashAttribute("message", " Employee Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_settings";
    }

}
