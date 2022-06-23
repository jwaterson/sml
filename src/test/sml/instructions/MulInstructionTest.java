package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class MulInstructionTest {
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
  @DisplayName("Basic initial tests")
  void execute() {
    assertAll(
            () -> assertEquals(0, m.getRegisters().getRegister(9)),
            () -> assertEquals(6, m.getRegisters().getRegister(10)),
            () -> assertEquals(0, m1.getRegisters().getRegister(0)),
            () -> assertEquals(18, m1.getRegisters().getRegister(7)),
            () -> assertEquals(1341988864, m1.getRegisters().getRegister(15)),
            () -> assertNotEquals(6, m1.getRegisters().getRegister(9)),
            () -> assertNotEquals(1, m1.getRegisters().getRegister(17)),
            // doesn't acknowledge floating point in instruction with label 0011
            () -> assertNotEquals(-1610386636.8, m1.getRegisters().getRegister(18))
    );
  }

  @Test
  void testToString() {
    assertAll(
            () -> assertEquals("13 mul 10 0 1", m.getProg().get(12).toString()),
            () -> assertEquals("14 mul 11 1 2", m.getProg().get(13).toString()),
            () -> assertEquals("-_- mul 7 6 5", m1.getProg().get(11).toString()),
            () -> assertEquals("0100 mul 20 18 19", m1.getProg().get(24).toString()),
            () -> assertNotEquals("03 mul    14 13         12", m1.getProg().get(18).toString())

    );
  }

}