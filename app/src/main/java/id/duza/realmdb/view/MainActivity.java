package id.duza.realmdb.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import id.duza.realmdb.R;
import id.duza.realmdb.adapter.TodoAdapter;
import id.duza.realmdb.model.Todo;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;
    private Realm myRealm;
    private RealmResults<Todo> todos;
    private List<Todo> todoList = new ArrayList<>();
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        setupView();
        setupViewListener();
        setupRealm();
    }

    private void setupViewListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
    }

    private void setupRealm() {
        myRealm = Realm.getDefaultInstance();
        todos = myRealm.where(Todo.class)
                .findAll();


        todos.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Todo>>() {
            @Override
            public void onChange(RealmResults<Todo> collection, OrderedCollectionChangeSet changeSet) {
                todoList.clear();
                todoList.addAll(collection);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoList.clear();
        todoList.addAll(todos);
        adapter.notifyDataSetChanged();
    }

    private void setupView() {
        listView = (ListView) findViewById(R.id.list_main);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        adapter = new TodoAdapter(this, todoList);
        listView.setAdapter(adapter);
    }
}
