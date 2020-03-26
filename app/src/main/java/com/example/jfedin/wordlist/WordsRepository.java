package com.example.jfedin.wordlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordsRepository {

   private WordsDao mWordsDao ;
   private LiveData<List<Words>> getAllWords;


   public WordsRepository(Application app){

       WordRoomDb db = WordRoomDb.getInstance(app);
       mWordsDao =db.wordsDao();
       getAllWords = mWordsDao.getAllWords();

   }
    //operations

    //insert
    public void insert(Words words){
         new InsertAsyncTask(mWordsDao).execute(words);
    }
    //delete
    public void delete(Words words){
      new DeleteAsyncTask(mWordsDao).execute(words);
    }
    //update
    public void update(Words words){
      new UpdateAsyncTask(mWordsDao).execute(words);
    }
    //getAllWords
    public LiveData<List<Words>> getAllWords(){
       return getAllWords;
    }
    //delete all words

   public void deleteAllWords(){
     new DeleteAllWordsAsyncTask(mWordsDao).execute();
   }


   private static class InsertAsyncTask extends AsyncTask<Words,Void,Void>{

       private WordsDao mWordsDao ;

       public InsertAsyncTask (WordsDao wordsDao){

           mWordsDao = wordsDao;
       }
       @Override
       protected Void doInBackground(Words... words) {
           mWordsDao.insert(words[0]);
           return null;
       }
   }

    private static class DeleteAsyncTask extends AsyncTask<Words,Void,Void>{

        private WordsDao mWordsDao ;

        public DeleteAsyncTask (WordsDao wordsDao){

            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }



    private static class UpdateAsyncTask extends AsyncTask<Words,Void,Void>{

        private WordsDao mWordsDao ;

        public UpdateAsyncTask (WordsDao wordsDao){

            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }


    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void,Void,Void>{

        private WordsDao mWordsDao ;

        public DeleteAllWordsAsyncTask (WordsDao wordsDao){

            mWordsDao = wordsDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }


}
