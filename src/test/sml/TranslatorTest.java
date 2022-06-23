package test.sml;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;
import sml.exceptions.IllegalInstructionException;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {
    private Machine m, m1, m2;
    private Translator t, t1, t2;
    private static final String BASE = "C:/Users/jjwat/OneDrive/Desktop/MSc_CS/7_SDP/" +
            "CW/individual-coursework-jwaterson/src/test/resources";

    @BeforeEach
    void setUp() {
        m = new Machine();
        t = new Translator(BASE + "/failingTestFiles/translator/WhitespaceLabel.txt");

        m1 = new Machine();
        t1 = new Translator(BASE + "/failingTestFiles/translator/DuplicateLabel.txt");
    }

    @AfterEach
    void tearDown() {
        m = null;
        m1 = null;

        t = null;
        t1 = null;
    }

    @Test
    void readAndTranslateTest1() {
        assertAll(
                () -> assertThrows(IllegalInstructionException.class,
                        () -> t.readAndTranslate(m.getLabels(), m.getProg())),
                () -> assertThrows(IllegalInstructionException.class,
                        () -> t1.readAndTranslate(m1.getLabels(), m1.getProg()))
        );
    }

    @Test
    void getInstruction() {
        // valid calls to getInstruction are demonstrated by implication in Instruction's subclasses' tests
        assertAll(
                () -> assertNull(t.getInstruction(""))
        );
    }
}
