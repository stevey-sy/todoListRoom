package com.example.roompractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        final AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries().build();

        mResultTextView.setText(appDatabase.todoDao().getAll().toString());
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDatabase.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultTextView.setText(appDatabase.todoDao().getAll().toString());
            }
        });
    }
}