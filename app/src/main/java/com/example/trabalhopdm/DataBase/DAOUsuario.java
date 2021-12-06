package com.example.trabalhopdm.DataBase;

import com.example.trabalhopdm.models.UsuarioModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOUsuario {
    private DatabaseReference db_ref;

    public DAOUsuario() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db_ref = db.getReference(UsuarioModel.class.getSimpleName());

    }

    public Task<Void> add(UsuarioModel usuario) {
        return db_ref.child(usuario.getId()).setValue(usuario);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return db_ref.child(key).updateChildren(hashMap);
    }


}
