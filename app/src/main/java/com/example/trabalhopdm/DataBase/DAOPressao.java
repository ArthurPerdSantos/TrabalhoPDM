package com.example.trabalhopdm.DataBase;

import com.example.trabalhopdm.models.PressaoModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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

}
