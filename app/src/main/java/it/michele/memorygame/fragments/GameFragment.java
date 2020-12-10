package it.michele.memorygame.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

import it.michele.memorygame.Card;
import it.michele.memorygame.R;
import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;

public class GameFragment extends Fragment {

    private final int MAT_ROW = 4;
    private final int MAT_COL = 3;

    private Card[][] cards = new Card[MAT_ROW][MAT_COL];

    private ImageView[][] imageViews = new ImageView[MAT_ROW][MAT_COL];

    private ArrayList<String> pickedCards = new ArrayList<>();

    private Context context;

    public static long startTime;

    public GameFragment(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);

        imageViews[0][0] = view.findViewById(R.id.uno);
        imageViews[0][1] = view.findViewById(R.id.due);
        imageViews[0][2] = view.findViewById(R.id.tre);
        imageViews[1][0] = view.findViewById(R.id.quattro);
        imageViews[1][1] = view.findViewById(R.id.cinque);
        imageViews[1][2] = view.findViewById(R.id.sei);
        imageViews[2][0] = view.findViewById(R.id.sette);
        imageViews[2][1] = view.findViewById(R.id.otto);
        imageViews[2][2] = view.findViewById(R.id.nove);
        imageViews[3][0] = view.findViewById(R.id.dieci);
        imageViews[3][1] = view.findViewById(R.id.undici);
        imageViews[3][2] = view.findViewById(R.id.dodici);

        Random random = new Random();
        Value[] values = Value.values();
        Seed[] seeds = Seed.values();

        /*
        Algoritmo di generazione delle carte
         */
        for(int i = 0; i < 6; i++){
            Value value;
            Seed seed;
            do{
                value = values[random.nextInt(values.length)];
                seed = seeds[random.nextInt(seeds.length)];
            } while (pickedCards.contains(value.getValue() + seed.getSeed()));
            pickedCards.add(value.getValue() + seed.getSeed());
            boolean cycle = true;
            do{
                int x = random.nextInt(MAT_ROW);
                int y = random.nextInt(MAT_COL);
                if(cards[x][y] == null){
                    cycle = false;
                    cards[x][y] = new Card(context, imageViews[x][y], value, seed);
                    do {
                        x = random.nextInt(MAT_ROW);
                        y = random.nextInt(MAT_COL);
                    } while (cards[x][y] != null);
                    cards[x][y] = new Card(context, imageViews[x][y], value, seed);
                }
            } while (cycle);
        }

        startTime = System.currentTimeMillis();



        return view;
    }
}
