package gflags

import gflags.exceptions.ConflictFlagNameException
import kotlin.jvm.JvmField

typealias AnyFlag = Flag<*, *>

/**
 * Singleton container of flags
 */
class FlagValues {

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

    fun parseArgv(argv: Array<String>) {
        argv.map {
            val key = it.split("=")[0].substring(2)
            val parts = it.split("=")
            val value = if (parts.size == 1) null else parts[1]
            Pair(key, value)
        }.forEach {
            getFlagValue(it.first).parse(it.second.orEmpty())
        }
    }
}

@JvmField
val FLAGS = FlagValues()
