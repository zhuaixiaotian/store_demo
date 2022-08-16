package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.ordermapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceimpl implements IOderService {
    @Autowired
    private ordermapper ordermapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService iCartService;

    @Override
    public Order create(Integer aid, Integer uid, Integer[] cids, String username) {
        Date date = new Date();
        Address getbyaid = addressService.getbyaid(aid,uid);
        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(getbyaid.getName());
        order.setRecvPhone(getbyaid.getPhone());
        order.setRecvProvince(getbyaid.getProvinceName());
        order.setRecvCity(getbyaid.getCityName());
        order.setRecvArea(getbyaid.getAreaName());
        order.setRecvAddress(getbyaid.getAddress());
        order.setStatus(0);
        order.setOrderTime(date);
        order.setCreatedUser(username);
        order.setCreatedTime(date);
        order.setModifiedUser(username);
        order.setModifiedTime(date);

        List<CartVO> getvobycids = iCartService.getvobycids(cids, uid);
        Long total=0L;

        for (CartVO getvobycid : getvobycids) {
            total+=getvobycid.getRealPrice()*getvobycid.getNum();
        }

        System.out.println(total);
        order.setTotalPrice(total);
        Integer insertorder = ordermapper.insertorder(order);
        if (insertorder!=1)
            throw  new InsertException("创建订单失败");


        for (CartVO cartVO : getvobycids) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

         Integer   rows = ordermapper.insertorder_item(orderItem);
            if (rows != 1) {
                throw new InsertException("插入订单项时产生未知的异常");
            }


        }

        return order;


    }


}
