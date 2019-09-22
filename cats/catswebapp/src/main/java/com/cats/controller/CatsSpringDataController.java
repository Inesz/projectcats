package com.cats.controller;

import com.cats.Cat;
import com.cats.DTO.CatDTO;
import com.cats.service.CatsCRUDService;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@Transactional
@RequestMapping("/")
public class CatsSpringDataController {
    private static Logger LOGGER = LoggerFactory.getLogger(CatsSpringDataController.class);

    @Autowired
    private CatsCRUDService catsCRUDService;

    @RequestMapping("")
    public String indexView(Model model) {
        return "index";
    }

    //start:POST-REDIRECT-GET
    @RequestMapping(value="/cats", method={RequestMethod.POST})
    public String catListView(Model model, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result){

        if (!result.hasErrors()) {
            catsCRUDService.insertCat(new Cat(catDTO.getName()));
            return "redirect:cats";
        }else {
            // TODO find better solution
            model.addAttribute("catsList", catsCRUDService.selectCats());
            return "catsList";
        }
    }

    @RequestMapping(value="/cats", method={RequestMethod.GET})
    public String doGet(Model model ){
        model.addAttribute("catsList", catsCRUDService.selectCats());
        model.addAttribute("catDTO", new CatDTO());
        return "catsList";
    }
    //end:POST-REDIRECT-GET

    @RequestMapping("/remove-{name}")
    public String catRemove(Model model, @PathVariable("name") String name) {
        catsCRUDService.deleteCat(catsCRUDService.selectCat(name));
        return "redirect:cats";
    }
}