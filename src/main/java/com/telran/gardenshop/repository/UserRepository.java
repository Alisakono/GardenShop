package com.telran.gardenshop.repository;



import com.telran.gardenshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Query("SELECT v FROM User v WHERE v.name LIKE %?1%")
    List<User> findUsersByName(String name);


}
