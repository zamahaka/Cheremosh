package org.zamahaka.cheremosh.ui.alert

import android.content.Context
import android.content.DialogInterface
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

data class AlertData(val title: String, val message: String)

fun Context.showOkAlert(data: AlertData, okHandler: (dialog: DialogInterface) -> Unit = {}) = alert {
    message = data.message
    title = data.title
    okButton(okHandler)
}.show()

