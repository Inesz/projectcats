package com.cat;

import com.cats.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CatsDAO {
    private static final Logger LOGGER = Logger.getLogger("CatsDAO");
    private List<Cat> cats = new ArrayList<>();

    public void addCat(Cat cat){
        cats.add(cat);
    }

    public void addCat(String catName){
        cats.add(new Cat(catName));
    }

    public void removeCat(String name){
        cats.removeIf( cat -> cat.getName().equals(name));
    }

    public String catsList (){
        String catsList = "";

        for (Cat cat : cats) {
            catsList = catsList.concat(cat.getName() + " " + cat.getBirth() + "\n");
        }

        return catsList;
    }

}
