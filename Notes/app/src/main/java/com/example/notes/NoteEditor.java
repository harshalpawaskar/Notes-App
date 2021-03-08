package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {

    EditText editTextTextMultiLine;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        editTextTextMultiLine = (EditText) findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        if(noteId!=-1)
        {
            editTextTextMultiLine.setText(MainActivity.notes.get(noteId));
        }
        else
        {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
        }

        editTextTextMultiLine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId,editTextTextMultiLine.getText().toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();
                HashSet<String> savedNotes = new HashSet(MainActivity.notes);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                sharedPreferences.edit().putStringSet("notes",savedNotes).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}