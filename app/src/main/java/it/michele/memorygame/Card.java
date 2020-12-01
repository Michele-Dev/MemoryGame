package it.michele.memorygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import it.michele.memorygame.assets.Seed;
import it.michele.memorygame.assets.Value;

public class Card {

    private ImageView imageView;
    private Drawable drawable;
    private Value value;
    private Seed seed;

    @SuppressLint("UseCompatLoadingForDrawables")
    public Card(Context context, ImageView imageView, Value value, Seed seed){
        this.imageView = imageView;
        this.value = value;
        this.seed = seed;

        Resources resources = context.getResources();
        drawable = resources.getDrawable(resources.getIdentifier(
                value.getValue() + seed.getSeed(),
                "drawable", context.getPackageName()));

        this.imageView.setOnClickListener((view) -> {

            if(MainActivity.revealed == 0){
                setImage(drawable);
                MainActivity.revealed++;
                MainActivity.revealed_Card = this;
            } else if(MainActivity.revealed_Card != this) {
                setImage(drawable);
                if(value.equals(MainActivity.revealed_Card.getValue()) &&
                        seed.equals(MainActivity.revealed_Card.getSeed())){
                    this.imageView.setVisibility(View.INVISIBLE);
                    MainActivity.revealed_Card.getImageView().setVisibility(View.INVISIBLE);
                } else {
                    setImage(MainActivity.back);
                    MainActivity.revealed_Card.setImage(MainActivity.back);
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
