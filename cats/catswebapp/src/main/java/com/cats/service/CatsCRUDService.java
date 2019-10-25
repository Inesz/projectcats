package com.cats.service;

import com.cats.Cat;
import com.cats.DAO.SpringDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatsCRUDService {

    @Autowired
    private SpringDataDAO springDataDAO;

    public void insertCat(Cat cat) {
        springDataDAO.save(cat);
    }

    public Cat selectCat(String name) {
        return springDataDAO.findByName(name).get();
    }

    public Cat selectCatById(String id) {
        Integer catId = Integer.parseInt(id);
        return springDataDAO.findById(catId).get();
    }

    public Cat selectCatById(Integer id) { return springDataDAO.findById(id).get(); }

    public List<Cat> selectCats() {
        return springDataDAO.findAll();
    }

    public void deleteCat(Cat cat){
        springDataDAO.delete(cat);
    }

    public void deleteCat(String id){
        springDataDAO.deleteById(Integer.parseInt(id));
    }

}
