package com.georgy.androidtodolist;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.georgy.androidtodolist.TodoAdapter.customButtonListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements customButtonListener {

    private TodoService todoService;
    TodoAdapter adapter;
    List todos = new ArrayList<Todo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoService = new TodoService(this);
        todoService.open();
        todos = todoService.getAllTodos();

        ListView lv = (ListView) findViewById(R.id.list);
        //lv.setAdapter(new ArrayAdapter<Todo>(this, R.layout.list_view, R.id.title, todos));

        adapter = new TodoAdapter(this, todos);
        adapter.setCustomButtonListner(this);
        lv.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        /*adapter = new ListAdapter(MainActivity.this, dataItems);
        adapter.setCustomButtonListner(MainActivity.this);
        listView.setAdapter(adapter);*/


        //adapter.setCustomButtonListner(MainActivity.this);
        //lv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.button_create_todo) {
            TodoAddEdit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void TodoAddEdit () {
        Intent intent = new Intent(this, CreateTodoActivity.class);
        startActivity(intent);
    }



    @Override
    public void onButtonClickListner(int position, String value) {

        Todo todo = (Todo) todos.get(position);

        todoService.delete(todo.getId());
        adapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, todo.getTitle() + " is removed.",
                Toast.LENGTH_SHORT).show();


    }



}
