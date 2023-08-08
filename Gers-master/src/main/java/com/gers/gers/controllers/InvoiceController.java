package com.gers.gers.controllers;

import com.gers.gers.models.*;
import com.gers.gers.repository.userRepository;
import com.gers.gers.service.booking.BookingService;
import com.gers.gers.service.employee.employeeService;
import com.gers.gers.service.invoice.invoiceService;
import com.gers.gers.service.sell.sellSpareService;
import com.gers.gers.service.user.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Long.sum;
import static org.apache.el.lang.ELArithmetic.add;

@Controller
public class InvoiceController {
    @Autowired
    private invoiceService  invoiceService;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private sellSpareService sellSpareService;
    @Autowired
    private userService userService;
    @GetMapping("/new_invoice")
    public String newInvoice(Model model)
    {
       List<Booking> fixed = bookingService.getFixedBookings();
       // List<Booking> bookings = bookingService.getUserBookings();
       //System.out.println("Received data: " + user);
        List<SellSpares>sales = sellSpareService.allSales();
        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);
        model.addAttribute("fixed", fixed);
        model.addAttribute("sales", sales);
        return "Admin/invoice/new";
    }

    @GetMapping("/create_invoice/{id}")
    public String crateInvoice(@PathVariable("id") Long id, Invoice invoice, Model model)
    {
        Booking booking  = bookingService.getOneBooking(id);

       Long newId =booking.getId();
       // int newId = id.intValue(booking.getId());
   System.out.println(booking);

        String firstname = userService.findUsername(booking.getUser()).getFirstname();
        String lastname = userService.findUsername(booking.getUser()).getLastname();
        String fullnames = firstname + " " + lastname;
        String phone = userService.findUsername(booking.getUser()).getPhone();

       List<SellSpares> orders = sellSpareService.findOrder(newId);
        System.out.println(orders);
        BigDecimal salesAmount = sellSpareService.totalAmount(newId.longValue());

       BigDecimal serviceAmount = booking.getAmount();
       BigDecimal totalAmount  = (BigDecimal) add(serviceAmount,salesAmount);
//        System.out.println(totalAmount);


        model.addAttribute("booking", booking);

        model.addAttribute("orders", orders);
        model.addAttribute("newId", newId);
        model.addAttribute("fullnames", fullnames);
        model.addAttribute("phone", phone);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("salesAmount", salesAmount);
        model.addAttribute("serviceAmount", serviceAmount);

        return "Admin/invoice/newInvoice";
    }



    @GetMapping("/all_invoices")
    public String storedEmployees(Model model){
        List<Invoice> invoice = invoiceService.allInvoices();
                // System.out.println(spares.toString());
        model.addAttribute("invoice", invoice);
        return "Admin/invoice/all";
    }

    @GetMapping("/get_invoice/{id}")
    public String getInvoice(@PathVariable("id") Long id, Model model){
        Invoice invoice  =invoiceService.getInvoice(id);
        model.addAttribute("invoice",invoice);
        return "Admin/invoice/edit";
    }


    @PostMapping("/sendInvoice")
    public String StoreInvoice(@ModelAttribute("invoice") Invoice invoice, BindingResult result, RedirectAttributes redirAttrs){
        invoiceService.createInvoice(invoice);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return  "redirect:/Admin";
        }
        redirAttrs.addFlashAttribute("message", "New Invoice Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/Admin";
    }

    @PostMapping("/update_invoice/{id}")
    public String updateInvoice(@PathVariable Long id,@ModelAttribute("invoice") Invoice invoice,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){
        //get data

        Invoice existingInvoice = invoiceService.getInvoice(id);
        existingInvoice.setOrderNo(invoice.getOrderNo());
        existingInvoice.setTotalAmount(invoice.getTotalAmount());

        //update data
        invoiceService.updateInvoice(invoice);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/edit";
        }
        redirAttrs.addFlashAttribute("message", " Invoice Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all";

    }

    @GetMapping("delete_invoice/{id}")
    public String deleteInvoice(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        invoiceService.deleteInvoice(id);

        return "redirect:/all";
    }


}
