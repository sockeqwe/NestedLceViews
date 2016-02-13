package com.hannesdorfmann.nestedlceviews.sub

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SubPresenter : MvpBasePresenter<SubView>(){

  var subscription: Subscription? = null

  fun loadData() {

    view?.showLoading(false)

    subscription = Observable.fromCallable ({
      Thread.sleep(4000)
      SubData()
    }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              view?.setData(it)
              view?.showContent()
            },
            {
              it.printStackTrace()
              view?.showError(it, false)
            }
        );
  }

  override fun detachView(retainInstance: Boolean) {
    super.detachView(retainInstance)
    if (!retainInstance) {
      subscription?.unsubscribe()
    }
  }
}