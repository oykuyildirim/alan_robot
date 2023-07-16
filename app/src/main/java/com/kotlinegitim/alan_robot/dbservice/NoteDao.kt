package com.kotlinegitim.alan_robot.dbservice

import androidx.room.*
import com.kotlinegitim.alan_robot.model.Note

@Dao

interface NoteDao {

    @Query("select * from Notes")
    fun allNotes() : List<Note>

    @Insert
    fun Insert( note: Note) : Long

    @Delete
    fun Delete( note: Note)

    @Update
    fun Update( note: Note)

    @Query("SELECT * FROM Notes WHERE note like :note ")
    fun searchItem( note: String ) : List<Note>
}