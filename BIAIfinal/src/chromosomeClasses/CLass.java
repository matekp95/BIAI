/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;

import java.util.List;

/**
 * 
 * @author Kamil Sowa
 */
public class CLass {
      private int Id;
      private List<Integer> listOfTaughtTeachers;
      private int rate;
      
    /**
     *Getter of id 
     * @return id of CLass
     */
    public int getId() {
        return Id;
    }

    /**
     *Setter of id
     * @param Id id of CLass
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Getter of getListOfTaughtTeachers
     * @return list of teacher that tought this CLass
     */
    public List<Integer> getListOfTaughtTeachers() {
        return listOfTaughtTeachers;
    }

    /**
     * Setter of getListOfTaughtTeachers
     * @param id id of CLass
     * @param listOfTaughtTeachers list of teacher that tought this CLass
     */
    public void setListOfTaughtTeachers(int id, List<Integer> listOfTaughtTeachers) {
        this.listOfTaughtTeachers = listOfTaughtTeachers;
        this.Id=id;
    }

    /**
     * Getter of rate 
     * @return rate of CLass
     */
    public int getRate() {
        return rate;
    }

    /**
     * Setter of rate 
     * @param rate rate of CLass
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Constructor
     * @param id id of CLass
     * @param listOfTaughtClasses list of teacher that tought this CLass
     */
    public CLass(int id,List<Integer> listOfTaughtClasses) {
        this.listOfTaughtTeachers = listOfTaughtClasses;
        this.Id=id;
    }

    /**
     * Getter of getListOfTaughtTeachers
     * @return list of teacher that tought this CLass
     */
    public List<Integer> getListOfTaughtClasses() {
        return listOfTaughtTeachers;
    }

    /**
     * Setter of getListOfTaughtTeachers
     * @param listOfTaughtClasses list of teacher that tought this CLass
     */
    public void setListOfTaughtClasses(List<Integer> listOfTaughtClasses) {
        this.listOfTaughtTeachers = listOfTaughtClasses;
    }

    /**
     * Print list of teacher for CLass
     */
    public void print()
    {
        DAO dao=new DAO();
        dao.getTecherNames();
        //System.out.print(listOfTaughtTeachers);
        System.out.print("[ ");
        for (Integer listOfTaughtTeacher : listOfTaughtTeachers) {
            System.out.print( dao.getTecherNames().get(listOfTaughtTeacher)+", ");
        }
        System.out.println(" ]");
 
                
    }

    /**
     * Print rate
     */
    public void printFunkcjaOceny()
    {
        System.out.print(rate);
    }
}
