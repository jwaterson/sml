package sml;

import java.util.Objects;

/**
 * This class represents an abstract instruction
 *
 * @author ...
 */
public abstract class Instruction {
  private String label;
  private String opcode;

  /**
   * Constructor: an instruction with label l and opcode op
   * (op must be an operation of the language)
   *
   * @param l  label
   * @param op operand
   */
  public Instruction(String l, String op) {
    setLabel(l);
    setOpcode(op);
  }

  public final String getLabel() {
    return label;
  }

  public final void setLabel(String lab) {
    this.label = lab;
  }

  public final String getOpcode() {
    return opcode;
  }

  public final void setOpcode(String op) {
    this.opcode = op;
  }

  public abstract void execute(Machine m);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Instruction other = (Instruction) o;
    return Objects.equals(label, other.label) && Objects.equals(opcode, other.opcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, opcode);
  }

  @Override
  public String toString() {
    return getLabel() + ": " + getOpcode();
  }

}
