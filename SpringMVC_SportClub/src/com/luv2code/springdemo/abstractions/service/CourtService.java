package com.luv2code.springdemo.abstractions.service;

import com.luv2code.springdemo.entity.Court;

public interface CourtService {
    Court findById(int id);
}
