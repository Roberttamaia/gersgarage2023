package com.gers.gers.controllers;
import com.gers.gers.models.Booking;
import com.gers.gers.models.userInfo;
import com.gers.gers.models.userVehicle;
import com.gers.gers.service.booking.BookingService;
import com.gers.gers.service.user.userService;
import com.gers.gers.service.uservehicle.userVehicleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private userService userService;

    @Autowired
    private userVehicleService userVehicleService;

    @Autowired
    private BookingService bookingService;
    @GetMapping("/forget")
    public String Forgot()
    {
        return "Auth/forgot-password";
    }

    @GetMapping("/user")
    public String User(Authentication authentication, Model model)
    {
        String username = authentication.getName();
        List<userVehicle> vehicles = userVehicleService.showUsersVehicle(username);
        List<Booking> bookings = bookingService.getUserBookings(username);
        List<Booking> repair =bookingService.getLastRepair(username);
        model.addAttribute("vehicles",vehicles);
        model.addAttribute("bookings",bookings);
        model.addAttribute("repair",repair);
        //model.addAttribute("userInfo",userInfo);
        return "User/index";
    }
    @GetMapping("/login")
    public String Login()
    {
        return "Admin/login";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Admin"));
        }
        else if(role.contains("ROLE_USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
        }
    }
    @GetMapping("/register")
    public String Registeri(Model model)
    {
        userInfo userInfo = new userInfo();
        model.addAttribute("userInfo" , userInfo);
        return "Admin/register";
    }
    @PostMapping("/registerUser")
    public String createUser(@RequestBody @ModelAttribute("userInfo") userInfo userinfo, BindingResult result, RedirectAttributes redirAttrs){


//
//        Optional<userInfo> usernameEntry = Optional.ofNullable(userService.findUsername(userinfo.getUsername()));
//        if (usernameEntry.isPresent()){
//            redirAttrs.addFlashAttribute("message", "Username used, Try Again");
//            redirAttrs.addFlashAttribute("alertClass", "alert-danger");
//            return  "redirect:/register";
//
//        }
        userService.createUser(userinfo);
        redirAttrs.addFlashAttribute("message", "Registration Successfuly..Kindly Login");
        redirAttrs.addFlashAttribute("alertClass", "alert-success");
        return  "redirect:/login";




    }

}
