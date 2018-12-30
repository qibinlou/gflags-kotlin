package gflags

import gflags.exceptions.IllegalFlagValueException

internal class IntegerArgumentParser(private val lowerBound: Int?, private val upperBound: Int?) : ArgumentParser<Int> {
    override fun parse(argument: String): Int {
        try {
            return checkBoundary(argument.toInt())
        } catch (e: NumberFormatException) {
            throw IllegalFlagValueException("$argument is not a valid integer value.")
        }

    }

    override fun getFlagType(): FlagType {
        return FlagType.INTEGER
    }

    private fun checkBoundary(value: Int): Int {
        if (lowerBound != null && value < lowerBound) {
            throw IllegalFlagValueException("$value is smaller than lowerBound $lowerBound")
        }
        if (upperBound != null && value > upperBound) {
            throw IllegalFlagValueException("$value is larger than upperBound $upperBound")
        }
        return value
    }
}