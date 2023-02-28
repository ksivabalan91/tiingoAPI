package com.stockmonkey.stockpicker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path={"/","/index"})
public class DCAController {

    @GetMapping
    public String getIndex(){        
        System.out.println("Return index");
        return"index";
    }

    @GetMapping("/dcaform")
    public String getDCAform(){
        System.out.println("Return dcaform");
        return "dcaform";
    }

    @GetMapping("/monkeyform")
    public String getMonkeyform(){
        System.out.println("Return monekeyform");
        return "monkeyform";
    }   
    
}
