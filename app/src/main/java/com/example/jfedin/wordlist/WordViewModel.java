package com.example.jfedin.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordsRepository mRepository;
    private LiveData<List<Words>> mAllWords ;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordsRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public void insert(Words words){
        mRepository.insert(words);
    }

    public void delete(Words words){
        mRepository.delete(words);
    }

    public void update(Words words){
        mRepository.update(words);
    }

    public void deleteAllWords(){
        mRepository.deleteAllWords();
    }

    public LiveData<List<Words>> getAllWords(){

        return mAllWords;
    }



}
