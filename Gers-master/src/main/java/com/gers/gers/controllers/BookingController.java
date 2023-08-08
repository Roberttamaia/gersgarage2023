package com.gers.gers.controllers;

import com.gers.gers.models.Booking;
import com.gers.gers.models.Employee;
import com.gers.gers.repository.BookingRepo;
import com.gers.gers.repository.GarageRepo;
import com.gers.gers.repository.gerServiceRepo;
import com.gers.gers.service.employee.employeeService;
import com.gers.gers.models.userVehicle;
import com.gers.gers.service.garage.GarageService;
import com.gers.gers.service.gerservices.gerservice;
import com.gers.gers.models.gerServiceModel;
import com.gers.gers.repository.userVehicleRepo;
import com.gers.gers.service.booking.BookingService;
import  com.gers.gers.service.uservehicle.userVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(name = "/bookings")
public class BookingController {

    @Autowired
   private  GarageRepo garageRepo;
    @Autowired
    private  gerservice gerservice;

   @Autowired
    private employeeService employeeService;
    @Autowired
    private userVehicleService userVehicleService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private userVehicleRepo userVehicleRepo;

    @Autowired
    private gerServiceRepo gerServiceRepo;

    private BookingRepo bookingRepo;
    @GetMapping("/new_booking")
    public String newBooking(Authentication authentication, Model model)
    {
        String name = authentication.getName();
        System.out.println(name);
        List<userVehicle> userVehicles = userVehicleService.showUsersVehicle(name);
        System.out.println(userVehicles);
      List<gerServiceModel> serv = gerservice.showAllServices();
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("serv",serv);
      model.addAttribute("userVehicles",userVehicles);
        return "Bookings/newBooking";
    }

    @GetMapping("/all_bookings")
    public String allBookings(Model model){


        List<Booking> bookings = bookingService.getAllBookings();

        model.addAttribute("bookings",bookings);

        return "Bookings/allBookings";
    }

    @GetMapping("/all_service")
    public String allService(Model model){
        List<Booking> bookings = bookingService.getServiceBookings();
        model.addAttribute("bookings",bookings);
        return "Bookings/service";
    }

    @GetMapping("/all_fixed")
    public String allFixed(Model model){
        List<Booking> bookings = bookingService.getFixedBookings();
        model.addAttribute("bookings",bookings);
        return "Bookings/fixed";
    }
    @GetMapping("/all_scrapped")
    public String allScrapped(Model model){
        List<Booking> bookings = bookingService.getScrappedBookings();
        model.addAttribute("bookings",bookings);
        return "Bookings/scrapped";
    }

    @GetMapping("/my_bookings")
    public String myBookings(Authentication authentication,Model model){
        String username = authentication.getName();
        List<userVehicle> vehicles = userVehicleService.showUsersVehicle(username);
        List<Booking> bookings = bookingService.getUserBookings(username);
        model.addAttribute("vehicles",vehicles);
        model.addAttribute("bookings",bookings);
        // System.out.println(spares.toString());

        return "Bookings/myBookings";
    }


    @GetMapping("/get_booking/{id}")
    public String getBooking(@PathVariable("id") Long id, Model model){

        Booking booking  =bookingService.getOneBooking(id);
        model.addAttribute("booking",booking);
        return "Bookings/editBooking";
    }

    @GetMapping("/update_status/{id}")
    public String updateStatus(@PathVariable("id") Long id, Model model){

        Booking booking  =bookingService.getOneBooking(id);
        model.addAttribute("booking",booking);
        return "Bookings/status";
    }

    @GetMapping("/new_order/{id}")
    public String newOder(@PathVariable("id") Long id, Model model){
        List<Employee> employee = employeeService.showAllEmployee();
        Booking booking  =bookingService.getOneBooking(id);
        model.addAttribute("booking",booking);
        model.addAttribute("employee",employee);
        return "Bookings/newOrder";
    }


    @PostMapping("/createBooking")
    public String createBooking(@ModelAttribute("booking") Booking booking, BindingResult result, RedirectAttributes redirAttrs){
       //check current mechanic services

  int getAll = garageRepo.getService();
 System.out.println(getAll);
//
//
     int getAllBookings = bookingService.getTotalBookings();
       // System.out.println(getAllBookings);

        redirAttrs.addFlashAttribute("message", "Number of daily Bookings Exceeded, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");

       if (getAllBookings >getAll){
          // System.out.println("great");
          return  "redirect:/new_booking";


       }



        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");

        if (result.hasErrors()) {
            return  "redirect:/my_bookings";
        }
        bookingService.createNew(booking);
        redirAttrs.addFlashAttribute("message", "New Booking Added");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/my_bookings";
    }

