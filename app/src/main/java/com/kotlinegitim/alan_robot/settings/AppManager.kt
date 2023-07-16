package com.kotlinegitim.alan_robot.settings

import android.R
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.kotlinegitim.alan_robot.model.Note
import com.kotlinegitim.alan_robot.database.NoteDatabase


class AppManager {

    companion object {
        var listItem = mutableListOf<String>()
    }

    fun showAllListItems(act: Activity, db: NoteDatabase, listview: ListView, isUpdated: Boolean){


        Thread{

            val ls = db.NoteDao().allNotes()
            for ( item in ls ) {

                listItem.add(item.note.toString())

            }

            act.runOnUiThread {


                setAdaptor(listview,act,isUpdated)

            }

        }.start()


    }



    fun addList(item : String,listview:ListView,act: Activity, db : NoteDatabase){

        listItem.add(item)
        setAdaptor(listview,act,false)
        val run = Runnable {


            val note = Note(
                null,
                item
            );
            db.NoteDao().Insert(note)


        }
        Thread(run).start()

    }

    fun removeList(item:String,listview:ListView,act: Activity, db: NoteDatabase){

        var isList = listItem.remove(item)
        if (isList == false){

            Toast.makeText(act,"No item in the list",Toast.LENGTH_LONG).show()
        }
        else {
            setAdaptor(listview, act, false)
            val run = Runnable {


                val note = Note(
                    null,
                    item
                );
                db.NoteDao().Delete(note)


            }
            Thread(run).start()
        }

    }
    
    fun updateList( item:String,upd_item:String,listview:ListView,act: Activity, db: NoteDatabase){

        val run = Runnable {


            var itemObj = db.NoteDao().searchItem(item)
            println(itemObj)


            if (itemObj.count() == 0){
                println("No Item")

                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(act,"No item like this",Toast.LENGTH_LONG).show()
                }, 1000)


            }
            else{

                var userupt = Note(
                    itemObj.get(0).id,
                    upd_item
                )
                db.NoteDao().Update(userupt)

                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(act,"Item is updated",Toast.LENGTH_LONG).show()
                }, 1000)


                act.runOnUiThread{
                    setAdaptor(listview,act,true)
                    listItem.removeAll(listItem)
                    showAllListItems(act,db,listview,false)
                }
            }



        }
        Thread(run).start()






    }

    fun openGoogle(act: Activity){
        try {
            var link = "https://www.google.com.tr/"
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            act.startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                act, "No application can handle this request."
                        + " Please install a webbrowser", Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
    }
    fun weatherForecast(act: Activity){
        try {
            var link = "https://www.mgm.gov.tr/tahmin/il-ve-ilceler.aspx#/"
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            act.startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                act, "No application can handle this request."
                        + " Please install a webbrowser", Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }
    }

    fun call(act: Activity){
        try {
            val callIntent = Intent(Intent.ACTION_VIEW)
            callIntent.data = Uri.parse("tel:"+"")
            act.startActivity(callIntent)
        } catch (activityException: ActivityNotFoundException) {

        }
    }


    fun setAdaptor(listview: ListView,act: Activity, isUpdated: Boolean){


        var adapter = ArrayAdapter(act, R.layout.simple_list_item_1, listItem)
        if(isUpdated == false) {
            listview.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        else{
            listItem.removeAll(listItem)
            adapter.clear()
            listview.adapter = adapter
            adapter.notifyDataSetChanged()
        }


    }


}
