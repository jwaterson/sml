package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class SubInstructionTest {
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
  void execute() {
    assertAll(
            () -> assertEquals(21, m.getRegisters().getRegister(5)),
            () -> assertEquals(21, m.getRegisters().getRegister(6)),
            () -> assertEquals(0, m.getRegisters().getRegister(12)),
            () -> assertEquals(Integer.MIN_VALUE + 4, m0.getRegisters().getRegister(28)),
            () -> assertEquals(0, m1.getRegisters().getRegister(4)),
            () -> assertEquals(8, m1.getRegisters().getRegister(2)),
            () -> assertNotEquals(8, m1.getRegisters().getRegister(1)),
            () -> assertNotEquals(0, m0.getRegisters().getRegister(29))
    );
  }

  @Test
  void testToString() {
    assertAll(
            () -> assertEquals("9 sub 6 5 6", m.getProg().get(8).toString()),
            () -> assertEquals("好 sub 28 29 31", m0.getProg().get(9).toString()),
            () -> assertEquals("t sub 1 3 1", m1.getProg().get(6).toString())
    );
  }


}