    @PostMapping("/update_booking/{id}")
    public String updateBooking(@PathVariable Long id,@ModelAttribute("booking") Booking booking,Model model,BindingResult result,
                              RedirectAttributes redirAttrs){

        //int number = bookingService.getNo(booking.getMechanic());

        //get data
        Booking existingBooking = bookingService.getOneBooking(id);
        existingBooking.setServiceType(booking.getServiceType());
        existingBooking.setBookingDate(booking.getBookingDate());
        existingBooking.setVehicle(booking.getVehicle());
        existingBooking.setDescription(booking.getDescription());
        existingBooking.setMechanic(booking.getMechanic());
        existingBooking.setAmount(booking.getAmount());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setPaid(booking.getPaid());

        //update data
        bookingService.updateBooking(booking);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_bookings";
        }
        redirAttrs.addFlashAttribute("message", " Booking Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_bookings";

    }


    @PostMapping("/update_status/{id}")
    public String updateBookingStatus(@PathVariable Long id,@ModelAttribute("booking") Booking booking,Model model,BindingResult result,
                                RedirectAttributes redirAttrs){

       // int number = bookingService.getNo(booking.getMechanic());


        //get data
        Booking existingBooking = bookingService.getOneBooking(id);
        existingBooking.setServiceType(booking.getServiceType());
        existingBooking.setBookingDate(booking.getBookingDate());
        existingBooking.setVehicle(booking.getVehicle());
        existingBooking.setDescription(booking.getDescription());
        existingBooking.setMechanic(booking.getMechanic());
        existingBooking.setAmount(booking.getAmount());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setPaid(booking.getPaid());
        existingBooking.setOrderName(booking.getOrderName());

        //update data
        bookingService.updateBooking(booking);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_bookings";
        }
        redirAttrs.addFlashAttribute("message", " Booking Updated");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_bookings";

    }



    @PostMapping("/newOrder/{id}")
    public String newOrder(@PathVariable Long id,@ModelAttribute("booking") Booking booking,Model model,BindingResult result,
                                RedirectAttributes redirAttrs){

        Booking existingBooking = bookingService.getOneBooking(id);
        String ser =  existingBooking.getServiceType();
        int number =  gerServiceRepo.servIndex(ser);
        String mech = existingBooking.getMechanic();

       List<Booking> numberBookings = bookingService.getMech(existingBooking.getMechanic());
       int total = 0;
        for(Booking bookingz: numberBookings){
            total += gerServiceRepo.servIndex( bookingz.getServiceType());

        }
        int allS = garageRepo.getMechs();
        if (total > allS){
            redirAttrs.addFlashAttribute("message", "Failed,Mechanic Has Maximum Services, Try Again");
            redirAttrs.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/all_bookings";

        }




        existingBooking.setServiceType(booking.getServiceType());
        existingBooking.setBookingDate(booking.getBookingDate());
        existingBooking.setVehicle(booking.getVehicle());
        existingBooking.setDescription(booking.getDescription());
        existingBooking.setMechanic(booking.getMechanic());
        existingBooking.setStatus(booking.getStatus());
        existingBooking.setAmount(booking.getAmount());
        existingBooking.setPickUpDate(booking.getPickUpDate());
        existingBooking.setPaid(booking.getPaid());
        existingBooking.setOrderName(booking.getOrderName());


        //update data
        bookingService.updateBooking(booking);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_bookings";
        }
        redirAttrs.addFlashAttribute("message", " order created");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/Admin";

    }

    @GetMapping("delete_booking/{id}")
    public String deleteBooking(@PathVariable Long id,BindingResult result,RedirectAttributes redirAttrs){
        bookingService.deleteBooking(id);
        redirAttrs.addFlashAttribute("message", "Failed, Try Again");
        redirAttrs.addFlashAttribute("alertClass", "alert-danger");
        if (result.hasErrors()) {
            return "redirect:/all_bookings";
        }
        redirAttrs.addFlashAttribute("message", " Booking Deleted");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/all_bookings";
    }

}
