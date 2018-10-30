package gflags

class Flag<T, P : ArgumentParser<T>>(
    val name: String,
    val default: T,
    private val parser: P,
    var value: T? = null,
    val help: String? = null,
    val shortName: String? = null
) {

    fun parse(argument: String): T {
        value = parser.parse(argument)
        return value!!
    }

    internal fun getFlagType(): FlagType {
        return FlagType.STRING
    }


}