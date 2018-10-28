package gflags

import gflags.FlagValues.FLAGS

object Defines {

    @Suppress("FunctionName")
    fun <T, P : ArgumentParser<T>> DEFINE_flag(flag: Flag<T, P>, flagValues: FlagValues = FLAGS) {
        flagValues.registerFlag(flag)
    }

    fun <T, P : ArgumentParser<T>> DEFINE(parser: P, name: String, default: T, flagValues: FlagValues = FLAGS) {
        DEFINE_flag(Flag(name, default, parser), flagValues)
    }

    fun DEFINE_string(name: String, default: String, help: String, flagValues: FlagValues = FLAGS) {
        TODO()
    }

    fun DEFINE_boolean(name: String, default: Boolean, help: String, flagValues: FlagValues = FLAGS) {
        TODO()
    }

    fun DEFINE_float(
        name: String,
        default: Float,
        help: String,
        lowerBound: Float? = null,
        upperBound: Float? = null,
        flagValues: FlagValues = FLAGS
    ) {
        TODO()
    }

    fun DEFINE_integer(
        name: String,
        default: Int,
        help: String,
        lowerBound: Int? = null,
        upperBound: Int? = null,
        flagValues: FlagValues = FLAGS
    ) {
        TODO()
    }

    fun DEFINE_enum(
        name: String,
        default: String,
        enumValues: Set<String>,
        help: String,
        flagValues: FlagValues = FLAGS
    ) {
        TODO()
    }

    fun declare_key_flag(flagName: String, flagValues: FlagValues = FLAGS) {
        TODO()
    }

}