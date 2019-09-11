package com.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String catListView(Model model, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result){

        if (!result.hasErrors()) {
            catsDao.addCat(catDTO.getName());
            return "redirect:cats";
        }else {
            /** find better solution */
            model.addAttribute("catsList", catsDao.getCatsList());
            return "catsList";
        }
    }

    @RequestMapping(value="/cats", method={RequestMethod.GET})
    public String doGet(Model model ){
        model.addAttribute("catsList", catsDao.getCatsList());
        model.addAttribute("catDTO", new CatDTO());
        return "catsList";
    }
    //end:POST-REDIRECT-GET

    @RequestMapping("/remove-{name}")
    public String catRemove(Model model, @PathVariable("name") String name) {
        catsDao.removeCat(name);
        return "redirect:cats";
    }
}