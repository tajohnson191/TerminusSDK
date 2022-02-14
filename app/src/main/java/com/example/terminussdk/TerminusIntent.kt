package com.example.terminussdk

enum class TerminusIntent(val value: String) {
    TerminusStatusRequest("com.target.stores.mobileptc.STATUS_REQUEST"),//TODO fix these
    TerminusStatusResponse("com.target.stores.mobileptc.STATUS_RESPONSE"),
}