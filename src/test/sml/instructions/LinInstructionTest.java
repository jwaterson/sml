package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.exceptions.IllegalInstructionException;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class LinInstructionTest {
    private Machine m, m0, m1, m2;
    private Translator t, t0, t1, t2;
    private static final String BASE = "C:/Users/jjwat/OneDrive/Desktop/MSc_CS/7_SDP/"
            + "CW/individual-coursework-jwaterson/src/test/resources";

    @BeforeEach
    void setUp() {
        m = new Machine();
        t = new Translator(BASE + "/passingTestFiles/MixAndMatch.txt");
        t.readAndTranslate(m.getLabels(), m.getProg());
        m.execute();

        m0 = new Machine();
        t0 = new Translator(BASE + "/passingTestFiles/DivMul.txt");
        t0.readAndTranslate(m0.getLabels(), m0.getProg());
        m0.execute();

        m1 = new Machine();
        t1 = new Translator(BASE + "/failingTestFiles/LinFailing1.txt");

        m2 = new Machine();
        t2 = new Translator(BASE + "/failingTestFiles/LinFailing2.txt");
    }

    @AfterEach
    void tearDown() {
        m = null;
        m0 = null;
        m1 = null;
        m2 = null;

        t = null;
        t0 = null;
        t1 = null;
        t2 = null;
    }

    @Test
    void execute() {
        assertAll(
                () -> assertEquals(13, m.getRegisters().getRegister(4)),
                () -> assertEquals(1, m0.getRegisters().getRegister(16)),
                () -> assertNotEquals(1, m0.getRegisters().getRegister(19)),
                () -> assertThrows(IllegalInstructionException.class,
                        () -> t1.readAndTranslate(m1.getLabels(), m1.getProg())),
                () -> assertThrows(IllegalInstructionException.class,
                        () -> t2.readAndTranslate(m2.getLabels(), m2.getProg()))
        );
    }

    @Test
    void testToString() {
        assertAll(
                () -> assertEquals("1 lin 0 1", m.getProg().get(0).toString()),
                () -> assertEquals("3 lin 1 1", m.getProg().get(2).toString()),
                () -> assertEquals("0110 lin 22 -3", m0.getProg().get(26).toString()),
                () -> assertNotEquals("1011  lin   27          2", m0.getProg().get(31).toString())
        );
    }

}