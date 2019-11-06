package com.cats.controller;

import com.cats.Cat;
import com.cats.DTO.CatDTO;
import com.cats.service.CatsCRUDService;
import com.cats.service.MngmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private MngmService mngmService;

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

    @RequestMapping("/remove-{id}")
    public String catRemove(Model model, @PathVariable("id") String id, final RedirectAttributes redirectAttributes) {

        mngmService.deleteCatWithCatFoto(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "redirectAttr.remove.success");

        return "redirect:cats";
    }


    @RequestMapping(value = "/cats/upload", method = {RequestMethod.POST})
    public String handleFileUpload(Model model, @RequestParam("imgFile") MultipartFile imgFile, @RequestParam("catId") String catId, @RequestParam("description") String description) {
        mngmService.addImage(imgFile, catId, description);
        return "redirect:" + catId;
    }

    @RequestMapping(value = "/cats/{id}", method = {RequestMethod.GET})
    public String catNameViewGet(Model model, @PathVariable("id") String id) {
        Cat cat = catsCRUDService.selectCatById(id);
        model.addAttribute("cat", cat);
        model.addAttribute("catImg", imgUrl(mngmService.getImage(cat)));
        model.addAttribute("catImgComment", getImgComment(cat));

        model.addAttribute("catDTO", new CatDTO());
        return "cat";
    }

    @RequestMapping(value = "/cats/{id}", method = {RequestMethod.POST})
    public String catNameViewPost(Model model, @PathVariable("id") String id, @ModelAttribute("catDTO") @Valid CatDTO catDTO, BindingResult result) {

        if (!result.hasErrors()) {
            updateCat(id, catDTO);
            return "redirect:" + id;
        } else {
            Cat cat = catsCRUDService.selectCatById(id);
            model.addAttribute("cat", cat);
            model.addAttribute("catImg", imgUrl(mngmService.getImage(cat)));
            model.addAttribute("catImgComment", getImgComment(cat));
            return "cat";
        }
    }

    private void updateCat(String id, CatDTO catDTO){
        Cat cat = catsCRUDService.selectCatById(id);
        cat.setName(catDTO.getName());
        cat.setOwner(catDTO.getOwner());
        //cat.setWeight(catDTO.getWeight());
        //cat.setBirth(catDTO.getBirth());
    }

    private String imgUrl(String imgEncode){
        String imgFormat = "data:image/jpeg;base64,";
        String imgSrc = (imgEncode.isBlank()) ? imgEncode : imgFormat + imgEncode;
        LOGGER.info("Cat image imgSrc:" + imgSrc);
        return imgSrc;
    }

    private String getImgComment(Cat cat){
        try {
            return cat.getCatFoto().getComment();
        } catch (Exception e) {
            return "";
        }
    }
}