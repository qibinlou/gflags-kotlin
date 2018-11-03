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

    fun getFlag(name: String): AnyFlag {
        return flags[name]!!
    }

    fun hasFlag(name: String): Boolean {
        return flags.containsKey(name)
    }

    fun parseArgv(argv: Array<String>) {
        argv.filter { it.startsWith("--") }
            .map { it.substring(2) }
            .map { it -> parseArgString(it) }
            .forEach {
                getFlag(it.first).parse(it.second.orEmpty())
            }
    }

    companion object {
        private fun parseArgString(arg: String): Pair<String, String?> {
            if (!arg.contains("=")) {
                if (arg.startsWith("no") && arg.length > 2) {
                    return Pair(arg.substring(2), "false")
                } else {
                    return Pair(arg, "true")
                }
            }
            val key = arg.split("=")[0]
            val parts = arg.split("=")
            val value = if (parts.size == 1) null else parts[1]
            return Pair(key, value)
        }
    }
}

@JvmField
val FLAGS = FlagValues()
