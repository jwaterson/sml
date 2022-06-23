package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents the Add instruction from the language.
 *
 * @author Josh Waterson
 */
public class AddInstruction extends Instruction {
    /**
     * Denotes type of SML instruction.
     */
    private static final String TYPE = "add";
    /**
     * Denotes the register to which result of operation will be stored.
     */
    private final int register;
    /**
     * Denotes first of the operation's two operands.
     */
    private final int s1;
    /**
     * Denotes second of the operation's two operands.
     */
    private final int s2;

    /**
     * @param label - label
     * @param register - register
     * @param s1 - operand
     * @param s2 - operand
     */
    public AddInstruction(final String label, final int register,
                          final int s1, final int s2) {
        super(label, TYPE);
        this.register = register;
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void execute(final Machine m) {
        m.getRegisters().setRegister(register, m.getRegisters().getRegister(s1)
                + m.getRegisters().getRegister(s2));
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", this.getLabel(),
                this.getOpcode(), this.register, this.s1, this.s2);
    }
}
