package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.jsonresult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{cid}")
    public jsonresult<Integer> addnum(@PathVariable Integer cid,HttpSession session)
    {
        Integer addnum = cartService.addnum(cid, getuidsession(session), getnamesession(session));
        return  new jsonresult<>(addnum,ok);
    }




    @DeleteMapping("/reduce/{cid}")
    public jsonresult<Integer> reducenum(@PathVariable Integer cid,HttpSession session)
    {

        Integer reducenum = cartService.reducenum(cid, getuidsession(session), getnamesession(session));
        return  new jsonresult<>(reducenum,ok);


    }

    @DeleteMapping("{cid}")
    public jsonresult<Void> delete(@PathVariable Integer cid,HttpSession session)
    {

       cartService.delete(cid, getuidsession(session));
       return  new jsonresult<>(ok);


    }
    @PostMapping
    public  jsonresult<List<CartVO>> getvobycids(HttpSession session, Integer[]cids)
    {

        if (cids==null)
        {
            return  new jsonresult<>(null,0);
        }
        List<CartVO> getvobycids = cartService.getvobycids(cids, getuidsession(session));
        return  new jsonresult<>(getvobycids,ok);
    }


}
