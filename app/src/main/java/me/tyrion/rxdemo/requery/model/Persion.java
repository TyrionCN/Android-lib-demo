package me.tyrion.rxdemo.requery.model;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Index;
import io.requery.Key;

/**
 * Created by taomaogan on 2016/12/9.
 */
@Entity
public interface Persion {

    @Key @Generated
    int getId();

    @Index(value = "name")
    String getName();

    @Index(value = "age")
    String getAge();

    @Index(value = "address")
    String getAddress();
}
