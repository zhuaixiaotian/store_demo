package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    void  addnewcart(Integer uid,Integer pid,Integer num,String username);
    List<CartVO> getvobyuid(Integer uid);
    Integer   addnum(Integer cid,Integer uid,String username);
    Integer  reducenum(Integer cid,Integer uid,String username);
    void   delete(Integer cid,Integer uid);
    List<CartVO> getvobycids(Integer []cids,Integer uid);
}
