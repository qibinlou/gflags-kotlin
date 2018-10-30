package examples;

import gflags.Defines;
import org.junit.Assert;
import org.junit.Test;

import static gflags.FlagValuesKt.FLAGS;

public class BasicUsage {

    @Test
    public void testBasicUsage() {
        Defines.DEFINE_string("name", "Leo", "Name of the user");
        Defines.DEFINE_string("hello", "ssdsd", "Name of the user");


        String[] args = {"--name=John", "--hello=Doe"};
        FLAGS.parseArgv(args);

        Assert.assertEquals("John", FLAGS.getFlagValue("name").getValue());
        Assert.assertEquals("Doe", FLAGS.getFlagValue("hello").getValue());

    }

}