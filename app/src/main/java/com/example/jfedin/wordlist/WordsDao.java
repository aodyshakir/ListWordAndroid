package com.example.jfedin.wordlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordsDao {

  @Insert
    void insert (Words words);

  @Update
    void update (Words words);

  @Delete
    void delete(Words words);

  @Query("DELETE from wordTable")
   void deleteAllWords();

  @Query("SELECT * From wordTable")
  LiveData<List<Words>>getAllWords();

}
