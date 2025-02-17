package assyatier.dev.learnspringboot;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    public String getHelloWorldMessage() {
        return "Hello World";
    }
}
