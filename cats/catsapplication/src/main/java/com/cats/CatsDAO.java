package com.cats;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class CatsDAO {
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

    public List<Cat> getCatsList() {
        return cats;
    }


}
