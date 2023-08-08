package com.gers.gers.controllers;


import com.gers.gers.models.Booking;
import com.gers.gers.models.Employee;
import com.gers.gers.models.SellSpares;
import com.gers.gers.models.userInfo;
import com.gers.gers.repository.*;
import com.gers.gers.service.employee.employeeService;
import com.gers.gers.service.sell.sellSpareService;
import com.gers.gers.service.booking.BookingService;
import com.gers.gers.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller

public class HomeController {
    @Autowired
   private BookingService bookingService;

    @Autowired
    private  sellSpareService sellSpareService;

    @Autowired
    private employeeService employeeService;

    @Autowired
    private userService userService;

    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private SellSpareRepo sellSpareRepo;

@Autowired
    private EmployeeRepo employeeRepo;
    @GetMapping("/")
    public String showLandingPage()
    {
        return "index";
    }


    @GetMapping("/Admin")
    public  String ShowAdmin(Model model)
    {
       long number = employeeService.allEmployees();
        List<SellSpares> sales = sellSpareService.allSales();
       List<Booking> book = bookingService.getNewBookings();
        List<Booking> fixed = bookingService.getFixedBookings();
        int bookingTotal = bookingService.getTotalBookings();
        BigDecimal totalAmount = invoiceRepo.amountTotal();
        int totalSpares = sellSpareRepo.allSpares();



      model.addAttribute("book",book);
      model.addAttribute("sales",sales);
      model.addAttribute("number",number);
      model.addAttribute("fixed",fixed);
      model.addAttribute("bookingTotal",bookingTotal);
      model.addAttribute("totalAmount",totalAmount);
      model.addAttribute("totalSpares",totalSpares);

        return "Admin/index";
    }

    @GetMapping("/all_users")
    public  String ShowClients(Model model)
    {
       List<userInfo> userInfo = userService.showAll();
        model.addAttribute("userInfo",userInfo);
        return "Admin/users/all";
    }




}

