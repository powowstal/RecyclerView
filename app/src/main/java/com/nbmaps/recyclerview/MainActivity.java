package com.nbmaps.recyclerview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbmaps.recyclerview.adapter.RVAdapter;
import com.nbmaps.recyclerview.mediator.Courier;
import com.nbmaps.recyclerview.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle_1;
    private RecyclerView recycle_2;
    private LinearLayoutManager mLayoutManager1, mLayoutManager2;
    private MyAdapter mAdapter;

    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RVAdapter adapter1 = null;
        RVAdapter adapter2 = null;
        Courier courier1 = new Courier();
        Courier courier2 = new Courier();

        recycle_1 = (RecyclerView) findViewById(R.id.recycle_1);
        recycle_2 = (RecyclerView) findViewById(R.id.recycle_2);

        // use a linear layout manager
        mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recycle_1.setLayoutManager(mLayoutManager1);
        recycle_2.setLayoutManager(mLayoutManager2);

        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        recycle_1.setAdapter(mAdapter);




        initializeData1();
        adapter1 = new RVAdapter(getApplicationContext() ,persons, courier1);
        recycle_1.setAdapter(adapter1);

        initializeData2();
        adapter2 = new RVAdapter(getApplicationContext() ,persons, courier2);
        recycle_2.setAdapter(adapter2);


        courier1.setRv(adapter2);
        courier2.setRv(adapter1);
    }

    private void initializeData1(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.bird1));
        persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.bird2));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.bird3));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.bird4));
    }

    private void initializeData2(){
        persons = new ArrayList<>();
        persons.add(new Person("Gray", "птица грязница", R.mipmap.bird3));
        persons.add(new Person("Red", "птица огница", R.mipmap.bird2));
        persons.add(new Person("Blue", "птица синица", R.mipmap.bird1));
        persons.add(new Person("many", "асасины", R.mipmap.bird4));
    }

//    private void initializeAdapter(RecyclerView recycle){
//        Context context;
//        context = getApplicationContext();
//        RVAdapter adapter = new RVAdapter(context ,persons);
//        recycle.setAdapter(adapter);
//    }
}
