package com.example.jfedin.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText wordEditText ;
    private EditText meaningEditText;
    private EditText typeEditText;

    //view Model for add new word Activity
    private AddNewWordViewModel mViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        wordEditText =findViewById(R.id.edit_text_word);
        meaningEditText =findViewById(R.id.edit_text_meaning);
        typeEditText =findViewById(R.id.edit_text_Type);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
        setTitle("Add New Activity");
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

        if (word.isEmpty()|| meaning.isEmpty()||type.isEmpty()){
            Toast.makeText(AddNewWordActivity.this,"please fill all field ",Toast.LENGTH_LONG).show();
            return;
        }
        mViewModel.insert(new Words(word,meaning,type));
        finish();


    }
}
