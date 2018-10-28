package gflags

import gflags.exceptions.ConflictFlagNameException

typealias AnyFlag = Flag<*, *>

/**
 * Singleton container of flags
 */
object FlagValues {
    /**
     * Alias for FlagValues singleton object.
     */
    val FLAGS = FlagValues

    private val flags: MutableMap<String, AnyFlag> = hashMapOf()

    fun registerFlag(flag: AnyFlag) {
        if (flags.containsKey(flag.name)) {
            throw ConflictFlagNameException(flag.name)
        }
        flags[flag.name] = flag
    }

    fun getFlagValue(name: String): AnyFlag {
        return flags[name]!!
    }

    fun hasFlag(name: String): Boolean {
        return flags.containsKey(name)
    }

}