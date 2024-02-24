package id.ac.ui.cs.advprog.eshop.repository;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class DataRepository<T> {
    private List<T> listData = new ArrayList<>();

    public T create(T data) {
        listData.add(data);
        return data;
    }

    public Iterator<T> findAll() {
        return listData.iterator();
    }

}
