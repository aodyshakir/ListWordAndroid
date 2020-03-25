package com.example.jfedin.wordlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public  class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private static List<Words> mWordList = new ArrayList<>();
    private static OnItemClickListener mListener;


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
          Words currentWord = mWordList.get(position);
          holder.textViewWord.setText(currentWord.getWordName());
          holder.textViewWord.setText(currentWord.getWordMeaning());
          holder.textViewWord.setText(currentWord.getWordType());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public void setWords(List<Words> words){
        mWordList = words;
        notifyDataSetChanged();
    }
    public static class WordViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;


        public WordViewHolder(@NonNull View itemView) {
           super(itemView);
           textViewWord =itemView.findViewById(R.id.word_text_view);
           textViewMeaning = itemView.findViewById(R.id.meaning_text_view);
           textViewType = itemView.findViewById(R.id.type_text_view);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int index = getAdapterPosition();
                   if (mListener != null && index !=RecyclerView.NO_POSITION){
                       mListener.onItemClick(mWordList.get(index));
                   }
               }
           });

       }
   }
    public interface OnItemClickListener{

        void onItemClick(Words words);
    }

    public void OnItemClickListener(OnItemClickListener listener){
        mListener = listener ;


   }


}
