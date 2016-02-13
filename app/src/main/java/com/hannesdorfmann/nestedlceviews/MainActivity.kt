package com.hannesdorfmann.nestedlceviews

import android.os.Bundle
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MainView, MvpLceViewStateActivity<ViewGroup, ScreenDescription, MainView, MainPresenter>() {

  private var data: ScreenDescription ? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    retainInstance = true
  }

  override fun getErrorMessage(p0: Throwable?, p1: Boolean): String = "An error has occurred"

  override fun setData(data: ScreenDescription) {
    this.data = data
    screenDescription.text = data.name
  }

  override fun loadData(p0: Boolean) {
    presenter.loadData()
  }

  override fun createPresenter(): MainPresenter = MainPresenter()

  override fun createViewState(): LceViewState<ScreenDescription, MainView> = RetainingLceViewState()

  override fun getData(): ScreenDescription? = data
}
