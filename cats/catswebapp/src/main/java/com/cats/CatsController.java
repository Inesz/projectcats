package com.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatsController {

    //do obiektów typu DAO dodaj @Component, bez tego nie działa, tylko beany działają bez tego.
    //@Autowired(required = false)
    @Autowired
    private CatsDAO catsDao;

    @RequestMapping("/")
    public String indexView(Model model) {
        return "index";
    }

    //start:POST-REDIRECT-GET
    @RequestMapping(value="/cats", method={RequestMethod.POST})
    public String catListView(Model model, @RequestParam(value = "name", required = false, defaultValue = "0") String name) {

        if (!name.equals("0")){
            catsDao.addCat(name);
        }

        return "redirect:cats";
    }

    @RequestMapping(value="/cats", method={RequestMethod.GET})
    public String doGet(Model model ){
        model.addAttribute("catsList", catsDao.getCatsList());
        return "catsList";
    }

    //end:POST-REDIRECT-GET

}