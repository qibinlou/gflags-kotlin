package examples;

import gflags.Defines;
import gflags.FlagValues;
import gflags.FlagValuesKt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BasicUsage {

    @Test
    public void testBasicUsage() {
        FlagValues FLAGS = new FlagValues();

        Defines.DEFINE_string("name", "Leo", "Name of the user", FLAGS);
        Defines.DEFINE_string("hello", "ssdsd", "Name of the user", FLAGS);


        String[] args = {"--name=John", "--hello=Doe"};
        FLAGS.parseArgv(args);

        assertEquals("John", FLAGS.getFlag("name").getValue());
        assertEquals("Doe", FLAGS.getFlag("hello").getValue());

    }

    @Test
    public void testFlagIsolation() {
        FlagValues FLAGS = FlagValuesKt.FLAGS;
        assertFalse(FLAGS.hasFlag("name"));
        assertFalse(FLAGS.hasFlag("hello"));
    }

}