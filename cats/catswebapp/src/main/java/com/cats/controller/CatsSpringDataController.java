package com.cats.controller;

import com.cats.Cat;
import com.cats.DTO.CatDTO;
import com.cats.DAO.SpringDataDAO;
import com.cats.service.CatsCRUDService;
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
//@EnableJpaRepositories("com.cats")
//@EnableJpaRepositories(transactionManagerRef="entityManagerFactory")
@RequestMapping("/")
public class CatsSpringDataController {
    private static Logger LOGGER = LoggerFactory.getLogger(CatsSpringDataController.class);
    private static org.apache.logging.log4j.Logger Log = org.apache.logging.log4j.LogManager.getLogger(CatsSpringDataController.class);

    @Autowired
    private CatsCRUDService catsCRUDService;

    @RequestMapping("")
    public String indexView(Model model) {
        LOGGER.info("Java Logging with SLF4J info test");
        LOGGER.debug("Java Logging with SLF4J debug test");
        Cat cat = new Cat("TestCat");
        LOGGER.error("Java Logging with SLF4J error test", cat);
        Log.info("Java Logging with log4j info test");
        Log.debug("Java Logging with log4j debug test");
        Log.error("Java Logging with log4j error test", cat);
        
        return "index";
    }

    //start:POST-REDIRECT-GET
    @RequestMapping(value="/cats", method={RequestMethod.POST})
    public String catListView(Model model, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result){

        if (!result.hasErrors()) {
            catsCRUDService.insertCat(new Cat(catDTO.getName()));
            return "redirect:cats";
        }else {
            /** find better solution */
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