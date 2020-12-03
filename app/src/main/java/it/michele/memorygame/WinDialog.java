package it.michele.memorygame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class WinDialog extends AppCompatDialogFragment {

    /*
    Crea un Dialog contenente il messaggio di vittoria.
    Al click del testo "Ok" l'applicazione viene chiusa
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Vittoria")
                .setMessage("Hai vinto! Riavvia l'app per un'altra partita")
                .setPositiveButton("Ok", (dialog, which) -> {
                    getActivity().finishAndRemoveTask();
                    System.exit(0);
                });
        return builder.create();
    }
}
