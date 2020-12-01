package it.michele.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Random;

import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;

public class MainActivity extends AppCompatActivity {

    private LinkedList<Card> cards;

    private ImageView[] imageViews;

    public static int revealed = 0;
    public static Card revealed_Card;
    public static Drawable back;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews = new ImageView[12];
        imageViews[0] = findViewById(R.id.uno);
        imageViews[1] = findViewById(R.id.due);
        imageViews[2] = findViewById(R.id.tre);
        imageViews[3] = findViewById(R.id.quattro);
        imageViews[4] = findViewById(R.id.cinque);
        imageViews[5] = findViewById(R.id.sei);
        imageViews[6] = findViewById(R.id.sette);
        imageViews[7] = findViewById(R.id.otto);
        imageViews[8] = findViewById(R.id.nove);
        imageViews[9] = findViewById(R.id.dieci);
        imageViews[10] = findViewById(R.id.undici);
        imageViews[11] = findViewById(R.id.dodici);

        back = getResources().getDrawable(R.drawable.back_back);

        cards = new LinkedList<>();

        Random random = new Random();
        Value[] values = Value.values();
        Seed[] seeds = Seed.values();

        for(int i = 0; i < imageViews.length; i++){
            Value value = values[random.nextInt(values.length)];
            Seed seed = seeds[random.nextInt(seeds.length)];
            cards.add(new Card(this, imageViews[i], value, seed));
            i++;
            cards.add(new Card(this, imageViews[i], value, seed));
        }
    }
}