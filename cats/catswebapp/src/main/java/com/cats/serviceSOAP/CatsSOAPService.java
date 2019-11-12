package com.cats.serviceSOAP;

import com.cats.Cat;
import com.cats.CatFoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

//endpointInterface need to be correct becouse of WebServiceException: Could not load Webservice SEI
@WebService(
        endpointInterface = "com.cats.serviceSOAP.CatsSoapOperations",
        name = "CatsSoapService",
        serviceName = "CatsSoapService",
        targetNamespace = "http://localhost:8080/catswebapp/soap")
class CatsSOAPService implements CatsSoapOperations {
    private static Logger LOGGER = LoggerFactory.getLogger(CatsSOAPService.class);

    /**
     * imitates downloading data from the database for reason of problems with autowiring DAO bean
     * no helped:
     * - annotated as @Service, @Controller, @Transactional..
     * - extends SpringBeanAutowiringSupport
     * - @PostConstruct
     *     public void init() {
     *         SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
     *     }
     * init internal method field with new working, but still can`t get data from springCatDAO
     * attempts to solve the problem with dependencies and servlet configuration did not help
     * @return
     */
    private List<Cat> getCatList() {
        LOGGER.info("Hibernate Error");
        List<Cat> catListLocal = new ArrayList<>();

        Cat c1 = new Cat("Tempek");
        Cat c2 = new Cat("Temposzek");
        c1.setCatFoto(new CatFoto());
        c2.setCatFoto(new CatFoto());
        catListLocal.add(c1);
        catListLocal.add(c2);

        return catListLocal;
    }

    @WebMethod()
    public String helloSOAP() {
        LOGGER.info("helloSOAP");
        return "Hello from SOAP";
    }

    @WebMethod()
    public List<Cat> catsList() {
        LOGGER.info("CatList");
        List<Cat> lc = getCatList();
        return lc;
    }
}