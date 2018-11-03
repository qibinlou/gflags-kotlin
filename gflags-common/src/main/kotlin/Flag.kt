package gflags

class Flag<T, P : ArgumentParser<T>>(
    val name: String,
    private val default: T,
    private val parser: P,
    val help: String? = null,
    val shortName: String? = null
) {

    private var _value: T? = null
    var value: T
        get() {
            if (_value == null) {
                return default
            }
            return _value!!
        }
        set(value) {
            _value = value
        }

    fun parse(argument: String): T {
        _value = parser.parse(argument)
        return _value!!
    }

    internal fun getFlagType(): FlagType {
        return FlagType.STRING
    }


}