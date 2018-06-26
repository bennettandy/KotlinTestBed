package com.avsoftware.kotlinapp.ui.recipe;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;

import com.avsoftware.kotlinapp.KotlinApp;
import com.avsoftware.kotlinapp.dagger.ApplicationComponent;
import com.avsoftware.kotlinapp.databinding.ActivityRecipeSearchBinding;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    @Inject
    protected SearchActivityViewModel mViewModel;

    private CompositeDisposable mDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposable = new CompositeDisposable();

        ApplicationComponent appComponent = KotlinApp.getGraph();
                appComponent.inject(this);

        View root = bindViewComponents();

        setContentView(root);
    }

    private View bindViewComponents() {

        ActivityRecipeSearchBinding mViewBinding = ActivityRecipeSearchBinding.inflate(LayoutInflater.from(this));
        mViewBinding.setViewModel(mViewModel);
        mViewBinding.setActivity(this);
        mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // connect search
        mDisposable.add(mViewModel.connectSearch().subscribe());

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
        mViewBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.getSearchTrigger().accept(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.getQueryText().accept(newText);
                return true;
            }
        });

        return mViewBinding.getRoot();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
