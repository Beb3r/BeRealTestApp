package com.gberanger.berealtestapp.common.extensions

import java.util.Base64

fun String.toBase64() =
    Base64.getEncoder().encodeToString(this.toByteArray(charset("UTF-8")))