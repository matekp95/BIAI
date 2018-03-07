/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kamil Sowa
 */
public class DAO {
     public static DAO instancja=new DAO();
    private List<Teacher> teachers=new ArrayList<>();
    
    public static final int ROZMIAR_STRONY=3;
    
    {
       Integer[] tab=new Integer[]{1,2,3,4};
       Teacher p=new Teacher( Arrays.asList(tab));
       teachers.add(p);
       
       tab=new Integer[]{1,2,3,4};
       p=new Teacher( Arrays.asList(tab));
       teachers.add(p);
       
       tab=new Integer[]{1,2};
       p=new Teacher( Arrays.asList(tab));
       teachers.add(p);
       
       tab=new Integer[]{3,4};
       p=new Teacher( Arrays.asList(tab));
       teachers.add(p);
    }
    public static DAO getInstancja(){
        return instancja;
    }
    public List<Teacher> getTeachers()
    {
        return teachers;
    }
   
    
}
