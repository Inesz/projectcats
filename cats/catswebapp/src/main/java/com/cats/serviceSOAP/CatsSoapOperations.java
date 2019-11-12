package com.cats.serviceSOAP;

import com.cats.Cat;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface CatsSoapOperations {
    @WebMethod(action = "helloSOAP", operationName="helloSOAP")
    String helloSOAP();

    @WebMethod(action = "catsList", operationName="catsList")
    List<Cat> catsList();
}