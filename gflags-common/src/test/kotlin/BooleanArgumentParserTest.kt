import gflags.BooleanArgumentParser
import gflags.FlagType
import gflags.exceptions.IllegalFlagValueException
import kotlin.test.*

class BooleanArgumentParserTest {

    @Test
    fun parse_trueStrings() {
        val parser = BooleanArgumentParser()
        assertTrue(parser.parse("true"))
        assertTrue(parser.parse("True"))
        assertTrue(parser.parse("TRUE"))
        assertTrue(parser.parse("t"))
        assertTrue(parser.parse("1"))
    }

    @Test
    fun parse_falseStrings() {
        val parser = BooleanArgumentParser()
        assertFalse(parser.parse("false"))
        assertFalse(parser.parse("False"))
        assertFalse(parser.parse("FALSE"))
        assertFalse(parser.parse("f"))
        assertFalse(parser.parse("0"))
    }

    @Test
    fun parse_inValidBooleanStrings() {
        assertInvalidBooleanString("ture")
        assertInvalidBooleanString("1.0")
        assertInvalidBooleanString("0000")
        assertInvalidBooleanString("0.0")
        assertInvalidBooleanString("falsee")
        assertInvalidBooleanString("null")
        assertInvalidBooleanString("NULL")
    }

    @Test
    fun getFlagType() {
        val parser = BooleanArgumentParser()
        assertEquals(FlagType.BOOLEAN, parser.getFlagType())
    }

    private companion object {
        fun assertInvalidBooleanString(value: String) {
            try {
                val parser = BooleanArgumentParser()
                parser.parse(value)
                fail("It should fail.")
            } catch (e: IllegalFlagValueException) {
                assertTrue(e.message!!.contains(value))
            }
        }
    }
}