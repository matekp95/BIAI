/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Data acces object class. Holds names of teachers and list of teachers that are tought by class.
 * @author Kamil Sowa
 */

public class DAO {
    /**
     * list of teachers that teach class
     */
    private List<CLass> classes=new ArrayList<>();
    /**
     * map of names of teachers
     */
    HashMap<Integer,String> teacherNames=new HashMap<Integer,String>();
    
    /**
     *
     */
    public static final int ROZMIAR_STRONY=3;
    
    /**
     *
     */
    public DAO()
    {
       Integer[] tab=new Integer[]{1,2,3,4,5,6,7,8,9,10};
       CLass p=new CLass(0, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,2,3,3,4,4,5,6,7,8,9,10};
       p=new CLass(1, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,1,2,3,4,5,6,7,7,8,8};
       p=new CLass(2, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,2,3,4,5,6,6,7,7,8,8};
       p=new CLass(3, Arrays.asList(tab));
       classes.add(p);
       
       
       
        teacherNames.put(1, "A");
        teacherNames.put(2, "B");
        teacherNames.put(3, "C");
        teacherNames.put(4, "D");
        teacherNames.put(5, "E");
        teacherNames.put(6, "F");
        teacherNames.put(7, "G");
        teacherNames.put(8, "H");
        teacherNames.put(9, "I");
        teacherNames.put(10, "J");
        
    }
    
    /**
     *Getter for all classes 
     * @return
     */
    public List<CLass> getClasses()
    {
        return classes;
    }
   /**
     *Getter for map of teacher names
     * @return
     */
    public HashMap<Integer,String> getTecherNames()
    {
        return teacherNames;
    }
    
}
