package pl.kwolszczak.order;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestAbCd {

    StringBuilder completed = new StringBuilder("");

    @AfterEach
     void tearDown(){
        System.out.println(completed.toString());
    }

    @Test
    @Order(1)
    void test4A(){
        System.out.println("A");
        completed.append("A");
    }

    @Test
    @Order(2)
    void test1D(){
        System.out.println("D");
        completed.append("D");
    }

    @Test
    @Order(3)
    void test2C(){
        System.out.println("C");
        completed.append("C");
    }

    @Test
    @Order(4)
    void test3B(){
        System.out.println("B");
        completed.append("B");
    }
}
