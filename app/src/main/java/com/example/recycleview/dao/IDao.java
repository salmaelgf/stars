package com.example.recycleview.dao;

import com.example.recycleview.beans.Star;

import java.util.ArrayList;
import java.util.List;

public interface IDao<T>{
        boolean create(T o);
        boolean update(T o);
        boolean delete(T o);
        T findById(int id);
        List<T> findAll();
    }


