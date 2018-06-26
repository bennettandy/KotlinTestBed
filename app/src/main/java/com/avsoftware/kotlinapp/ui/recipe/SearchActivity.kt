package com.avsoftware.kotlinapp.ui.recipe

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.ActivityRecipeSearchBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import timber.log.Timber
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject lateinit var mViewModel: SearchActivityViewModel

    private var mDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var mViewBinding: ActivityRecipeSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDisposable = CompositeDisposable()

        val appComponent = KotlinApp.graph
        appComponent.inject(this)

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_search)
        mViewBinding.setLifecycleOwner(this)

        bindViewComponents()
    }

    private fun bindViewComponents(): View {

        mViewBinding.viewModel = mViewModel
        mViewBinding.activity = this
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        // connect search
        mDisposable.add(mViewModel.connectSearch()
                .doOnError(Timber::e)
                .subscribe())

        // Live Data, pins observer to Activity lifecycle, cleans up self
        //        mViewModel.getRecipeClickedLiveData().observe(this, recipeWrapper -> {
        //            Timber.d("Recipe Clicked");
        //            if (recipeWrapper != null && recipeWrapper.getDetails() != null) {
        //                Intent i = RecipeActivity.newIntent(recipeWrapper.getDetails(), SearchActivity.this);
        //                startActivity(i);
        //            }
        //        });

        //        mDisposable.add(RxView.clicks(mViewBinding.purchaseButton)
        //                .throttleFirst(1, TimeUnit.SECONDS)
        //                .doOnNext(o -> mSubsViewModel.purchaseSubscription(this, "monthly_subscription_1"))
        //                .subscribe());
        //
        //        mDisposable.add(RxView.clicks(mViewBinding.launchSubscriptionManager)
        //                .throttleFirst(1, TimeUnit.SECONDS)
        //                .doOnNext(o -> mSubsViewModel.launchUserSubscriptionsjourney(this))
        //                .subscribe());
        //
        //        mDisposable.add(RxView.clicks(mViewBinding.billingFlowButton)
        //                .throttleFirst(1, TimeUnit.SECONDS)
        //                .map(__ -> AppSubscriptionActivity.newIntent(this))
        //                .doOnNext(this::startActivity)
        //                .subscribe());

        //        mDisposable.add(RxView.clicks(mViewBinding.launchQrcode)
        //                .throttleFirst(1, TimeUnit.SECONDS)
        //                .doOnNext(this::launchQRReader)
        //                .subscribe());
        //
        //        mDisposable.add(RxView.clicks(mViewBinding.launchChild)
        //                .throttleFirst(1, TimeUnit.SECONDS)
        //                .map(__ -> new Intent(this, DashboardActivity.class))
        //                .doOnNext(this::startActivity)
        //                .subscribe());

        // Refactor into custom binder?
        mViewBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mViewModel!!.searchTrigger.accept(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mViewModel!!.queryText.accept(newText)
                return true
            }
        })

        return mViewBinding.root
    }

    //    public void launchQRReader(Object o) {
    //        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance
    //        mDisposable.add(
    //                rxPermissions
    //                        .request(android.Manifest.permission.CAMERA)
    //                        .observeOn(AndroidSchedulers.mainThread())
    //                        .doOnNext(granted -> {
    //                            if (granted) { // Always true pre-M
    //                                // We have Camera permission
    //                                Intent qrIntent = new Intent(this, QRCodeActivity.class);
    //                                startActivity(qrIntent);
    //                            } else {
    //                                // permission denied
    //                                Timber.w("Camera Permission Missing");
    //                                Toast.makeText(this, getText(R.string.onboarding_error_missing_permission), Toast.LENGTH_LONG).show();
    //                            }
    //                        }).toFlowable(BackpressureStrategy.BUFFER).subscribe(aBoolean -> {}, Timber::e));
    //    }

    //    private void launchCustomTab(Object o) {
    //
    //        String url = "http://www.safetonet.com";
    //        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
    //
    //        builder.setToolbarColor(getResources().getColor(R.color.blue));
    //
    //        CustomTabsIntent customTabsIntent = builder.build();
    //        customTabsIntent.launchUrl(this, Uri.parse(url));
    //
    //    }

    override fun onDestroy() {
        super.onDestroy()
            mDisposable.dispose()
    }
}
