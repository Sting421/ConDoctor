package com.example.rawdb;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void testUserMethod(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }



}
