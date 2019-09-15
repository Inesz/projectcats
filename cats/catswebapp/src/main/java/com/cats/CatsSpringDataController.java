package com.cats;

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
@RequestMapping("/sd")
public class CatsSpringDataController {

    @Autowired
    private SpringDataDAO catsDao;

    @RequestMapping("")
    public String indexView(Model model) {
        return "index";
    }

    //start:POST-REDIRECT-GET
    @RequestMapping(value="/cats", method={RequestMethod.POST})
    public String catListView(Model model, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result){

        if (!result.hasErrors()) {
            catsDao.save(new Cat(catDTO.getName()));
            return "redirect:cats";
        }else {
            /** find better solution */
            model.addAttribute("catsList", catsDao.findAll());
            return "catsList";
        }
    }

    @RequestMapping(value="/cats", method={RequestMethod.GET})
    public String doGet(Model model ){
        model.addAttribute("catsList", catsDao.findAll());
        model.addAttribute("catDTO", new CatDTO());
        return "catsList";
    }
    //end:POST-REDIRECT-GET

    @RequestMapping("/remove-{name}")
    public String catRemove(Model model, @PathVariable("name") String name) {
        catsDao.delete(catsDao.findByName(name).get());
        return "redirect:cats";
    }
}