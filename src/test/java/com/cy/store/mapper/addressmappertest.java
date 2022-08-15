package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class addressmappertest {
    @Autowired
    private com.cy.store.mapper.addressmapper addressmapper;
    @Test
    public  void f()
    {
        Address address = new Address();
        address.setUid(9);
        address.setAddress("wddsasd");
        Integer insert = addressmapper.insert(address);
        System.out.println(address);
    }

    @Test
    public  void t()
    {
        List<Address> selectbyuid = addressmapper.selectbyuid(9);
        System.out.println(selectbyuid);
    }

    @Test
    public  void selectbyaid()
    {
        Address findbyaid = addressmapper.findbyaid(5);
        System.out.println(findbyaid);

    }

    @Test
    public  void updatenondefault()
    {
        Integer updatenondefault = addressmapper.updatenondefault(9);
        System.out.println(updatenondefault);

    }
    @Test
    public  void updatedefault ()
    {
        addressmapper.updatedefault(5,"sty",new Date());


    }


    @Test
    public  void delete()
    {

        Integer deletebyaid = addressmapper.deletebyaid(2);
        System.out.println(deletebyaid);

    }

    @Test
    public  void  findlast()
    {
        Address findlastmodified = addressmapper.findlastmodified(9);
        System.out.println(findlastmodified);

    }


}
