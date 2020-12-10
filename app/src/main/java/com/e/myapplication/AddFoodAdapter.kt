package com.e.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.entity.FoodEntity

/// custom adapter not implememnted


public class AddFoodAdapter(
        private var activity: Activity,
        private var items: ArrayList<Any>,
        private val mContext: Context
    ) : BaseAdapter() {

//    private class ViewHolder(row: View?) {
//        var food: TextView? = null
//        var protein : TextView? = null
//        var fat : TextView? = null
//        var kcal : TextView? = null
//        var carbs : TextView? = null
//
//        init {
//            this.food = row?.findViewById<TextView>(R.id.textView2)
//            this.protein = row?.findViewById<TextView>(R.id.textView3)
//            this.fat = row?.findViewById<TextView>(R.id.textView4)
//            this.kcal = row?.findViewById<TextView>(R.id.textView5)
//            this.carbs= row?.findViewById<TextView>(R.id.textView6)
//
//
//        }
//    }
        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.listview_rowitem, viewGroup, false)

            var food = row?.findViewById<TextView>(R.id.textView2)
            var protein = row?.findViewById<TextView>(R.id.textView3)
            var fat = row?.findViewById<TextView>(R.id.textView4)
            var carbs = row?.findViewById<TextView>(R.id.textView5)
            var kcal= row?.findViewById<TextView>(R.id.textView6)

//              0    1     2    3     4      5   6
//            [carb, kcal, fat, mass, protein, 2, name]
            var currentItem: MutableMap<String, Any> = items[position] as MutableMap<String, Any>
            println("    " + currentItem)
            if (food != null) {
                food.text = currentItem.get("name").toString()
            }
            if (protein != null) {
                protein.text = currentItem.get("protein").toString()
            }
            if (fat != null) {
                fat.text = currentItem.get("fat").toString()
            }
            if (kcal != null) {
                kcal.text = currentItem.get("kcal").toString()
            }
            if (carbs != null) {
                carbs.text = currentItem.get("carb").toString()
            }
            return row
        }
    }




