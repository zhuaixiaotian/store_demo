package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;

import com.cy.store.mapper.ProductMapper;
import com.cy.store.mapper.cartmapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.*;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class ICartServiceimpl implements ICartService {
    @Autowired
    private com.cy.store.mapper.cartmapper cartmapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addnewcart(Integer uid, Integer pid, Integer num, String username) {
        Cart result = cartmapper.findById(uid, pid);
        if (result==null)//没有放入过购物车  插入
        {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedTime(new Date());
            Integer rows =cartmapper.insert(cart);
            if (rows!=1)
            {
                throw  new InsertException("插入购物车异常");
            }


        }//已经放入购物车 更新
        else {
            Integer updatenumbycid = cartmapper.updatenumbycid(result.getCid(),
                    result.getNum() + num, username, new Date());
            if (updatenumbycid!=1)
            {

                throw  new UpdateException("更新购物车异常");
            }



        }


    }

    @Override
    public List<CartVO> getvobyuid(Integer uid) {

        return cartmapper.findbyuid(uid);
    }

    public  void checkCart(Cart cart,Integer uid){
        if (cart==null)
        {

            throw  new CartNotFoundException("购物车不存在");
        }

        if (!cart.getUid().equals(uid))
        {
            throw  new AccessDeniedException("数据非法访问");
        }
    }




    @Override
    public Integer addnum(Integer cid, Integer uid, String username) {

        Cart findbycid = cartmapper.findbycid(cid);
        checkCart(findbycid,uid);
        Integer num=findbycid.getNum()+1;
        Integer updatenumbycid = cartmapper.updatenumbycid(cid, num, username, new Date());
        if (updatenumbycid!=1)
        {
            throw  new UpdateException("购物车更新异常");
        }
        return  num;

    }

    @Override
    public Integer reducenum(Integer cid, Integer uid, String username) {


        Cart findbycid = cartmapper.findbycid(cid);
        checkCart(findbycid,uid);
        Integer num=findbycid.getNum()-1;
        if (num<=0)
        {
            Integer deletebycid = cartmapper.deletebycid(cid);
            if (deletebycid!=1)
            {
                throw new DeleteException("购物车删除失败");

            }
            return  -1;
        }
        Integer updatenumbycid = cartmapper.updatenumbycid(cid, num, username, new Date());

        if (updatenumbycid != 1) {
            throw  new UpdateException("购物车更新异常");

        }
            return  num;

    }

    @Override
    public void delete(Integer cid, Integer uid) {
        Cart findbycid = cartmapper.findbycid(cid);
        checkCart(findbycid,uid);
        Integer deletebycid = cartmapper.deletebycid(cid);
        if (deletebycid!=1)
        {
            throw  new DeleteException("购物车删除失败");
        }


    }

    @Override
    public List<CartVO> getvobycids(Integer[] cids, Integer uid) {
        List<CartVO> findlisbycid = cartmapper.findlisbycid(cids);
        for (CartVO cartVO : findlisbycid) {
            if (!cartVO.getUid().equals(uid))
                throw  new AccessDeniedException("数据非法访问");
        }
        return findlisbycid;


    }
}
