package com.stockmonkey.stockpicker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stockmonkey.stockpicker.repositories.DCArepository;
import com.stockmonkey.stockpicker.services.DCAservice;

@Controller
@RequestMapping(path="/dcaform")
public class DCAformController {

    @Autowired
    private DCAservice dcaSvc;
    @Autowired
    private DCArepository dcaRepo;

    @PostMapping(path="/fill")
    public String postDCAform(@RequestParam MultiValueMap<String,String> form, Model model){        

        String ticker = form.getFirst("ticker");
        String start = form.getFirst("startDate");
        String end = form.getFirst("endDate");
        int investInterval = Integer.parseInt(form.getFirst("investInterval"));
        double initialAmount = Double.parseDouble(form.getFirst("initialAmount"));
        double investAmount = Double.parseDouble(form.getFirst("investAmount"));
        System.out.println("POST computeDCA");
        dcaSvc.computeDCA(ticker, start, end, investInterval, investAmount, initialAmount);

        System.out.println("Controller add model attributes");
        model.addAttribute("dcaList",dcaRepo.getAll());

        System.out.println("Return dcaform2");
     
        return"dcaform2";
    }

    // @DeleteMapping(path = "/fill/delete")
    // public String delete

    
}
