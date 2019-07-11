package com.luv2code.springdemo.abstractions.dao;

import com.luv2code.springdemo.entity.Client;

public interface ClientDAO {
   Client findById(int id);
}
