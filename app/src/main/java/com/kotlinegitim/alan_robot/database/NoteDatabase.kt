package com.kotlinegitim.alan_robot.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlinegitim.alan_robot.model.Note
import com.kotlinegitim.alan_robot.dbservice.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {


        abstract fun NoteDao() : NoteDao


}