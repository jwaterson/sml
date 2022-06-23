package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class OutInstructionTest {
    private final ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
    private Machine m, m1;
    private Translator t, t1;
    private static final String BASE = "C:/Users/jjwat/OneDrive/Desktop/MSc_CS/7_SDP/"
            + "CW/individual-coursework-jwaterson/src/test/resources";

    @BeforeEach
    void setUp() {
        m = new Machine();
        t = new Translator(BASE + "/passingTestFiles/MixAndMatch.txt");
        t.readAndTranslate(m.getLabels(), m.getProg());
        m.execute();

        m1 = new Machine();
        t1 = new Translator(BASE + "/passingTestFiles/Fibonacci.txt");
        t1.readAndTranslate(m1.getLabels(), m1.getProg());
        m1.execute();

        System.setOut(new PrintStream(bOutStream));
    }

    @AfterEach
    void tearDown() {
        m = null;
        m1 = null;
        t = null;
        t1 = null;
    }

    @Test
    @DisplayName("Basic initial test 1")
    void execute() {
        Instruction out = m.getProg().get(14);
        out.execute(m);
        assertEquals("15\n", bOutStream.toString());
    }

    @Test
    @DisplayName("Basic initial test 2")
    void execute1() {
        Instruction out = m.getProg().get(15);
        out.execute(m);
        // missing the newline at the end, so will not pass
        assertNotEquals("6", bOutStream.toString());
    }

    @Test
    @DisplayName("Basic initial test 3")
    void execute2() {
        Instruction out = m1.getProg().get(10);
        out.execute(m1);
        assertEquals("8\n", bOutStream.toString());
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals("15 out 11", m.getProg().get(14).toString()),
                () -> assertEquals("16 out 10", m.getProg().get(15).toString()),
                () -> assertEquals("j out 3", m1.getProg().get(10).toString())
        );
    }


}