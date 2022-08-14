package com.cy.store.controller;

import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.jsonresult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class producecontroller extends basecontroller{
    @Autowired
    private IProductService productService;

   @GetMapping
    public jsonresult<List<Product>>  gethotlist()
    {
        System.out.println("wddsa");
        List<Product> hotList = productService.findHotList();
        System.out.println("wddsa");
        return  new jsonresult<>(hotList,ok);
    }

    @GetMapping("{id}")
    public  jsonresult<Product> getById(@PathVariable Integer id)
    {
        Product byId = productService.findById(id);
        return  new jsonresult<>(byId,ok);

    }


}
