package com.example.trabalhopdm.DataBase;

import android.app.backup.BackupHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhopdm.models.BatimentosModel;
import com.example.trabalhopdm.models.UsuarioModel;
import com.example.trabalhopdm.models.VacinaModel;
import com.example.trabalhopdm.utils.RetornoQuerys;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    public void QueryPegaDadosModel(String key, final RetornoQuerys retorno) {
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<String> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(VacinaModel.class.getSimpleName()).child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                map.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    map.put(snapshot1.getKey(), snapshot1.getValue().toString());
                }
                retorno.onCallback(map);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
