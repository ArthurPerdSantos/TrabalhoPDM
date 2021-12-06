package com.example.trabalhopdm.DataBase;

import android.app.backup.BackupHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhopdm.models.UsuarioModel;
import com.example.trabalhopdm.models.VacinaModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Locale;

public class DAOVacina {
    private DatabaseReference db_ref;

    public DAOVacina() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db_ref = db.getReference(VacinaModel.class.getSimpleName());

    }

    public Task<Void> add(VacinaModel vacina) {
        return db_ref.child(vacina.getId_usario()).child(vacina.getDose()).setValue(vacina);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return db_ref.child(key).child(hashMap.get("dose").toString()).updateChildren(hashMap);

    }

}
