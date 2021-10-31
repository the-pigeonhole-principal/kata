package helloworld;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class HelloWorldTest {

    @Test
    public void helloWorld() {
        assertThat(HelloWorld.getHelloWorld(), is("Hello world"));
    }
    
}
