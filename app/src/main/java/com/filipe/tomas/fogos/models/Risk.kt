package com.filipe.tomas.fogos.models

import com.filipe.tomas.fogos.R

enum class Risk(val value: Int) {
    lower(R.string.lower),
    moderated(R.string.moderated),
    high(R.string.high),
    veryHigh(R.string.veryHigh),
    max(R.string.max)
}
