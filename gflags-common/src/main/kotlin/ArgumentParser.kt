package gflags

interface ArgumentParser<T> {
    fun parse(argument: String): T
    fun getFlagType(): FlagType
}