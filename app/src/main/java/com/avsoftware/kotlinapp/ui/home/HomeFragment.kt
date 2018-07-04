package com.avsoftware.kotlinapp.ui.home

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.HomeFragmentBinding
import com.firebase.ui.auth.AuthUI
import io.reactivex.disposables.CompositeDisposable
import rx_activity_result2.Result
import rx_activity_result2.RxActivityResult
import timber.log.Timber
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser




class HomeFragment : Fragment() {

    companion object {
        const val RC_SIGN_IN = 123
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var viewBinding: HomeFragmentBinding

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewBinding = HomeFragmentBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel


        viewBinding.recipeBtn.setOnClickListener { _ ->
            val nav = NavHostFragment.findNavController(this)
            nav.navigate(R.id.recipeSearchFragment)
        }

        viewBinding.authenticationButton.setOnClickListener { _ ->
            launchAuthenticationFlow()
        }
    }

    private fun launchAuthenticationFlow() {

        // Choose authentication providers
        val providers = listOf(AuthUI.IdpConfig.EmailBuilder().build())

//                Arrays.asList(
//                AuthUI.IdpConfig.EmailBuilder().build(),
//                AuthUI.IdpConfig.PhoneBuilder().build(),
//                AuthUI.IdpConfig.GoogleBuilder().build(),
//                AuthUI.IdpConfig.FacebookBuilder().build(),
//                AuthUI.IdpConfig.TwitterBuilder().build())

        // Create and launch sign-in intent

        val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

        //startActivityForResult(intent, RC_SIGN_IN)
        disposable.add(
        RxActivityResult.on(this).startIntent(intent)
                .subscribe { t: Result<HomeFragment>? ->
                    if (t != null) {
                        handleSubscribeResult(t)
                    }
                })
    }

    private fun handleSubscribeResult(result: Result<HomeFragment>) {
        Timber.d("Got Result Data: ${result.data()?.extras ?: "none" }")

        if (result.resultCode() == RESULT_OK) {
            // Successfully signed in
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: 0

            Timber.d("Successful Authentication: $userId")
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            Timber.d("Authentication Failed")
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
