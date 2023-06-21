package com.forex.controllers;

import com.forex.entities.Subscription;
import com.forex.entities.User;
import com.forex.services.PacketService;
import com.forex.services.SendMailService;
import com.forex.services.SubscriptionService;
import com.forex.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private PacketService packetService;

    @GetMapping("/new")
    public String newSub(Model model, Principal principal){
        String username = principal.getName();
        Subscription subscription = userService.getUserByUsername(username).getCurrentSubscription();
        if(subscription!=null)
            return "redirect:cancel";
        subscription = new Subscription();
        model.addAttribute("subscription",subscription);
        model.addAttribute("packets",packetService.findAll());
        return "subscription/new";
    }
    @PostMapping("create_new")
    public String createNew(Subscription subscription,  Model model,Principal principal){
        String username = principal.getName();
        Subscription subscription1 = userService.getUserByUsername(username).getCurrentSubscription();
        if(subscription1!=null)
            return "redirect:cancel";
        subscription.setUser(userService.getUserByUsername(principal.getName()));
        subscription.setValue(subscription.getPacket().getPrice());
        subscriptionService.save(subscription);
        return "redirect:../home/index";
    }

    @GetMapping("/cancel")
    public String cancelSub(Model model,Principal principal){
        String username = principal.getName();
        Subscription subscription = userService.getUserByUsername(username).getCurrentSubscription();
        if(subscription==null)
            return "redirect:new";
        model.addAttribute("subscription",subscription);
        return "subscription/cancel";
    }

    @GetMapping("/real_cancel")
    public String realCancelSub(Model model,Principal principal){
        String username = principal.getName();
        Subscription subscription = userService.getUserByUsername(username).getCurrentSubscription();
        if(subscription!=null){
            subscription.setIscanceled(true);
            subscriptionService.save(subscription);
        }
        return "redirect:new";
    }

}
