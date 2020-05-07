package com.lelin.banneradswithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var recyclerItem:ArrayList<Any> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713")
        rv_item.setHasFixedSize(true)
        rv_item.layoutManager=LinearLayoutManager(this)
        getData()
        getBannerAds()
        loadBannerAds()

        rv_item.adapter=Adapter(recyclerItem,this)
    }


    fun getData(){
        for (i in 1..100){
            recyclerItem.add(Data("Lelin","App"))
        }
    }


    fun getBannerAds(){
        for (i in 1..recyclerItem.size step ITEM_PER_ADD){

            val adview=AdView(applicationContext)
            adview.adSize= AdSize.BANNER
            adview.adUnitId= BANNER_AD_ID
            recyclerItem.add(i,adview)
        }


    }

    fun loadBannerAds(){
        for ((index,value) in recyclerItem.withIndex()){

            val item= recyclerItem[index]
            if (item is AdView){
                val advieww = (item).also {
                    it.loadAd(AdRequest.Builder().build())
                }
            }

        }
    }


    companion object{
        val ITEM_PER_ADD=4
        val BANNER_AD_ID="ca-app-pub-3940256099942544/6300978111"
    }
}
