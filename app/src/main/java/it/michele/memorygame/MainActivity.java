package it.michele.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;

public class MainActivity extends AppCompatActivity {

    private final int MAT_ROW = 4;
    private final int MAT_COL = 3;

    //private LinkedList<Card> cards;
    private Card[][] cards = new Card[MAT_ROW][MAT_COL];

    private ImageView[][] imageViews = new ImageView[MAT_ROW][MAT_COL];

    public static int revealed = 0;
    public static Card revealed_Card;
    public static Drawable back;
    public static AtomicInteger guessedCards = new AtomicInteger(0);

    private static FragmentManager fragmentManager;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        imageViews[0][0] = findViewById(R.id.uno);
        imageViews[0][1] = findViewById(R.id.due);
        imageViews[0][2] = findViewById(R.id.tre);
        imageViews[1][0] = findViewById(R.id.quattro);
        imageViews[1][1] = findViewById(R.id.cinque);
        imageViews[1][2] = findViewById(R.id.sei);
        imageViews[2][0] = findViewById(R.id.sette);
        imageViews[2][1] = findViewById(R.id.otto);
        imageViews[2][2] = findViewById(R.id.nove);
        imageViews[3][0] = findViewById(R.id.dieci);
        imageViews[3][1] = findViewById(R.id.undici);
        imageViews[3][2] = findViewById(R.id.dodici);

        back = getResources().getDrawable(R.drawable.back_back);

        Random random = new Random();
        Value[] values = Value.values();
        Seed[] seeds = Seed.values();

        /*
        Algoritmo di generazione delle carte
         */
        for(int i = 0; i < 6; i++){
            Value value = values[random.nextInt(values.length)];
            Seed seed = seeds[random.nextInt(seeds.length)];
            boolean cycle = true;
            do{
                int x = random.nextInt(MAT_ROW);
                int y = random.nextInt(MAT_COL);
                if(cards[x][y] == null){
                    cycle = false;
                    cards[x][y] = new Card(this, imageViews[x][y], value, seed);
                    do {
                        x = random.nextInt(MAT_ROW);
                        y = random.nextInt(MAT_COL);
                    } while (cards[x][y] != null);
                    cards[x][y] = new Card(this, imageViews[x][y], value, seed);
                }
            } while (cycle);
        }
    }

    /*
    Viene eseguito quando l'utente vince
     */
    public static void win(){
        /*
        Mostra il Dialog creato precedentemente nella classe WinDialog
         */
        WinDialog winDialog = new WinDialog();
        winDialog.show(fragmentManager, "win dialog");
    }
}