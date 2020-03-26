package com.example.jfedin.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText wordEditText ;
    private EditText meaningEditText;
    private EditText typeEditText;

    private boolean editMode ;
    private int mID;

    public static final String EXTRA_ID = "com.example.jfedin.wordlist.extraid";

    public static final String EXTRA_WORD = "com.example.jfedin.wordlist.WORD";
    public static final String EXTRA_MEANING = "com.example.jfedin.wordlist.MEANING";
    public static final String EXTRA_TYPE = "com.example.jfedin.wordlist.TYPE";

    //view Model for add new word Activity
    private AddNewWordViewModel mViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        wordEditText =findViewById(R.id.edit_text_word);
        meaningEditText =findViewById(R.id.edit_text_meaning);
        typeEditText =findViewById(R.id.edit_text_Type);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_exit);


        Intent i = getIntent();
        if (i.hasExtra(EXTRA_ID)){
            // update
            setTitle("Edit Word");
            editMode = false ;
            mID = i.getIntExtra(EXTRA_ID,-1);
            wordEditText.setText(i.getStringExtra(EXTRA_WORD));
            meaningEditText.setText(i.getStringExtra(EXTRA_MEANING));
            typeEditText.setText(i.getStringExtra(EXTRA_TYPE));
        }else {
            // insert
            setTitle("Add New Word");
            editMode = true ;
        }

        mViewModel = new ViewModelProvider(this).get(AddNewWordViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void saveWord(){
        String word = wordEditText.getText().toString().trim();
        String meaning =meaningEditText.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();

        Words wordObject = new Words(word,meaning,type  );

        if (word.isEmpty()|| meaning.isEmpty()||type.isEmpty()){
            Toast.makeText(AddNewWordActivity.this,"please fill all field ",Toast.LENGTH_LONG).show();
            return;
        }

        if (editMode){
              wordObject.setId(mID);
            mViewModel.insert(wordObject);
        }else {
            mViewModel.update(wordObject);
        }

        finish();


    }
}
