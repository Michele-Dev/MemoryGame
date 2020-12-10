package it.michele.memorygame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.michele.memorygame.MainActivity;
import it.michele.memorygame.R;

public class MainFragment extends Fragment {

    private Context context;

    public MainFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        Button load_game = view.findViewById(R.id.load_game);
        load_game.setOnClickListener((v) -> {
            MainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, new GameFragment(context)).commit();
        });

        return view;
    }
}
