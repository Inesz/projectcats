package com.cats;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
        DBTestEntityManager();
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

    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void DBTestEntityManager() {
        try {
            Query query = entityManager.createQuery("SELECT k FROM cats k");
            List<Cat> cats = query.getResultList();
            for (Cat c : cats) {
                System.out.println(c.getId() + " " + c.getName() + " " + c.getOwner());
            }
        }
        catch(Exception e){
            System.out.println("DBTestEntityManager exception:\n");
            e.printStackTrace();
        }
    }
}
