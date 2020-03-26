package com.example.jfedin.wordlist;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //viewModel
    private WordViewModel mWordViewModel;

    //RecyclerView
    private RecyclerView mRecyclerView ;
    private WordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //floating_button
        FloatingActionButton floatingActionButton = findViewById(R.id.bottom_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(MainActivity.this,AddNewWordActivity.class);
               startActivityForResult(i,1);
            }
        });

        //recycler view
        mRecyclerView = findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

       //connect Recyclerview with Adapter
        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);



       //ViewModel
        mWordViewModel= new ViewModelProvider(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
           @Override
           public void onChanged(List<Words> words) {
               //update UI

               //RecycleView
               mWordAdapter.setWords(words);
              // Toast.makeText(MainActivity.this,"On Changed Worked",Toast.LENGTH_SHORT).show();
           }
       });

       mWordAdapter.OnItemClickListener(new WordAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(Words words) {
               Intent intent = new Intent(MainActivity.this,AddNewWordActivity.class);
               intent.putExtra(AddNewWordActivity.EXTRA_ID,words.getId());
               intent.putExtra(AddNewWordActivity.EXTRA_WORD,words.getWordName());
               intent.putExtra(AddNewWordActivity.EXTRA_MEANING,words.getWordMeaning());
               intent.putExtra(AddNewWordActivity.EXTRA_TYPE,words.getWordType());

               startActivity(intent);

           }
       });
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

               // delete item
              int position = viewHolder.getAdapterPosition();
              mWordViewModel.delete(mWordAdapter.getWordAt(position));


           }
       }).attachToRecyclerView(mRecyclerView);
    }
}
