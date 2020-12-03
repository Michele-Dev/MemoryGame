package it.michele.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicBoolean;

import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;

public class Card {

    private ImageView imageView;
    private Drawable drawable;
    private Value value;
    private Seed seed;

    /*
    Variabile di lock, per gestire l'esecuzione dei Thread.
    Usiamo la classe AtomicBoolean perchè è Thread-Safe
    https://javarevisited.blogspot.com/2012/01/how-to-write-thread-safe-code-in-java.html#axzz6fa4OFDlh
     */
    private static AtomicBoolean lock = new AtomicBoolean(false);

    @SuppressLint("UseCompatLoadingForDrawables")
    public Card(Context context, ImageView imageView, Value value, Seed seed){
        this.imageView = imageView;
        this.value = value;
        this.seed = seed;

        /*
        Otteniamo il drawable (L'immagine) della carta dall'Enumeratore
        Value e Seed
         */
        Resources resources = context.getResources();
        drawable = resources.getDrawable(resources.getIdentifier(
                value.getValue() + seed.getSeed(),
                "drawable", context.getPackageName()));

        //imageView.setImageDrawable(drawable);

        this.imageView.setOnClickListener((view) -> {
            if(!lock.get()) {
                if (MainActivity.revealed == 0) {
                    setImage(drawable);
                    MainActivity.revealed++;
                    MainActivity.revealed_Card = this;
                } else if (MainActivity.revealed_Card != this) {
                    setImage(drawable);
                    lock.set(true);
                    /*
                    Avviamo il nuovo thread per evitare di interrompere il main Thread.
                    In questo modo la seconda carta viene visualizzata a schermo per N
                    millisecondi, dipendentemente dal valore di sleep che inseriamo nel
                    metodo Thread#sleep(int milliseconds).
                    Infine disabilitiamo il lock impostandolo a false.
                     */
                    new Thread(() -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (value.equals(MainActivity.revealed_Card.getValue()) &&
                                seed.equals(MainActivity.revealed_Card.getSeed())) {
                            this.imageView.setVisibility(View.INVISIBLE);
                            MainActivity.revealed_Card.getImageView().setVisibility(View.INVISIBLE);
                        } else {
                            setImage(MainActivity.back);
                            MainActivity.revealed_Card.setImage(MainActivity.back);
                        }
                        lock.set(false);
                    }).start();
                    MainActivity.revealed = 0;
                }
            }
        });

    }

    public void setImage(Drawable drawable){
        imageView.setImageDrawable(drawable);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public Value getValue() {
        return value;
    }

    public Seed getSeed() {
        return seed;
    }
}
