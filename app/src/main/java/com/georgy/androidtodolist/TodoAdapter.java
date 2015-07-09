package com.georgy.androidtodolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgy on 03.07.15.
 */
public class TodoAdapter extends ArrayAdapter<Todo> {

    customButtonListener customListner;
    Context context;
    List todos = new ArrayList<Todo>();

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    public TodoAdapter(Context context, List<Todo> todos) {
        super(context, R.layout.list_view, todos);
        this.todos = todos;
        this.context = context;
    }

    public class ViewHolder {
        TextView title;
        TextView description;
        TextView for_date;
        Button button;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final  Todo todo;
        todo = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView
                    .findViewById(R.id.title);

            viewHolder.description = (TextView) convertView
                    .findViewById(R.id.description);

            viewHolder.button = (Button) convertView
                    .findViewById(R.id.deleteBtn);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView for_date = (TextView) convertView.findViewById(R.id.for_date);

        // Populate the data into the template view using the data object
        title.setText(todo.getTitle());
        description.setText(todo.getDescription());
        for_date.setText(todo.getForDate());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(position, todo.toString());
                }

            }
        });

        return convertView;
    }




}
