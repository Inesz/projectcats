package com.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        connectionTest();
        DBTestDataSource();
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

    @Autowired
    private DataSource dataSource;
    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    /** for Tomcat and sout, logs are in catalina.out in tomcat file tree*/
    public void DBTestEntityManager() {
        Query query = entityManager.createQuery("SELECT k FROM cats k");
        List<Cat> cats =  query.getResultList();
        for(Cat c : cats){
            System.out.println(c.getId() + " " + c.getName() + " " + c.getOwner());
        }
    }

    /** for Tomcat and sout, logs are in catalina.out in tomcat file tree*/
    public void DBTestDataSource(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cats");
            ResultSet rs = ps.executeQuery();
            System.out.println("connection");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("owner"));
            }

        } catch (SQLException e) {
            System.out.println("connection failed");
        }
    }

    /** for Tomcat and sout, logs are in catalina.out in tomcat file tree*/
    public void connectionTest(){
        if(entityManager.isOpen()){
            System.out.println("open");
        }else{
            System.out.println("not open");
        }

        entityManager.getProperties().forEach((s,o) -> System.out.println(s));
    }
}
