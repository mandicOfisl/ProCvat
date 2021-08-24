package hr.algebra.lmandic.procvat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.lmandic.procvat.framework.setBooleanPreference
import hr.algebra.lmandic.procvat.framework.startActivity

class ProcvatReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference("first_run", false)
        context.startActivity<MenuActivity>()
    }
}