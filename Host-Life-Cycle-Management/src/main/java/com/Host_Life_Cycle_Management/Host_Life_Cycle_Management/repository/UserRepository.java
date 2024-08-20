package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.repository;

import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  java.util.*;

//kennzeichnet das Interface als ein Spring Data Repository
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Methode zur Suche eines Benutzers anhand des Benutzernamens
    Optional<User> findByUsername(String username);
}
