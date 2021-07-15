package com.example.webboot.service;

import com.example.webboot.dto.Auto;
import com.example.webboot.dto.Trip;
import com.example.webboot.dto.User;
import com.example.webboot.repository.AutoRepository;
import com.example.webboot.repository.TripRepository;
import com.example.webboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.*;

@org.springframework.stereotype.Service
public class TestCaseService {
    private AutoRepository autoRepository;

    private UserRepository userRepository;

    private TripRepository tripRepository;

    @Autowired
    public TestCaseService(AutoRepository autoRepository, UserRepository userRepository, TripRepository tripRepository) {
        this.autoRepository = autoRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    public void addUserFailSessionExpire() {
        userRepository.findAll().forEach(u -> System.out.println(u.toString()));
    }

    @Transactional
    public void addUserSuccess() {
        userRepository.findAll().forEach(u -> System.out.println(u.toString()));
    }

    public void addUserNonTransactionalSuccess() {
        userRepository.findUsersByFetch().forEach(u -> System.out.println(u.toString()));
    }

    public void manyToManyCheck() {
        tripRepository.findAll().forEach(t-> System.out.println(t.toString()));
    }

    @Transactional
    public void manyToManyWithCompositionItems() {
        for (Trip trip : tripRepository.findAll()) {
            for (Auto auto : trip.getAutos()) {
                System.out.println(auto.getUser().toString());
            }
        }
    }

    public void callTransactionAnnotaitedMethod() {
        initData();
    }

    @Transactional
    public void addUserAndCarSuccess() {
        User first = new User("Ivan", 22, Collections.emptySet());
        userRepository.findAll().forEach(u -> System.out.println(u.toString()));
        cleanUp();
    }

    @Transactional
    public void initData() {
        Auto whiteLada = new Auto("lada", "white", null);
        Auto blackToyota = new Auto("toyota", "black", null);

        User first = new User("Ivan", 22, new HashSet<>());
        first.addAuto(whiteLada);
        first.addAuto(blackToyota);
        first = userRepository.save(first);


        Auto blackAudi = new Auto("audi", "black", null);

        User second = new User("Mikhail", 23, new HashSet<>());
        second.addAuto(blackAudi);
//        blackAudi.setUser(second);
        second = userRepository.save(second);
//        autoRepository.save(blackAudi);


        Trip saintP = new Trip("A104", "24.05.2022");
        saintP.addAuto(blackAudi);
        saintP = tripRepository.save(saintP);


        Trip moscow = new Trip("A104", "28.05.2022");
        moscow.addAuto(blackAudi);
        moscow.addAuto(blackToyota);
        moscow = tripRepository.save(moscow);
    }

    public void detachedPersistEntity() {
        Auto whiteLada = new Auto("lada", "white", null);

        User first = new User("Ivan", 22, new HashSet<>());
        first.addAuto(whiteLada);
        first = userRepository.save(first);

        Trip saintP = new Trip("A104", "24.05.2022");
        saintP.addAuto(whiteLada);
        saintP = tripRepository.save(saintP);
    }

    public void cleanUp(){
        userRepository.deleteAll();
        tripRepository.deleteAll();
        autoRepository.deleteAll();
    }
}
