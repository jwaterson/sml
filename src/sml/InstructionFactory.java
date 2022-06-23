package sml;

import sml.exceptions.IllegalInstructionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * This Singleton class creates instructions. There should only be one
 * instance of this class throughout the lifecycle of an SML interpreter.
 *
 * @author Josh Waterson
 */
public class InstructionFactory {

    private static final InstructionFactory instance;
    private final Properties props; // set of key-value pairs

    static {
        instance = new InstructionFactory();
    }

    /**
     * Constructor: Initialises a stream containing contents of
     * beans.properties
     */
    private InstructionFactory() {
        props = new Properties();
        try (FileInputStream inputStream = new FileInputStream("bean.properties")) {
            props.load(inputStream); // {add=instructions.AddInstruction, ...}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InstructionFactory getInstance() {
        return instance;
    }

    public Instruction getInstruction(Class<?> instructionType, Object[] params) {
        try {
            return (Instruction) instructionType.getConstructors()[0].newInstance(params);
        } catch (InstantiationException |
                InvocationTargetException | IllegalAccessException e) {
            throw new IllegalInstructionException();
        }
    }

    public Class<?> getType(String type) {
        try {
            return Class.forName(props.getProperty(type));
        } catch (ClassNotFoundException | NullPointerException e) {
            throw new IllegalInstructionException("No " + type + " instruction in SML");
        }
    }

}
