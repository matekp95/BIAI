/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;

import java.util.List;

/**
 *
 * @author Kamil Sowa
 */
public class CLass {
        private int Id;
      private List<Integer> listOfTaughtTeachers;
      private int rate;
      
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public List<Integer> getListOfTaughtTeachers() {
        return listOfTaughtTeachers;
    }

    public void setListOfTaughtTeachers(int id, List<Integer> listOfTaughtTeachers) {
        this.listOfTaughtTeachers = listOfTaughtTeachers;
        this.Id=id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public CLass(int id,List<Integer> listOfTaughtClasses) {
        this.listOfTaughtTeachers = listOfTaughtClasses;
         this.Id=id;
    }

    public List<Integer> getListOfTaughtClasses() {
        return listOfTaughtTeachers;
    }

    public void setListOfTaughtClasses(List<Integer> listOfTaughtClasses) {
        this.listOfTaughtTeachers = listOfTaughtClasses;
    }
    public void print()
    {
        System.out.print(listOfTaughtTeachers);
    }
    public void printFunkcjaOceny()
    {
        System.out.print(rate);
    }
}
