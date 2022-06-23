package sml.instructions;

import sml.Instruction;
import sml.Machine;

/**
 * This class represents the Out instruction from the SML language.
 *
 * @author Josh Waterson
 */
public class BnzInstruction extends Instruction {
    /**
     * Denotes type of SML instruction.
     */
    private static final String TYPE = "bnz";
    /**
     * represents the register whose value will be evaluated.
     */
    private final int register;
    /**
     * represents the label of the instruction to which execution
     * will defer if register's value is not zero.
     */
    private final String destinationLabel;

    /**
     * @param label - label
     * @param register - register
     * @param destinationLabel - label of next instruction if condition met
     */
    public BnzInstruction(final String label, final int register,
                          final String destinationLabel) {
        super(label, TYPE);
        this.register = register;
        this.destinationLabel = destinationLabel;
    }

    @Override
    public void execute(final Machine m) {
        if (m.getRegisters().getRegister(register) != 0) {
            for (Instruction i : m.getProg()) {
                if (i.getLabel().equals(destinationLabel)) {
                    m.setPc(m.getProg().indexOf(i));
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s",
                this.getLabel(), this.getOpcode(),
                this.register, this.destinationLabel);
    }
}
