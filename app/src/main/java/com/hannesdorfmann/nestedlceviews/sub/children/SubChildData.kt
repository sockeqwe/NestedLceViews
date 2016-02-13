package com.hannesdorfmann.nestedlceviews.sub.children

import android.os.Parcel
import android.os.Parcelable

/**
 *
 *
 * @author Hannes Dorfmann
 */
class SubChildData(val items: MutableList<String> = arrayListOf("Item 1", "Item 2", "Item 3",
    "Item 4",
    "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13",
    "Item 14", "Item 15")) : Parcelable {

  constructor(source: Parcel) : this() {
    items.clear() // hacky workaround
    source.readStringList(items)
  }

  override fun writeToParcel(parcel: Parcel, p1: Int) = parcel.writeStringList(items)

  override fun describeContents(): Int = 0


  companion object {
    @JvmField final val CREATOR: Parcelable.Creator<SubChildData> = object : Parcelable.Creator<SubChildData> {
      override fun createFromParcel(source: Parcel): SubChildData {
        return SubChildData(source)
      }

      override fun newArray(size: Int): Array<SubChildData?> {
        return arrayOfNulls(size)
      }
    }
  }
}

