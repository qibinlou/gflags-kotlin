package gflags

import gflags.exceptions.IllegalFlagValueException

internal class FloatArgumentParser : ArgumentParser<Float> {
    override fun parse(argument: String): Float {
        try {
            return argument.toFloat()
        } catch (e: NumberFormatException) {
            throw IllegalFlagValueException("$argument is not a valid float value.")
        }
    }

    override fun getFlagType(): FlagType {
        return FlagType.FLOAT
    }
}