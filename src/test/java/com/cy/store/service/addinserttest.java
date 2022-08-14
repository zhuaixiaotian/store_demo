package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
public class addinserttest {
    @Autowired
    private  IAddressService addressService;
    @Test
    public  void f()
    {

        Address address = new Address();
        address.setUid(10);
        address.setAddress("jiangsusheng");
    addressService.addnewaddress(10,"sty",new Address());


    }

    @Test
    public  void defaultTest()
    {
        addressService.setDefault(3,9,"sty");



    }
    @Test
    public  void delete()
    {
        addressService.deletebyaid(12,9,"sty");


    }



}
