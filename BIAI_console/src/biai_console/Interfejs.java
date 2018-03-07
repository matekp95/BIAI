/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author Kamil
 */
public class Interfejs {

    public static void main(String[] args) {
            ChromosomeDAO chromosom=new ChromosomeDAO(4, 5, 4);
           
            chromosom.printTeachersClasses();
            
            chromosom.createRandomMatrix();
            chromosom.printChromosome();
            Plans plans=new Plans(chromosom);
            plans.createPlans();
            plans.print();
	    	
    }

}
