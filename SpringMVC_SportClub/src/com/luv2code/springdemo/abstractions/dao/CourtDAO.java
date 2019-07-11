package com.luv2code.springdemo.abstractions.dao;

import com.luv2code.springdemo.entity.Court;

public interface CourtDAO {
     Court findById(int id);
}
