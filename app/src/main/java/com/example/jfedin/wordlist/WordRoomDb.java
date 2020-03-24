package com.example.jfedin.wordlist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Words.class ,version = 1)
public abstract class WordRoomDb extends RoomDatabase {

    private static WordRoomDb instance;
    public abstract WordsDao wordsDao();

    //Singlton
    public static synchronized WordRoomDb getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordRoomDb.class,"word-database")
                    .fallbackToDestructiveMigrationFrom()
                    .addCallback(roomCallBack)
                    .build();
        }
           return instance;

    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
            
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void ,Void,Void>{

        private WordsDao mWordDao ;

       PopulateDataAsyncTask(WordRoomDb db){
        mWordDao = db.wordsDao();
       }

        @Override
        protected Void doInBackground(Void... voids) {
           mWordDao.insert(new Words("book","book","non"));
           mWordDao.insert(new Words("book","book","non"));
           mWordDao.insert(new Words("book","book","non"));

            return null;
        }
    }


}
