package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {
   void addnewaddress(Integer uid,String username,Address address);
   List<Address> getbyuid(Integer uid );
   void  setDefault(Integer aid,Integer uid,String username);
   void deletebyaid(Integer aid,Integer uid,String username);
}
