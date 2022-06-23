package test.sml.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Translator;

import static org.junit.jupiter.api.Assertions.*;

class AddInstructionTest {
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
    t0 = new Translator(BASE + "/passingTestFiles/AddSub.txt");
    t0.readAndTranslate(m0.getLabels(), m0.getProg());
    m0.execute();

    m1 = new Machine();
    t1 = new Translator(BASE + "/passingTestFiles/Fibonacci.txt");
    t1.readAndTranslate(m1.getLabels(), m1.getProg());
    m1.execute();

    m2 = new Machine();
    t2 = new Translator(BASE + "/failingTestFiles/InvalidRegister.txt");
    t2.readAndTranslate(m2.getLabels(), m2.getProg());
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
            () -> assertEquals(2, m.getRegisters().getRegister(0)),
            () -> assertEquals(3, m.getRegisters().getRegister(1)),
            () -> assertEquals(5, m.getRegisters().getRegister(2)),
            () -> assertEquals(Integer.MAX_VALUE, m0.getRegisters().getRegister(0)),
            () -> assertEquals(Integer.MIN_VALUE, m0.getRegisters().getRegister(1)),
            () -> assertEquals(Integer.MAX_VALUE, m0.getRegisters().getRegister(31)),
            () -> assertEquals(Integer.MAX_VALUE - 1, m0.getRegisters().getRegister(30)),
            () -> assertEquals(8, m1.getRegisters().getRegister(3)),
            // register 31 is originally loaded with value -1
            () -> assertNotEquals(-1, m0.getRegisters().getRegister(31)),
            // check a register's value is updated after addition
            () -> assertNotEquals(0, m.getRegisters().getRegister(20)),
            // check addition doesn't go ahead where an invalid register is provided
            () -> assertThrows(ArrayIndexOutOfBoundsException.class, m2::execute)
    );
  }

  @Test
  void testToString() {
    assertAll(
            () -> assertEquals("2 add 0 0 0", m.getProg().get(1).toString()),
            () -> assertEquals("4 add 1 0 1", m.getProg().get(3).toString()),
            () -> assertEquals("MUL add 1 0 1", m0.getProg().get(3).toString()),
            () -> assertEquals("r add 3 1 2", m1.getProg().get(5).toString())
    );
  }


}