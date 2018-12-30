package examples;

import gflags.Defines;
import gflags.FlagValues;
import gflags.FlagValuesKt;
import gflags.exceptions.IllegalFlagValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class BasicUsage {

    @Test
    public void testStringFlag() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_string("name", "Leo", "Name of the user", FLAGS);
        Defines.DEFINE_string("hello", "ssdsd", "Name of the user", FLAGS);


        String[] args = {"--name=John", "--hello=Doe"};
        FLAGS.parseArgv(args);

        assertEquals("John", FLAGS.getFlag("name").getValue());
        assertEquals("Doe", FLAGS.getFlag("hello").getValue());

    }

    @Test
    public void testBooleanFlag() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_boolean("dry_run", true, "Dry run mode flag", FLAGS);
        Defines.DEFINE_boolean("verbose", false, "Verbose log flag", FLAGS);
        Defines.DEFINE_boolean("testOnly", false, "test only flag", FLAGS);
        Defines.DEFINE_boolean("default_boolean", false, "default boolean flag", FLAGS);
        Defines.DEFINE_boolean("parallel", true, "paralel boolean flag", FLAGS);
        Defines.DEFINE_boolean("nobody", false, "a flag name with prefix no", FLAGS);


        String[] args = {"--dry_run=false", "--verbose=t", "--testOnly", "--noparallel", "--nobody=true"};
        FLAGS.parseArgv(args);

        assertEquals(false, FLAGS.getFlag("dry_run").getValue());
        assertEquals(true, FLAGS.getFlag("verbose").getValue());
        assertEquals(true, FLAGS.getFlag("testOnly").getValue());
        assertEquals(false, FLAGS.getFlag("default_boolean").getValue());
        assertEquals(false, FLAGS.getFlag("parallel").getValue());
        assertEquals(true, FLAGS.getFlag("nobody").getValue());
    }

    @Test
    public void testIntegerFlag() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_integer("workers", 1, "worker thread pool size", null, null, FLAGS);
        Defines.DEFINE_integer("decreaseStep", -5, "negative value", null, null, FLAGS);

        String[] args = {"--workers=4", "--decreaseStep=-1"};
        FLAGS.parseArgv(args);

        assertEquals(4, FLAGS.getFlag("workers").getValue());
        assertEquals(-1, FLAGS.getFlag("decreaseStep").getValue());

    }

    @Test
    public void testIntegerFlag_invalidFloatValue() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_integer("workers", 1, "worker thread pool size", null, null, FLAGS);

        String[] args = {"--workers=4.0"};
        IllegalFlagValueException exception = assertThrows(IllegalFlagValueException.class, () -> FLAGS.parseArgv(args));
        assertTrue(exception.getMessage().contains("4.0"));
    }

    @ParameterizedTest(name = "\"{0}\" should be parsed into integer {0}")
    @ValueSource(strings = {"1", "2", "7", "8"})
    public void testIntegerFlag_inBoundaryValue(String arg) {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_integer("workers", 1, "worker thread pool size", 1, 8, FLAGS);

        String[] args = {"--workers=" + arg};
        FLAGS.parseArgv(args);

        assertEquals(Integer.parseInt(arg), FLAGS.getFlag("workers").getValue());
    }

    @ParameterizedTest(name = "\"{0}\" should cause parsing error")
    @ValueSource(strings = {"-1", "0", "9", "2147483647"})
    public void testIntegerFlag_outOfBoundaryValue(String arg) {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_integer("workers", 1, "worker thread pool size", 1, 8, FLAGS);

        String[] args = {"--workers=" + arg};
        IllegalFlagValueException exception = assertThrows(IllegalFlagValueException.class, () -> FLAGS.parseArgv(args));
        assertTrue(exception.getMessage().contains(arg));
        assertTrue(exception.getMessage().contains("1") || exception.getMessage().contains("8"));
    }

    @Test
    public void testEnumFlag_defaultEnumValue() {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_enum("color", "RED", "color enum", new HashSet<>(Arrays.asList("RED", "GREEN", "BLUE")), FLAGS);
        FLAGS.parseArgv(new String[0]);
        assertEquals("RED", FLAGS.getFlag("color").getValue());
    }

    @Test
    public void testEnumFlag_validEnumArg() {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_enum("color", "RED", "color enum", new HashSet<>(Arrays.asList("RED", "GREEN", "BLUE")), FLAGS);
        FLAGS.parseArgv(new String[]{"--color=BLUE"});
        assertEquals("BLUE", FLAGS.getFlag("color").getValue());
    }

    @Test
    public void testEnumFlag_invalidEnumArg() {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_enum("color", "RED", "color enum", new HashSet<>(Arrays.asList("RED", "GREEN", "BLUE")), FLAGS);
        assertThrows(IllegalFlagValueException.class, () -> FLAGS.parseArgv(new String[]{"--color=BLUES"}));
    }

    @Test
    public void testEnumFlag_caseSensitiveEnumParser() {
        FlagValues FLAGS = new FlagValues();
        Defines.DEFINE_enum("color", "RED", "color enum", new HashSet<>(Arrays.asList("RED", "GREEN", "BLUE")), FLAGS);
        assertThrows(IllegalFlagValueException.class, () -> FLAGS.parseArgv(new String[]{"--color=green"}));
    }

    @Test
    public void testFlagIsolation() {
        FlagValues FLAGS = FlagValuesKt.FLAGS;
        assertFalse(FLAGS.hasFlag("name"));
        assertFalse(FLAGS.hasFlag("hello"));
    }

}