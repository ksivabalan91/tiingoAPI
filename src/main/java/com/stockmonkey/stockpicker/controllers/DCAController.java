package com.stockmonkey.stockpicker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stockmonkey.stockpicker.repositories.DCArepository;
import com.stockmonkey.stockpicker.services.DCAservice;


@Controller
@RequestMapping(path={"/","/index"})
public class DCAController {

    @GetMapping
    public String getIndex(){        
        System.out.println("Return index.html");
        return"index.html";
    }

    // @GetMapping("/dcaform")
    // public String getDCAform(){
    //     System.out.println("Return dcaform.html");
    //     return "dcaform.html";
    // }

    @GetMapping("/monkeyform")
    public String getMonkeyform(){
        System.out.println("Return monekeyform.html");
        return "monkeyform.html";
    }   
    
}
