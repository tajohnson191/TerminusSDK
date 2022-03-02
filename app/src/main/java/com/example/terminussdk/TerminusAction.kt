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
}