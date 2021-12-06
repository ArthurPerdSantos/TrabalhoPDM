package com.example.trabalhopdm.DataBase;

import androidx.annotation.NonNull;

import com.example.trabalhopdm.models.BatimentosModel;
import com.example.trabalhopdm.models.PressaoModel;
import com.example.trabalhopdm.utils.RetornoQuerys;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;

public class DAOPressao {
    private DatabaseReference db_ref;

    public DAOPressao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db_ref = db.getReference(PressaoModel.class.getSimpleName());

    }

    public Task<Void> add(PressaoModel pressao) {
        return db_ref.child(pressao.getId_usuario()).setValue(pressao);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return db_ref.child(key).updateChildren(hashMap);

    }

    public void QueryPegaDadosModel(String key, final RetornoQuerys retorno) {
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<String> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(PressaoModel.class.getSimpleName()).child(key);
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
