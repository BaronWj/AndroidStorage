package com.example.panweijie.sdcarddemo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by panweijie on 16/1/21.
 */
public interface PersonService {
    public boolean addPerson(Object[] params);

    public boolean deletePerson(Object[] params);

    public boolean updatePerson(Object[] params);

    public Map<String,String> viewPerson(String[] selectionArgs);

    public List<Map<String,String>> viewPersonMaps(String[] selectionArgs);

}
