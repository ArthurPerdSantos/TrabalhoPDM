package com.example.trabalhopdm.DataBase;

import androidx.annotation.NonNull;

import com.example.trabalhopdm.utils.RetornoQuerys;
import com.example.trabalhopdm.models.BatimentosModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DAOBatimentos {
    private DatabaseReference db_ref;

    public DAOBatimentos() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db_ref = db.getReference(BatimentosModel.class.getSimpleName());

    }

    public Task<Void> add(BatimentosModel batimentos) {
        return db_ref.child(batimentos.getId_usuario()).setValue(batimentos);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return db_ref.child(key).updateChildren(hashMap);

    }

    public void Query(String key, final RetornoQuerys retorno) {
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<String> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(BatimentosModel.class.getSimpleName()).child(key);
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
