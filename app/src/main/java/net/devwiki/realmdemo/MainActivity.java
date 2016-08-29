package net.devwiki.realmdemo;

import android.gesture.Prediction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.devwiki.realmdemo.bean.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_rv)
    RecyclerView listRv;
    @BindView(R.id.result_tv)
    TextView resultTv;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = DemoApp.getRealm();
    }

    @OnClick({R.id.insert_btn, R.id.delete_btn, R.id.update_btn, R.id.query_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_btn:
                insert();
                break;
            case R.id.delete_btn:
                delete();
                break;
            case R.id.update_btn:
                update();
                break;
            case R.id.query_btn:
                query();
                break;
        }
    }

    private void insert() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = DemoApp.getRealm().createObject(Person.class);
                person.setId("id-001");
                person.setName("name");
                person.setAge(15);
                person.setSex(1);
            }
        });
    }

    private void delete() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Person.class).equalTo("name", "name").findAll().deleteAllFromRealm();
            }
        });
    }

    private void query() {
        RealmResults<Person> results = realm.where(Person.class).equalTo("name", "name").findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<Person>>() {
            @Override
            public void onChange(RealmResults<Person> element) {
                if (element.size() > 0) {
                    Person person = element.first();
                    resultTv.append(person.toString() + "\n");
                }
            }
        });
    }

    private void update() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.where(Person.class).equalTo("name", "name").findFirst();
                person.setAge(person.getAge() + 1);
            }
        });
    }
}
