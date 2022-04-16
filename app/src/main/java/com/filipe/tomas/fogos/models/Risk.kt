package com.filipe.tomas.fogos.models

import com.filipe.tomas.fogos.R

enum class Risk(val value: Int) {
    Lower(R.string.lower),
    Moderated(R.string.moderated),
    High(R.string.high),
    VeryHigh(R.string.veryHigh),
    Max(R.string.max)
}
