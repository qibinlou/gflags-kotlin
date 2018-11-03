package gflags

import gflags.exceptions.IllegalFlagValueException

internal class IntegerArgumentParser : ArgumentParser<Int> {
    override fun parse(argument: String): Int {
        try {
            return argument.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalFlagValueException("$argument is not a valid integer value.")
        }

    }

    override fun getFlagType(): FlagType {
        return FlagType.INTEGER
    }
}