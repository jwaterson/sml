package sml.instructions;

import sml.Instruction;
import sml.Machine;

import java.io.ByteArrayOutputStream;

/**
 * This class represents the Out instruction from the language.
 *
 * @author Josh Waterson
 */
public class OutInstruction extends Instruction {
    /**
     * Denotes type of SML instruction.
     */
    private static final String TYPE = "out";
    /**
     * represents the register whose value will be evaluated.
     */
    private final int register;

    /**
     * @param label - label
     * @param register - register whose value is to be output
     */
    public OutInstruction(final String label, final int register){
        super(label, TYPE);
        this.register = register;
    }

    @Override
    public void execute(final Machine m) {
        System.out.print(m.getRegisters().getRegister(register) + "\n");
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",
                this.getLabel(), this.getOpcode(), this.register);
    }
}
