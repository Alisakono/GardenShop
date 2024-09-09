package com.telran.gardenshop.repository;



import com.telran.gardenshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
   User findUsersByEmail(String email);


}
