package it.michele.memorygame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import it.michele.memorygame.fragments.WinFragment;

public class WinDialog extends AppCompatDialogFragment {

    private Context context;

    public WinDialog(Context context){
        this.context = context;
    }

    /*
    Crea un Dialog contenente il messaggio di vittoria.
    Al click del testo "Ok" l'applicazione viene chiusa
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Vittoria")
                .setMessage("Hai vinto! Clicca Ok per visualizzare i punteggi")
                .setPositiveButton("Ok", (dialog, which) -> {
                    //getActivity().finishAndRemoveTask();
                    //System.exit(0);
                    MainActivity.fragmentManager.beginTransaction()
                            .replace(R.id.container_fragment, new WinFragment(context)).commit();
                });
        return builder.create();
    }
}
