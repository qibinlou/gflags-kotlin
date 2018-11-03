package examples;

import gflags.Defines;
import gflags.FlagValues;
import gflags.FlagValuesKt;
import gflags.exceptions.IllegalFlagValueException;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testIntegerFlag_invalidValue() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_integer("workers", 1, "worker thread pool size", null, null, FLAGS);

        String[] args = {"--workers=4.0"};
        try {
            FLAGS.parseArgv(args);
            fail("It should fail.");
        } catch (IllegalFlagValueException e) {
            assertTrue(e.getMessage().contains("4.0"));
        }

    }

    @Test
    public void testFlagIsolation() {
        FlagValues FLAGS = FlagValuesKt.FLAGS;
        assertFalse(FLAGS.hasFlag("name"));
        assertFalse(FLAGS.hasFlag("hello"));
    }

}