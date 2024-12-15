package ardwloop.ext.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.llschall.ardwloop.ArdwloopStarter;
import org.llschall.ardwloop.ext.ArdwloopExtStarter;

import java.util.Objects;

public class BuildTest {

    @Test
    public void checkJava() {
        assertEquals("21",
                Objects.requireNonNull(System.getProperty("java.version")).split("\\.")[0]);
    }

    @Test
    public void checkArdwloop() {
        assertEquals("0.2.5", ArdwloopStarter.VERSION);
        assertEquals(1001, ArdwloopStarter.VERSION_INT);
        assertEquals("0.1.5", new ArdwloopExtStarter().getVERSION());
        assertEquals(1001, new ArdwloopExtStarter().getVERSION_INT());
    }
}
