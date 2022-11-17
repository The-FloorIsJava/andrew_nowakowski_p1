package com.revature.ReimbursementSystem.Util.Interface;

import java.util.List;

public interface Crudable<T, P> {
    T insert(T newObject);

    List<T> selectAll();

    T selectByField(String field, P value);

    boolean update(T updatedObject);

    boolean deleteByField(String field, P value);
}
