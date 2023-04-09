package com.microservices.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String> {

}
