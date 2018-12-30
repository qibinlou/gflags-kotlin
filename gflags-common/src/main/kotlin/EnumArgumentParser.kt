package gflags

import gflags.exceptions.IllegalFlagValueException

internal class EnumArgumentParser(private val enumValues: Set<String>) : ArgumentParser<String> {
    override fun parse(argument: String): String {
        if (!enumValues.contains(argument)) {
            throw IllegalFlagValueException("$argument is not valid enum value. Possible enum values are: $enumValues")
        }
        return argument
    }

    override fun getFlagType(): FlagType {
        return FlagType.ENUM
    }

}