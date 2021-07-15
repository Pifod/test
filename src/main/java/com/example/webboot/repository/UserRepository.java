package com.example.webboot.repository;

import com.example.webboot.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u left JOIN FETCH u.autos")
    Set<User> findUsersByFetch();

//    @Query("SELECT u FROM User u left JOIN FETCH u.autos")
//    Set<User> findUsersByFetch();

    List<User> findUserByName(String name);

    List<User> findUserByNameAndAge(String name, Integer age);

}
