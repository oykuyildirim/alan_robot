package com.kotlinegitim.alan_robot

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.kotlinegitim.alan_robot.database.NoteDatabase
import com.kotlinegitim.alan_robot.settings.AppManager
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var itemList : ListView
    var obj = AppManager()



    override fun onCreate(savedInstanceState: Bundle?) {

        var alanButton: AlanButton? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = AlanConfig.builder().setProjectId("").build()
        alanButton = findViewById(R.id.alan_button)
        itemList = findViewById(R.id.itemList)


        val db = Room.databaseBuilder(
            this,
            NoteDatabase::class.java,
            "NoteDatabase"
        ).build()


        obj.showAllListItems(this,db,itemList,false)


        alanButton?.initWithConfig(config)

        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan AI Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    myCommands(command, commandName,db)

                    Log.d("AlanButton", "onCommand: commandName: $commandName")
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };

        alanButton?.registerCallback(alanCallback);

    }

    fun myCommands(command: JSONObject, commandName : String, db : NoteDatabase){



        println(commandName)
        if(commandName =="add_list"){

            var item = command.getJSONObject("data").getString("item")
            Toast.makeText(this,"Added to list +${item}!", Toast.LENGTH_LONG).show()

            obj.addList(item,itemList,this,db)

        }

        else if (commandName == "remove_list"){

            var item = command.getJSONObject("data").getString("item")
            Toast.makeText(this,"Removed from the list -${item}!", Toast.LENGTH_LONG).show()

            obj.removeList(item,itemList,this,db)

        }
        else if (commandName == "open_google"){

            obj.openGoogle(this)

        }

        else if (commandName == "open_weather"){

            obj.weatherForecast(this)
        }

        else if (commandName =="call"){

            obj.call(this)

        }

        else if (commandName == "update_item"){

            var item = command.getJSONObject("data").getString("item")

            var update = command.getJSONObject("data").getString("updated")
            obj.updateList(item,update,itemList,this,db)
        }


    }



}