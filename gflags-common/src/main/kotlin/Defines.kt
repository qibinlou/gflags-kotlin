package gflags


import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

object Defines {

    @JvmStatic
    @Suppress("FunctionName")
    fun <T, P : ArgumentParser<T>> DEFINE_flag(flag: Flag<T, P>, flagValues: FlagValues = FLAGS) {
        flagValues.registerFlag(flag)
    }

    @JvmStatic
    fun <T, P : ArgumentParser<T>> DEFINE(
        parser: P,
        name: String,
        default: T,
        help: String? = null,
        flagValues: FlagValues = FLAGS
    ) {
        DEFINE_flag(Flag(name, default, parser, help = help), flagValues)
    }

    @JvmStatic
    @JvmOverloads
    fun DEFINE_string(name: String, default: String, help: String? = null, flagValues: FlagValues = FLAGS) {
        DEFINE(Parsers.STRING_PARSER, name, default, help, flagValues)
    }

    @JvmStatic
    @JvmOverloads
    fun DEFINE_boolean(name: String, default: Boolean, help: String? = null, flagValues: FlagValues = FLAGS) {
        DEFINE(Parsers.BOOLEAN_PARSER, name, default, help, flagValues)
    }

    @JvmStatic
    @JvmOverloads
    fun DEFINE_float(
        name: String,
        default: Float,
        help: String,
        lowerBound: Float? = null,
        upperBound: Float? = null,
        flagValues: FlagValues = FLAGS
    ) {
        DEFINE(Parsers.FLOAT_PARSER, name, default, help, flagValues)
    }

    @JvmOverloads
    @JvmStatic
    fun DEFINE_integer(
        name: String,
        default: Int,
        help: String,
        lowerBound: Int? = null,
        upperBound: Int? = null,
        flagValues: FlagValues = FLAGS
    ) {
        val parser = IntegerArgumentParser(lowerBound, upperBound)
        DEFINE_flag(Flag(name, default, parser, help), flagValues)
    }

    @JvmOverloads
    @JvmStatic
    fun DEFINE_enum(
        name: String,
        default: String,
        help: String,
        enumValues: Set<String>,
        flagValues: FlagValues = FLAGS
    ) {
        val parser = EnumArgumentParser(enumValues)
        DEFINE_flag(Flag(name, default, parser, help), flagValues)
    }

    @JvmStatic
    fun declare_key_flag(flagName: String, flagValues: FlagValues = FLAGS) {
        TODO()
    }

}