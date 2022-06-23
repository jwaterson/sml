package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BnzInstructionTest {
    private Machine m, m0, m1;
    private Translator t, t0, t1;
    private static final String BASE = "C:/Users/jjwat/OneDrive/Desktop/MSc_CS/7_SDP/"
            + "CW/individual-coursework-jwaterson/src/test/resources";

    @BeforeEach
    void setUp() {
        m = new Machine();
        t = new Translator(BASE + "/passingTestFiles/MixAndMatch.txt");
        t.readAndTranslate(m.getLabels(), m.getProg());
        m.execute();

        m0 = new Machine();
        t0 = new Translator(BASE + "/passingTestFiles/AddSub.txt");
        t0.readAndTranslate(m0.getLabels(), m0.getProg());
        m0.execute();

        m1 = new Machine();
        t1 = new Translator(BASE + "/passingTestFiles/Fibonacci.txt");
        t1.readAndTranslate(m1.getLabels(), m1.getProg());
        m1.execute();
    }

    @AfterEach
    void tearDown() {
        m = null;
        m0 = null;
        m1 = null;

        t = null;
        t0 = null;
        t1 = null;
    }

    @Test
    @DisplayName("Basic initial test")
    void execute() {
        assertAll(
                () -> assertEquals(0, m.getRegisters().getRegister(12)),
                () -> assertEquals(0, m0.getRegisters().getRegister(27)),
                () -> assertEquals(0, m1.getRegisters().getRegister(4))
        );
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals("19 bnz 12 18", m.getProg().get(19).toString()),
                () -> assertEquals("ö bnz 27 _lin271", m0.getProg().get(15).toString()),
                () -> assertEquals("o bnz 4 r", m1.getProg().get(9).toString())
        );
    }


}