package id.duza.realmdb.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.duza.realmdb.R;
import id.duza.realmdb.model.Todo;
import io.realm.Realm;

public class EditorActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private Button btnSave;
    private Realm myRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        setupView();
        setupViewListener();
    }

    private void setupViewListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }

    private void saveData() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(title))   { //if not empty
            commitToRealm(title, description);
        }
    }

    private void commitToRealm(String title, String description) {
            myRealm = Realm.getDefaultInstance();
            myRealm.beginTransaction();
            Todo todo = myRealm.createObject(Todo.class);
            todo.setTitle(title);
            todo.setDescription(description);

            myRealm.commitTransaction();
    }

    private void setupView() {
        etTitle = (EditText) findViewById(R.id.et_title);
        etDescription = (EditText) findViewById(R.id.et_desc);
        btnSave = (Button) findViewById(R.id.btn_save);
    }
}
