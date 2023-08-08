package com.gers.gers.controllers;

import com.gers.gers.models.Booking;
import com.gers.gers.models.userInfo;
import com.gers.gers.service.booking.BookingService;
import  com.gers.gers.service.spare.spareService;
import com.gers.gers.models.SellSpares;
import com.gers.gers.models.Spares;
import com.gers.gers.service.sell.sellSpareService;
import com.gers.gers.service.user.userService;
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
public class SaleController {
    @Autowired
    private sellSpareService sellSpareService;

    @Autowired
    private  spareService spareService;

    @Autowired
    private BookingService bookingService;
    @Autowired
    private userService userService;
    @GetMapping("/new_sale")
    public String newSale(Model model)
    {
        List<Booking> bookings = bookingService.getAllBookings();
        List<userInfo> users = userService.showAll();
        List<Spares> spares = spareService.getSpares();
        SellSpares sellSpares = new SellSpares();

        model.addAttribute("sellSpares", sellSpares);
        model.addAttribute("spares", spares);
        model.addAttribute("users",users);
        model.addAttribute("bookings",bookings);
        return "Admin/sales/newSale";
    }

    @GetMapping("/all_sale")
    public String allSales(Model model){
        List<SellSpares> sellSpares = sellSpareService.allSales();
        // System.out.println(spares.toString());
        model.addAttribute("sellSpares", sellSpares);
        return "Admin/sales/allSales";
    }

    @GetMapping("/get_sales/{id}")
    public String getSale(@PathVariable("id") Long id, Model model){
        SellSpares sellSpares  =sellSpareService.getOne(id);
        model.addAttribute("sellSpares",sellSpares);
        return "Admin/sales/editSale";
    }

    @PostMapping("/createSale")
    public String StoreSale(@ModelAttribute("sellSpares") SellSpares sellSpares, BindingResult result, RedirectAttributes redirAttrs){
        sellSpareService.createNew(sellSpares);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/new_sale";
        }
        redirAttrs.addFlashAttribute("message", "New Sales Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/all_sale";
    }

    @PostMapping("/update_sales/{id}")
    public String updateSpare(@PathVariable Long id,@ModelAttribute("sellSpares") SellSpares sellSpares,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data

        SellSpares existingSale = sellSpareService.getOne(id);
        existingSale.setUser(sellSpares.getUser());
        existingSale.setSpare(sellSpares.getSpare());
        existingSale.setAmount(sellSpares.getAmount());
        existingSale.setOrders(sellSpares.getOrders());

        //update data
        sellSpareService.updateSale(sellSpares);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_sales";
        }
        redirAttrs.addFlashAttribute("message", " Sale Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_sales";

    }

    @GetMapping("delete_sale/{id}")
    public String deleteSpare(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        sellSpareService.deleteSale(id);
//        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
//        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
//        if (result.hasErrors()) {
//            return "redirect:/all_employees";
//        }
//        redirAttrs.addFlashAttribute("message", " Employee Deleted");
//        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_sales";
    }

}
