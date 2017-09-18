package org.zamahaka.cheremosh.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import org.jetbrains.anko.toast
import org.zamahaka.cheremosh.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            // Successfully signed in
//            startSignedInActivity(response)
            finish()
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
