package com.gers.gers.controllers;
import com.gers.gers.models.Spares;
import com.gers.gers.service.spare.spareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class SpareController {
@Autowired
    private spareService spareService;
    @GetMapping("/new_spare")
    public String newSpares(Model model)
    {
        Spares spare = new Spares();
        model.addAttribute("spare", spare);
        return  "Admin/spares/newSpares";
    }

    @GetMapping("/all_spares")
    public String storedSpares(Model model){
        List<Spares> spares = spareService.getSpares();
       // System.out.println(spares.toString());
        model.addAttribute("spares", spares);
        return "Admin/spares/allSpare";
    }

    @GetMapping("/get_spare/{id}")
    public String getOneSpare(@PathVariable("id") Long id, Model model){
         Spares spare  =spareService.getOneSpare(id);
         model.addAttribute("spare",spare);
         return "Admin/spares/editSpare";
    }


    @PostMapping("/createSpare")
    public String StoreSpare(@ModelAttribute("spare") Spares spare, BindingResult result, RedirectAttributes redirAttrs){
        spareService.storeSpare(spare);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_spare";
        }
        redirAttrs.addFlashAttribute("message", "New Spare Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/new_spare";
    }

@PostMapping("/update_spare/{id}")
    public String updateSpare(@PathVariable Long id,@ModelAttribute("spare") Spares spares,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
    //get data

    Spares existingSpare = spareService.getOneSpare(id);
    existingSpare.setSpareName(spares.getSpareName());
    existingSpare.setSparePrice(spares.getSparePrice());
    existingSpare.setSpareQuantity(spares.getSpareQuantity());
    //update data
    spareService.updateSpare(spares);
    redirAttrs.addFlashAttribute("message", "Failed, Try Again");
    redirAttrs.addFlashAttribute("alertClass", "alert-danger");
    if (result.hasErrors()) {
        return "redirect:/all_spares";
    }
    redirAttrs.addFlashAttribute("message", " Spare Updated");
    redirAttrs.addFlashAttribute("alertClass", "alert-success");
    return "redirect:/all_spares";

}

@GetMapping("delete_spare/{id}")
public String deleteSpare(@PathVariable Long id){
        spareService.deleteSpare(id);
        return "redirect:/all_spares";

}


}
