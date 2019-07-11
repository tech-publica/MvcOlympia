package com.luv2code.springdemo.abstractions.service;

import com.luv2code.springdemo.entity.Client;

public interface ClientService {
    Client findById(int id);
    
}
