package sml;

import sml.exceptions.IllegalInstructionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

/**
 * This class receives the path of the file that will be translated into an SML program.
 *
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Josh Waterson
 */
public final class Translator {
    private static final String PATH = "";
    private final String fileName; // source file of SML code
    private String line = "";

    public Translator(String file) {
        fileName = PATH + file;
    }

    /**
     * translate the small program in the file into lab (the labels) and
     * prog (the program)
     * return "no errors were detected"
     */
    public boolean readAndTranslate(Labels lab, List<Instruction> prog) {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            lab.reset();
            prog.clear();

            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ioE) {
                return false;
            }

            while (line != null) {
                String label = scan();

                if (lab.indexOf(label) != -1) { // check label isn't already in labels
                    throw new IllegalInstructionException("Labels must be unique!");
                }

                if (label.length() > 0) {
                    Instruction ins = getInstruction(label);

                    if (ins != null) {
                        lab.addLabel(label);
                        prog.add(ins);
                    }
                }

                try {
                    line = sc.nextLine();
                } catch (NoSuchElementException ioE) {
                    return false;
                }
            }
        } catch (IOException ioE) {
            System.err.println("File: IO error " + ioE);
            return false;
        }
        return true;
    }

    /**
     The input line should consist of an SML instruction, with its label already removed.
     Translate line into an instruction with label "label" and return the instruction
    */
    public Instruction getInstruction(String label) {
        if (line.equals("")) {
            return null;
        }
        try {
            Class<?> instructionType = InstructionFactory.getInstance().getType(scan());
            Class<?>[] paramTypes = instructionType.getConstructors()[0].getParameterTypes();
            Object[] params = Stream.concat(Stream.of(label),
                            Arrays.stream(Arrays.stream(paramTypes).toArray(Class<?>[]::new),
                                            1, paramTypes.length)
                                    .map(i -> i.getName().equals("int") ||
                                            i.getName().equals("java.lang.Integer") ? scanInt() : scan()))
                    .toArray();
            return InstructionFactory.getInstance().getInstruction(instructionType, params);
        } catch (IllegalInstructionException e) {
            System.err.println(line + " ");
            throw new IllegalInstructionException();
        }
    }

    /**
     * Returns the first word (contiguous sequence of non-whitespace characters)
     * of line and removes it from line. If there is no word, returns, ""
     */
    private String scan() {
        line = line.trim();
        if (line.length() == 0) {
            return "";
        }

        int i = 0;
        while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
            i = i + 1;
        }
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
    }

    /**
     * Return the first word (contiguous sequence of non-whitespace characters) of line
     * as an integer. If there is any error, return the maximum int
     */
    private int scanInt() {
        String word = scan();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}