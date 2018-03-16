/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kamil Sowa
 */
public class DAO {
    
    private List<CLass> classes=new ArrayList<>();
    
    
    public static final int ROZMIAR_STRONY=3;
    
    public DAO()
    {
       Integer[] tab=new Integer[]{1,2,3,4,5};
       CLass p=new CLass(0, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,2,3,4,5};
       p=new CLass(1, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,2,3,4,5};
       p=new CLass(2, Arrays.asList(tab));
       classes.add(p);
       
       tab=new Integer[]{1,2,3,4,5};
       p=new CLass(3, Arrays.asList(tab));
       classes.add(p);
    }
    
    public List<CLass> getClasses()
    {
        return classes;
    }
   
    
}
