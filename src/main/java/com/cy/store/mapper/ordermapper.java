package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

public interface ordermapper {
    Integer  insertorder(Order order);
    Integer  insertorder_item(OrderItem orderItem);
}
