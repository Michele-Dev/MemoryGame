package it.michele.memorygame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import it.michele.memorygame.MainActivity;
import it.michele.memorygame.R;

public class WinFragment extends Fragment {

    private Context context;

    public WinFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.win_fragment, container, false);

        Button exit = view.findViewById(R.id.exit);
        exit.setOnClickListener((v) -> {
            MainActivity.fragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, new MainFragment(context)).commit();
        });

        FileInputStream fis = null;
        try {
            fis = context.openFileInput("scores.txt");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        /*Utilizziamo UFTF_8 perchè è in grado di leggere tutti i caratteri unicode
        Esempio:  ♦, ►
        */
        /*
        La StringBuilder ci permette di costruire una stringa in maniera efficiente e pulita
         */
        StringBuilder stringBuilder = new StringBuilder();
        /*
        Leggiamo tutto il file e lo salviamo nella StringBuilder
         */
        try(BufferedReader reader = new BufferedReader(isr)){
            String line = reader.readLine();
            int lineNumber = 1;
            while (line != null){
                stringBuilder.append(lineNumber).append(". ").append(line).append("\n");
                line = reader.readLine();
                lineNumber++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        TextView results = view.findViewById(R.id.results);
        results.setText(stringBuilder.toString());

        return view;
    }
}
