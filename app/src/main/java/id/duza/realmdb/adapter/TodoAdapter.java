package id.duza.realmdb.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.duza.realmdb.R;
import id.duza.realmdb.model.Todo;

/**
 * Created by dhiyaulhaqza on 3/25/17.
 */

public class TodoAdapter extends ArrayAdapter<Todo> {

    private Context context;

    public TodoAdapter(@NonNull Context context, @NonNull List<Todo> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDesc);

        Todo todo = getItem(position);
        if (todo != null) {
            tvTitle.setText(todo.getTitle());
            tvDescription.setText(todo.getDescription());
        }
        return convertView;
    }
}
