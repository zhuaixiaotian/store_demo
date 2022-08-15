package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.jsonresult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class cartcontroller extends basecontroller{
    @Autowired
    private ICartService cartService;


    @GetMapping("/{id}/{amount}")
    public jsonresult<Void> addCart(@PathVariable Integer id,@PathVariable Integer amount, HttpSession session)
    {
        Integer uid=getuidsession(session);
        String username=getnamesession(session);
        cartService.addnewcart(uid,id,amount,username);
        return  new jsonresult<>(ok);

    }

    @GetMapping
    public  jsonresult<List<CartVO>> getvobyuid(HttpSession session)
    {
        List<CartVO> getvobyuid = cartService.getvobyuid(getuidsession(session));
        return  new jsonresult<>(getvobyuid,ok);

    }


}
