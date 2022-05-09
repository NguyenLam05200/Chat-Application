/*
 * Click nbfs://nbhost/SystemFileSystem/Objectemplates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Objectemplates/Classes/Class.java to edit this template
 */
package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author holohoi
 */
public class ObjectHanlde {

    public static Object[] concatObject(Object[] array1, Object[] array2) {
        List<Object> resultList = new ArrayList<>(array1.length + array2.length);
        Collections.addAll(resultList, array1);
        Collections.addAll(resultList, array2);

        Object[] resultArray = (Object[]) Array.newInstance(array1.getClass().getComponentType(), 0);
        return resultList.toArray(resultArray);
    }

    public static void main(String[] args) {
        Object[] x = new Object[]{"x", "value x"};
        Object[] y = new Object[]{"y", "value y"};
        Object[] r = concatObject(x, y);
        for (Object e : r) {
            System.out.println(e);
        }
    }
}
