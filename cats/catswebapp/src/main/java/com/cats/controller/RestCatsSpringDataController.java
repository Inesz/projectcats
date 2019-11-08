package com.cats.controller;

import com.cats.Cat;
import com.cats.service.CatsCRUDService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

    @Controller
    @Transactional
    @RequestMapping("/rest/cats")
    public class RestCatsSpringDataController {
        private static Logger LOGGER = LoggerFactory.getLogger(RestCatsSpringDataController.class);

        @Autowired
        CatsCRUDService catsCRUDService;

        @RequestMapping(value="/hello", method= RequestMethod.GET)
        public ResponseEntity<String> getString() {
            return new ResponseEntity<String>("Hello! It is first rest service", new HttpHeaders(), HttpStatus.OK);
        }

        /**
         * Makes the object Cat with CatFoto available at the address /rest/cats/catId as json.
         *
         * Hibernate.initialize(cat.getCatFoto()) not working without @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) -
         * isInitialized is true, but there is still problem:
         * "No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties
         * discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)"
         * JsonIgnoreProperties and initialize() solved the FetchType.LAZY issue.
         *
         * @param id catId
         * @return http response
         * {"id":91,"catFoto":{"comment":"Kotek mamrotek :-)","size":1051794,"type":"image/jpeg","oldname":"liberte.JPG","newname":"1571939222555"},"name":"Olek","birth":1567288800000,"weight":3.0,"owner":"Kasia"}
         */
        @RequestMapping(value="/{id}", method= RequestMethod.GET)
        public ResponseEntity<Cat> getCat(@PathVariable("id") String id) {
            Cat cat = catsCRUDService.selectCatById(id);
            LOGGER.info("Is initialized cat: " + Boolean.toString(Hibernate.isInitialized(cat))); //true
            LOGGER.info("Is initialized catFoto: " + Boolean.toString(Hibernate.isInitialized(cat.getCatFoto()))); //false

            Hibernate.initialize(cat.getCatFoto());
            LOGGER.info("Is initialized cat: " + Boolean.toString(Hibernate.isInitialized(cat))); //true
            LOGGER.info("Is initialized catFoto: " + Boolean.toString(Hibernate.isInitialized(cat.getCatFoto()))); //true

            return new ResponseEntity<Cat>(cat, new HttpHeaders(), HttpStatus.OK);
        }
    }