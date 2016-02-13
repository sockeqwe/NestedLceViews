package com.hannesdorfmann.nestedlceviews.sub.children

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
class SubChildPresenter : MvpBasePresenter<SubChildView>() {

  var subscription: Subscription? = null

  fun loadData() {

    view?.showLoading(false)

    subscription = Observable.fromCallable ({
      Thread.sleep(4000)
      SubChildData()
    }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              view?.setData(it)
              view?.showContent()
            },
            {
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