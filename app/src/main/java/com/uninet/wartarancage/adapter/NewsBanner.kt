package com.uninet.wartarancage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.uninet.wartarancage.R
import com.uninet.wartarancage.model.NewsPostModel

class NewsBanner : PagerAdapter {


    var context:Context
    var mNewsList :List<NewsPostModel>

    constructor(context: Context, mNewsList: List<NewsPostModel>) : super() {
        this.context = context
        this.mNewsList = mNewsList
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.banner_item_swipe, null)
        val image = view.findViewById<ImageView>(R.id.img__banner)
        val title = view.findViewById<TextView>(R.id.news__title)
        val content = view.findViewById<TextView>(R.id.news__content)
        val date = view.findViewById<TextView>(R.id.news__date)

        image.setImageResource(mNewsList.get(position).img)
        title.setText(mNewsList.get(position).title)
        content.setText(mNewsList.get(position).content)
        title.setText(mNewsList.get(position).title)
        date.setText(mNewsList.get(position).date)
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return mNewsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}