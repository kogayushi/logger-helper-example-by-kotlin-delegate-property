package ktlog.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KProperty

class KtLog {
    var log: Logger? = null

    operator fun getValue(thisRef: Any, property: KProperty<*>): Logger {
        return log ?: LoggerFactory.getLogger(
            thisRef.javaClass.name.let {
                val matchIndex = it.length - 10
                when (it.lastIndexOf("\$Companion")) {
                    matchIndex -> it.substring(0, matchIndex)
                    else -> it
                }
            }
        ).apply { this@KtLog.log = this }
    }
}
