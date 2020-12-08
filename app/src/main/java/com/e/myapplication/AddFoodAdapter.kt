package com.e.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.e.myapplication.entity.FoodEntity

/// custom adapter not implememnted


public class AddFoodAdapter(
        private var activity: Activity,
        private var items: ArrayList<Any>
    ) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var food: TextView? = null
        var protein : TextView? = null
        var fat : TextView? = null
        var kcal : TextView? = null
        var carbs : TextView? = null

        init {
            this.food = row?.findViewById<TextView>(R.id.textView2)
            this.protein = row?.findViewById<TextView>(R.id.textView3)
            this.fat = row?.findViewById<TextView>(R.id.textView4)
            this.kcal = row?.findViewById<TextView>(R.id.textView5)
            this.carbs= row?.findViewById<TextView>(R.id.textView6)


        }
    }
        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(position: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            TODO("Not yet implemented")
        }
    }
