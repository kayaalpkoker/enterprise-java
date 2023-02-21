package edu.sabanciuniv.module03;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    // Service & End-point & API
    @RequestMapping("/hello")
    public String sayHello(@RequestParam String name){
        return "Hello IT526";
    }

    @RequestMapping("/hello2")
    public String sayHello2(){
        return "Hello IT526 from the other side";
    }

}
