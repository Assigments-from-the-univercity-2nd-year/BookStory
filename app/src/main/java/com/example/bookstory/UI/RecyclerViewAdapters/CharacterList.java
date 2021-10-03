package com.example.bookstory.UI.RecyclerViewAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstory.DAO.Book;
import com.example.bookstory.DAO.Character;
import com.example.bookstory.R;
import com.example.bookstory.UI.Fragments.BookDescriptionFragmentArgs;
import com.example.bookstory.UI.Fragments.BookDescriptionFragmentDirections;

import java.util.List;

public class CharacterList extends RecyclerView.Adapter<CharacterList.CharacterListHolder> {

    Context context;
    List<Character> characters;
    Book book;

    /**
     * Constructor, that needs the current Context,
     * the book and the List<Character> of this book
     *
     * @param context Context
     * @param characters List<Character> of a specific book
     * @param book the book
     */
    public CharacterList(Context context, List<Character> characters, Book book) {
        this.context = context;
        this.characters = characters;
        this.book = book;
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
        holder.character = characters.get(position);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class CharacterListHolder extends RecyclerView.ViewHolder {

        TextView characterName, characterPseudonyms;
        Character character;

        public CharacterListHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.textView_characterRow_characterName);
            characterPseudonyms = itemView.findViewById(R.id.textView_characterRow_characterPseudonyms);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Bundle data = new Bundle();
                    data.putString("character", character.characterName);
                    Navigation.findNavController(v).navigate(
                            R.id.action_bookDescriptionFragment_to_bookListFragment,
                            data
                    );*/
                    BookDescriptionFragmentDirections.ActionBookDescriptionFragmentToBookListFragment2 action =
                            BookDescriptionFragmentDirections.actionBookDescriptionFragmentToBookListFragment2()
                                    .setCharacter(character)
                            .setBookForPopUp(book);
                    Navigation.findNavController(v).navigate(action);
                }
            });
        }
    }
}
