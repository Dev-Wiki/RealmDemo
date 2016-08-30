package net.devwiki.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.devwiki.realmdemo.bean.Cat;
import net.devwiki.realmdemo.bean.Dog;
import net.devwiki.realmdemo.bean.Person;
import net.devwiki.realmdemo.bean.PetColor;
import net.devwiki.realmdemo.util.UUIDUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class PersonActivity extends AppCompatActivity {

    @BindView(R.id.operator_tv)
    TextView operatorTv;

    private Realm realm;
    private RealmResults<Person> results;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
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
                person = DemoApp.getRealm().createObject(Person.class);
                person.setId(UUIDUtil.getUUID());
                person.setName("张三");
                person.setAge(15);
                person.setSex(1);

                Cat cat1 = DemoApp.getRealm().createObject(Cat.class);
                cat1.setName("大喵");
                cat1.setId(UUIDUtil.getUUID());
                cat1.setColor(PetColor.BLACK);
                person.getCats().add(cat1);

                Cat cat2 = DemoApp.getRealm().createObject(Cat.class);
                cat2.setName("小喵");
                cat2.setId(UUIDUtil.getUUID());
                cat2.setColor(PetColor.BROWN);
                person.getCats().add(cat2);

                Dog dog1 = DemoApp.getRealm().createObject(Dog.class);
                dog1.setName("大汪");
                dog1.setId(UUIDUtil.getUUID());
                dog1.setColor(PetColor.BLACK);
                person.getDogs().add(dog1);
            }
        });
    }

    private void delete() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Person.class).equalTo("name", "张三").findAll().deleteAllFromRealm();
            }
        });
    }

    private void query() {
        results = realm.where(Person.class).equalTo("name", "张三").findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<Person>>() {
            @Override
            public void onChange(RealmResults<Person> element) {
                if (element.size() > 0) {
                    Person person = element.first();
                    operatorTv.append(person.toString() + "\n");
                }
            }
        });
    }

    private void update() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = DemoApp.getRealm().createObject(Dog.class);
                dog.setName("小汪");
                dog.setId(UUIDUtil.getUUID());
                dog.setColor(PetColor.GREY);
                person.getDogs().add(dog);
            }
        });
    }
}
