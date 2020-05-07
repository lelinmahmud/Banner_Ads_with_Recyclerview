package com.lelin.banneradswithrecyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView

class Adapter(val list: List<Any>,val context: Context):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_PERSON->{
                val view=LayoutInflater.from(context).inflate(R.layout.item_add_involmence_new,parent,false)
                return DataViewHolder(view)
            }
            else ->{
                val view=LayoutInflater.from(context).inflate(R.layout.banner_ads_layout,parent,false)
                return BannerViewHolder(view)
            }

        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewType=getItemViewType(position)
//
        if (list.get(position) is AdView){
            Log.e("tag", "instance of add found at position : $position")
        }

        when(viewType){
            ITEM_PERSON->{
                Log.e("tag", "add not found  $position")

            }

            ITEM_BANNER_AD ->{
                Log.e("tag", "add found $position")

                val bannerViewHolder=holder as BannerViewHolder
                val adView:AdView= list.get(position+1) as AdView
                val addCardView:ViewGroup=bannerViewHolder.itemView as ViewGroup

                if (addCardView.childCount>0){
                    addCardView.removeAllViews()
                }
                if (addCardView.parent!=null){
                    (adView.parent as ViewGroup).removeView(adView)
                }

                addCardView.addView(adView)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position%4==0){
            return ITEM_BANNER_AD
        }
        else {
            return ITEM_PERSON
        }
    }


    inner class DataViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

    }

    inner class BannerViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){

    }


    companion object{
        private val ITEM_PERSON = 0
        private val ITEM_BANNER_AD = 1
    }


}