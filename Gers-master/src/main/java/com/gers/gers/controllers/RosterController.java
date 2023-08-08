package com.gers.gers.controllers;

import com.gers.gers.models.Booking;
import com.gers.gers.repository.BookingRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class RosterController {



    @Autowired
    private BookingRepo bookingRepo;
    @GetMapping("/roster")
    public String newRoster(Model model,Booking booking)
    {
        Booking roster = new Booking();
        model.addAttribute("roster",roster);
        return "Admin/roster/Roster";
    }

    @GetMapping("/get_roster")
    public String getRoster(@RequestParam("bookingDate") LocalDate bookingDate, Model model)
    {

           List<Booking> roster =  bookingRepo.getRoster(bookingDate);

        model.addAttribute("roster",roster);
        return "Admin/roster/Roster";
    }




}
