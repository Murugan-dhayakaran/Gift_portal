package com.dhaya.giftportal.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dhaya.giftportal.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    
}
