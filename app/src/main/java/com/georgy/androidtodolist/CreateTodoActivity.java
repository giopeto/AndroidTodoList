package com.georgy.androidtodolist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateTodoActivity extends ActionBarActivity {

    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        todoService = new TodoService(this);
        todoService.open();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Todo");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_app);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_create_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createTodo(View view) {

        EditText title = (EditText) findViewById(R.id.title);
        EditText description = (EditText) findViewById(R.id.description);

        if (title.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter todo title", Toast.LENGTH_SHORT).show();
        } else if (description.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter todo description", Toast.LENGTH_SHORT).show();
        } else {
            title.setOnEditorActionListener(new DoneOnEditorActionListener());
            description.setOnEditorActionListener(new DoneOnEditorActionListener());

            DatePicker myDatePicker = (DatePicker) findViewById(R.id.for_date);
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
            String forDate = df.format(new Date(myDatePicker.getYear() - 1900, myDatePicker.getMonth(), myDatePicker.getDayOfMonth()));

            Todo allTodos = todoService.set(title.getText().toString(), description.getText().toString(), forDate.toString());

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
