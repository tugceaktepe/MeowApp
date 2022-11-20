package com.aktepetugce.onedayofacat.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class DailyRoutine(
    @StringRes val title : Int,
    @DrawableRes val image : Int,
    @StringRes val description : Int
)