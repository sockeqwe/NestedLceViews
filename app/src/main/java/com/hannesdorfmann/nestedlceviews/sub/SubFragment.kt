package com.hannesdorfmann.nestedlceviews.sub

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState
import com.hannesdorfmann.nestedlceviews.R
import com.hannesdorfmann.nestedlceviews.sub.children.SubChildFragment

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SubFragment : SubView, MvpLceViewStateFragment<ViewGroup, SubData, SubView, SubPresenter>() {

  private var data: SubData? = null
  private lateinit var adapter: MyPagerAdapter
  private lateinit var viewPager: ViewPager
  private lateinit var tabs: TabLayout

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.fragment_sub, container, false)

    viewPager = view.findViewById(R.id.viewPager) as ViewPager
    tabs = view.findViewById(R.id.tabs) as TabLayout

    return view
  }

  override fun setData(data: SubData) {
    this.data = data
    adapter = MyPagerAdapter(childFragmentManager, data.items)
    viewPager.adapter = adapter
    tabs.setupWithViewPager(viewPager)
  }

  override fun loadData(p0: Boolean) = presenter.loadData()

  override fun createViewState(): LceViewState<SubData, SubView> = RetainingLceViewState()

  override fun getData() = data

  override fun createPresenter(): SubPresenter = SubPresenter()

  override fun getErrorMessage(p0: Throwable?, p1: Boolean) = "An error has occurred"
}

class MyPagerAdapter(fm: FragmentManager, val items : List<String>) : FragmentPagerAdapter(fm) {


  override fun getItem(p0: Int): Fragment = SubChildFragment()

  override fun getCount() = items.size

  override fun getPageTitle(position: Int) = items[position]

}