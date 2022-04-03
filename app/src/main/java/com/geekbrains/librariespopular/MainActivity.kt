package com.geekbrains.librariespopular

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.librariespopular.login.LoginContract
import com.geekbrains.librariespopular.login.LoginFragment
import com.geekbrains.librariespopular.login.LoginPresenter

class MainActivity : AppCompatActivity() {

    private var presenter: LoginContract.Presenter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerMainActivity, LoginFragment.newInstance())
            .commitNow()

            filterConnection()
            restorePresenter()
    }
}
    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    private fun filterConnection() {
    val filterConnection = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(networkStateReceiver, filterConnection)
}

private var networkStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val noConnectivity =
            intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
        if (!noConnectivity) {
            onConnectionFound()
        } else {
            onConnectionLost()
        }
    }
}

fun onConnectionLost() {
    //Toast.makeText(this, "Connection LOST", Toast.LENGTH_SHORT).show()
}

fun onConnectionFound() {
    //Toast.makeText(this, "Connection YES", Toast.LENGTH_SHORT).show()
}
}