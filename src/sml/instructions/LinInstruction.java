package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents the Lin instruction from the language.
 *
 * @author Josh Waterson
 */
public class LinInstruction extends Instruction {
    /**
     * Denotes type of SML instruction.
     */
    private static final String TYPE = "lin";
    /**
     * Denotes the register to which result of operation will be stored.
     */
    private final int register;
    /**
     * Denotes first of the operation's two operands.
     */
    private final int s1;

    /**
     * @param label - label
     * @param register - register
     * @param s1 - value to be loaded
     */
    public LinInstruction(final String label, final int register,
                          final int s1) {
        super(label, TYPE);
        this.register = register;
        this.s1 = s1;
    }

    @Override
    public void execute(final Machine m) {
        m.getRegisters().setRegister(register, s1);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s",
                this.getLabel(), this.getOpcode(), this.register, this.s1);
    }
}
