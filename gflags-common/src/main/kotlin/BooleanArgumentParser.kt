package gflags

import gflags.exceptions.IllegalFlagValueException

internal class BooleanArgumentParser : ArgumentParser<Boolean> {

    private companion object {
        val TRUE_LITERALS = setOf("true", "t", "1")
        val FALSE_LITERALS = setOf("false", "f", "0")
    }

    override fun parse(argument: String): Boolean {
        val lowercaseArgument = argument.toLowerCase()
        if (TRUE_LITERALS.contains(lowercaseArgument)) {
            return true
        }
        if (FALSE_LITERALS.contains(lowercaseArgument)) {
            return false
        }
        throw IllegalFlagValueException("$argument is not a valid boolean value.")
    }

    override fun getFlagType(): FlagType {
        return FlagType.BOOLEAN
    }
}