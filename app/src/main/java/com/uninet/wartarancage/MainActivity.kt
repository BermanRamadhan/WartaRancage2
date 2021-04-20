package com.uninet.wartarancage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.uninet.wartarancage.adapter.NewsBanner
import com.uninet.wartarancage.model.NewsPostModel

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var bannerAdapter: NewsBanner
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = this.findViewById(R.id.news__banner)
        tabLayout = this.findViewById(R.id.news__banner__indic)


        val mList = ArrayList<NewsPostModel>()
//        val news : Boolean = mList.add(NewsPostModel(R.drawable.persib,"Persib News","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))
//        mList.add(NewsPostModel(R.drawable.truk,"Persib blablabal","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))
//        mList.add(NewsPostModel(R.drawable.truk,"Persib blablabal","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))
//        mList.add(NewsPostModel(R.drawable.truk,"Persib blablabal","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))
//        mList.add(NewsPostModel(R.drawable.truk,"Persib blablabal","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))
//        mList.add(NewsPostModel(R.drawable.truk,"Persib blablabal","bla bla abllablablab lab lab lablablablalabla blablbalb", null,"8-4-2021"))

        tabLayout.tabGravity = TabLayout.GRAVITY_START
        viewPager.offscreenPageLimit = mList.size

        bannerAdapter = NewsBanner(this,mList)
        viewPager.adapter = this.bannerAdapter
        tabLayout.setupWithViewPager(viewPager)


    }
}