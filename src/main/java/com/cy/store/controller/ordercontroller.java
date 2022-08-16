package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.service.IOderService;
import com.cy.store.util.jsonresult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class ordercontroller extends  basecontroller{
    @Autowired
    private IOderService orderService;

    @GetMapping("/{aid}")
    public jsonresult<Order> getOrder(@PathVariable Integer aid, Integer []cids, HttpSession session)
    {
        Integer uid=getuidsession(session);
        String username=getnamesession(session);
        Order order = orderService.create(aid, uid, cids, username);
        return  new jsonresult<>(order,ok);

    }


}
