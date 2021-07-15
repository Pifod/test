package com.example.webboot;

import com.example.webboot.service.TestCaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebBootApplicationTests {
    @Autowired
    private TestCaseService testCaseService;

    @BeforeEach
    public void setup(){
        testCaseService.cleanUp();
        testCaseService.initData();
    }

    @AfterEach
    public void cleanUp(){
        testCaseService.cleanUp();
    }

    @Test
    void gettingLazyInitializationException() {
        testCaseService.addUserFailSessionExpire();
    }

    @Test
    void printAllUser() { //N+1 problem
        testCaseService.addUserSuccess();
    }

    @Test
    void printAllUserByFetchJoin() {//N + 1 solved
        testCaseService.addUserNonTransactionalSuccess();
    }

    @Test
    void manyToManyTable() {
        testCaseService.addUserNonTransactionalSuccess();
    }

    @Test
    void gettingObjectWithCompositionObjectTree() { // is correct way?
        testCaseService.manyToManyWithCompositionItems();
    }

    @Test
    void gettingDetachedEntityPersistingException() {
        testCaseService.detachedPersistEntity();
    }

    @Test
    void gettingProxyEscape() {
        testCaseService.callTransactionAnnotaitedMethod();
    }

    @Test
    void contextLoads() {
//        bothService.addUserFail();
//        bothService.addUserSuccess();
//        testCaseService.addUserNonTransactionalSuccess();
//        testCaseService.cleanUp();
        testCaseService.manyToManyCheck();


//        User first = new User("Ivan", 22, Collections.emptySet());
//        bothService.addUser(first);
//        bothService.findAllUser().forEach(u -> System.out.println(u.toString()));
//
//
//        bothService.removeAllUser();
//
//        User second = new User("Mikhail", 23, new HashSet<>(Arrays.asList(new Auto("audi", "black", null))));
//        bothService.addUser(first);
//        bothService.findAllUser().forEach(u -> System.out.println(u.toString()));

    }

}
