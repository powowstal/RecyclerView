package com.nbmaps.recyclerview.mediator;

import com.nbmaps.recyclerview.adapter.RVAdapter;
import com.nbmaps.recyclerview.pojo.Person;

/**
 * Created by Александр on 16.06.2017.
 */

public class Courier{

    private RVAdapter rv;

    public void setRv(RVAdapter rv) {
        this.rv = rv;
    }

    public void add(Person person){
        rv.copyItem(person);
    }
}
