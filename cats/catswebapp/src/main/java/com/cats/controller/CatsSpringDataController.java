package com.cats.controller;

import com.cats.Cat;
import com.cats.DTO.CatDTO;
import com.cats.service.CatsCRUDService;
import com.cats.service.CatFotoMngmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@Transactional
@RequestMapping("/")
public class CatsSpringDataController {
    private static Logger LOGGER = LoggerFactory.getLogger(CatsSpringDataController.class);

    @Autowired
    private CatsCRUDService catsCRUDService;

    @Autowired
    private CatFotoMngmService catFotoMngmService;

    @RequestMapping("")
    public String indexView(Model model) {
        return "index";
    }

    //start:POST-REDIRECT-GET
    @RequestMapping(value = "/cats", method = {RequestMethod.POST})
    public String catListView(Model model, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result) {

        if (!result.hasErrors()) {
            catsCRUDService.insertCat(new Cat(catDTO.getName()));
            return "redirect:cats";
        } else {
            // TODO find better solution
            model.addAttribute("catsList", catsCRUDService.selectCats());
            return "catsList";
        }
    }

    @RequestMapping(value = "/cats", method = {RequestMethod.GET})
    public String doGet(Model model) {
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


    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public String handleFileUpload(Model model, @RequestParam("imgFile") MultipartFile imgFile, @RequestParam("catName") String catName, @RequestParam("description") String description) {
        catFotoMngmService.saveFile(imgFile, catName, description);
        return "redirect:cats";
    }
}