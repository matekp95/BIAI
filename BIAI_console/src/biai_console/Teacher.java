/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;

import java.util.List;

/**
 *
 * @author Kamil Sowa
 */
public class Teacher {
    private List<Integer> listOfTaughtClasses;

    public Teacher(List<Integer> listOfTaughtClasses) {
        this.listOfTaughtClasses = listOfTaughtClasses;
    }

    public List<Integer> getListOfTaughtClasses() {
        return listOfTaughtClasses;
    }

    public void setListOfTaughtClasses(List<Integer> listOfTaughtClasses) {
        this.listOfTaughtClasses = listOfTaughtClasses;
    }
    public void print()
    {
        System.out.print(listOfTaughtClasses);
    }
    
}
