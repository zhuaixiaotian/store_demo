package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;

public interface IOderService {
    Order create(Integer aid, Integer uid, Integer []cids, String username);
}
