package com.example.terminussdk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

/**
 * A Terminus Action that can happen. Each [TerminusAction] is associated with a [TerminusIntent]. This is useful
 * for easy sending and consuming of intents around Terminus.
 */
sealed class TerminusAction(val intent: TerminusIntent) {
    /**
     * The list of potential arguments the [TerminusAction] may have that you wish to send or receive
     * through the intent. The [send] function handles many types of data that we can use for
     * intents.
     */
    open val actionExtras: List<Pair<String, *>> = emptyList()

    fun send(context: Context?, appId: String) {
        val intent = Intent(intent.value).apply {
            addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            // a way of securing the intent a little bit, only offering the broadcast back to the
            // given [appId]
            setPackage(appId)
            // used to log what app sent what intent
            putExtra("sender", appId)
            actionExtras.forEach {
                when (val value = it.second) {
                    is Boolean -> putExtra(it.first, value)
                    is Byte -> putExtra(it.first, value)
                    is Int -> putExtra(it.first, value)
                    is Long -> putExtra(it.first, value)
                    is Float -> putExtra(it.first, value)
                    is Double -> putExtra(it.first, value)
                    is String -> putExtra(it.first, value)
                    is Parcelable -> putExtra(it.first, value)
                    is ByteArray -> putExtra(it.first, value)
                    is Bundle -> putExtra(it.first, value)
                    else -> {/* no-op */
                    }
                }
            }
        }
        context?.sendBroadcast(intent)
    }
}

// PaymentTerminal status
object ActionTerminusStatusRequest : TerminusAction(TerminusIntent.TerminusStatusRequest)

data class ActionTerminusResponse(
    val connected: Boolean
) : TerminusAction(TerminusIntent.TerminusStatusResponse) {
    override val actionExtras: List<Pair<String, *>> =
        listOf(::connected.name to connected)
}