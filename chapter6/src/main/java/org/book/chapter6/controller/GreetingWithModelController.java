package org.book.chapter6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingWithModelController {

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greetingMap/{name1}/{name2}")
    public String greetingMap(@PathVariable(name = "name1") String name1,
                              @PathVariable(name = "name2") String name2,
                              ModelMap map) {
        map.addAttribute("name1");
        map.addAttribute("name");
        return "greetingMap";
    }

    @GetMapping("/greetingMV/{firstName}/{secondName}")
    public ModelAndView greetingModelView(@PathVariable(name = "firstName") String firstName,
                                          @PathVariable(name = "secondName") String secondName) {
        Map<String, String> model = new HashMap<>();
        model.put("first", firstName);
        model.put("second", secondName);
        return new ModelAndView("greetingMV", model);
    }

}
