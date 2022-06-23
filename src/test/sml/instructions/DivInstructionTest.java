package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class DivInstructionTest {
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
    t1 = new Translator(BASE + "/passingTestFiles/DivMul.txt");
    t1.readAndTranslate(m1.getLabels(), m1.getProg());
    m1.execute();
  }

  @AfterEach
  void tearDown() {
    m = null;
    m1 = null;

    t = null;
    t1 = null;
  }

  @Test
  void execute() {
    assertAll(
            () -> assertEquals(0, m.getRegisters().getRegister(7)),
            () -> assertEquals(1, m.getRegisters().getRegister(8)),
            () -> assertEquals(6, m1.getRegisters().getRegister(8)),
            () -> assertEquals(m1.getRegisters().getRegister(17),
                    m1.getRegisters().getRegister(15)),
            () -> assertEquals(-1341988864, m1.getRegisters().getRegister(20)),
            () -> assertNotEquals(-447329621, m1.getRegisters().getRegister(23)),
            // floating points not acknowledged by design
            () -> assertNotEquals(-149109873.667, m1.getRegisters().getRegister(24)),
            () -> assertNotEquals(1, m1.getRegisters().getRegister(31))
    );
  }

  @Test
  void testToString() {
    assertAll(
            () -> assertEquals("10 div 7 0 1", m.getProg().get(9).toString()),
            () -> assertEquals("11 div 8 1 0", m.getProg().get(10).toString()),
            () -> assertEquals("1001 div 25 24 23", m1.getProg().get(29).toString()),
            () -> assertNotEquals("-_-_-_     div 10 8 9", m1.getProg().get(14).toString())
    );
  }


}