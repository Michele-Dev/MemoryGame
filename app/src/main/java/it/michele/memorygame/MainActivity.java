package it.michele.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;
import it.michele.memorygame.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static int revealed = 0;
    public static Card revealed_Card;
    public static Drawable back;
    public static AtomicInteger guessedCards = new AtomicInteger(0);

    public static FragmentManager fragmentManager;

    private static Context context;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.container_fragment, new MainFragment(this)).commit();

        back = getResources().getDrawable(R.drawable.back_back);
    }

    /*
    Viene eseguito quando l'utente vince
     */
    public static void win(long time){
        /*
        Scriviamo sul file l'ultimo punteggio, e lo inseriamo alla fine (MODE_APPEND).
        Dichiarando la variabile fos in questo modo, visto che è uno stream, quest'ultimo
        verrà chiuso alla fine dell'esecuzione del blocco try.
         */
        try(FileOutputStream fos = context.openFileOutput("scores.txt", Context.MODE_APPEND)){
            String score = time + "\n";
            fos.write(score.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

        /*
        Reset delle variabili globali
         */

        revealed = 0;
        revealed_Card = null;
        guessedCards.set(0);
        /*
        Mostra il Dialog creato precedentemente nella classe WinDialog
         */
        WinDialog winDialog = new WinDialog(context);
        winDialog.show(fragmentManager, "win dialog");
    }
}