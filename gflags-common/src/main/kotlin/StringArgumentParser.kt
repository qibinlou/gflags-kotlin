package gflags

internal class StringArgumentParser : ArgumentParser<String> {
    override fun parse(argument: String): String {
        return argument
    }

    override fun getFlagType(): FlagType {
        return FlagType.STRING
    }
}