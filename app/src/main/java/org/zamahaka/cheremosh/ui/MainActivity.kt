package org.zamahaka.cheremosh.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.jetbrains.anko.toast
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.model.Event
import org.zamahaka.cheremosh.model.Location
import org.zamahaka.cheremosh.ui.timeline.TimeLineActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if (FirebaseAuth.getInstance().currentUser != null) {
            TimeLineActivity.launch(this)
        } else {
            val loginIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setTheme(AuthUI.getDefaultTheme())
                    .setLogo(AuthUI.NO_LOGO)
                    .setAvailableProviders(getLoginProviders())
                    .setIsSmartLockEnabled(true)
                    .setAllowNewEmailAccounts(true)
                    .build()

            startActivityForResult(loginIntent, RQC_SIGN_IN)
        }

    }

    private fun fetchTest() {
        FirebaseDatabase.getInstance().reference
                .child("test")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        println(p0)

                        val list = p0.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Location>>() {})

                        println(list)

                        fetchEvents()
                    }
                })
    }

    private fun fetchEvents() {
        FirebaseDatabase.getInstance().reference
                .child("events")
                .child(/*timestamp*/1509140624L.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        println(p0)

                        val list = p0.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards Event>>() {})

                        println(list)
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RQC_SIGN_IN -> data?.let { handleSignInResponse(resultCode, it) }
        }
    }

    private fun getLoginProviders(): List<AuthUI.IdpConfig> {
        val providers = mutableListOf<AuthUI.IdpConfig>()

        providers += AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER)
                .build()

        providers += AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER)
                .build()

        return providers
    }

    private fun handleSignInResponse(resultCode: Int, data: Intent) {
        val response = IdpResponse.fromResultIntent(data)

        if (resultCode == RESULT_OK) {
            TimeLineActivity.launch(this)
            return
        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                toast("Sign in canceled")
                return
            }

            if (response.errorCode == ErrorCodes.NO_NETWORK) {
                toast("No internet")
                return
            }

            if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                toast("Unknown error")
                return
            }
        }

        toast("unknown_sign_in_response")
    }


    companion object {
        private const val RQC_SIGN_IN = 100
    }

}
