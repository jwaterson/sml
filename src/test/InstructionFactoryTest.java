package test;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.InstructionFactory;
import sml.exceptions.IllegalInstructionException;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFactoryTest {

    private InstructionFactory insFactory;

    @BeforeEach
    void setUp() {
        insFactory = InstructionFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        insFactory = null;
    }

    @Test
    void getInstance() {
        assertNotNull(InstructionFactory.getInstance());
        insFactory = null;
        assertNull(insFactory);


    }

    @Test
    void getInstruction() {
        assertThrows(IllegalInstructionException.class,
                () -> insFactory.getInstruction(
                        Class.forName("sml.Instruction"),
                        new Object[]{null, null}
                ));
    }

    @Test
    void getType() {
        assertThrows(IllegalInstructionException.class,
                () -> insFactory.getType("mod"));
    }
}