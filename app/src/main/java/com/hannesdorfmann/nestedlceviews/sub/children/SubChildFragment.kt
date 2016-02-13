package com.hannesdorfmann.nestedlceviews.sub.children

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.ParcelableDataLceViewState
import com.hannesdorfmann.nestedlceviews.R

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SubChildFragment : SubChildView, MvpLceViewStateFragment<ListView, SubChildData, SubChildView, SubChildPresenter>() {

  private var data: SubChildData? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_subchild, container, false)

  override fun setData(data: SubChildData) {
    this.data = data
    val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, data!!.items)
    contentView.adapter = adapter
  }

  override fun loadData(p0: Boolean) = presenter.loadData()

  override fun createViewState(): LceViewState<SubChildData, SubChildView> = ParcelableDataLceViewState()

  override fun getData(): SubChildData? = data

  override fun createPresenter(): SubChildPresenter = SubChildPresenter()

  override fun getErrorMessage(p0: Throwable?, p1: Boolean) = "An error has occurred"

}