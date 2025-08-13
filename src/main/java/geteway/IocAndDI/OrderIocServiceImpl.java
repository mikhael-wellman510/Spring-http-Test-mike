package geteway.IocAndDI;

import org.springframework.stereotype.Service;


public class OrderIocServiceImpl {

    public String testDi(String name){

        return "Hai "  + name;
    }
}
