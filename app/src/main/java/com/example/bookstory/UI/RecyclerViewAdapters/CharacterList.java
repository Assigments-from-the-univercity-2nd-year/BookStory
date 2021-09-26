package com.example.bookstory.UI.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.Character;
import com.example.bookstory.R;

import java.util.List;

public class CharacterList extends RecyclerView.Adapter<CharacterList.CharacterListHolder> {

    Context context;
    List<Character> characters;

    public CharacterList(Context context, List<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    @NonNull
    @Override
    public CharacterListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new CharacterListHolder(
                layoutInflater.inflate(R.layout.character_row, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterListHolder holder, int position) {
        holder.characterName.setText(characters.get(position).characterName);
        holder.characterPseudonyms.setText(characters.get(position).pseudonyms);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class CharacterListHolder extends RecyclerView.ViewHolder {

        TextView characterName, characterPseudonyms;

        public CharacterListHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.textView_characterRow_characterName);
            characterPseudonyms = itemView.findViewById(R.id.textView_characterRow_characterPseudonyms);
        }
    }
}
