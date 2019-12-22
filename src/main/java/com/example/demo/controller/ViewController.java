package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class ViewController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }
}
