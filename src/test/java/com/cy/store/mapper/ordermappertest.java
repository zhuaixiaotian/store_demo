package com.cy.store.mapper;

import com.alibaba.druid.filter.AutoLoad;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ordermappertest{
    @Autowired
    private  ordermapper ordermapper;
    @Test
    public  void insertorderr()
    {
        Order order = new Order();
        order.setUid(9);
        order.setRecvName("sty");
        order.setRecvPhone("1234");
        ordermapper.insertorder(order);

    }
    @Test
    public  void insertitem()
    {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000028);
        orderItem.setTitle("wdds");
        ordermapper.insertorder_item(orderItem);

    }


}
