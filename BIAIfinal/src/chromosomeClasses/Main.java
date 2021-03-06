/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromosomeClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import viewCLasses.Plans;

import static chromosomeClasses.ConstansInterface.NUMBER_OF_CHROMOSOMES;
/**
 * Starts algorithm. Shows results of genetic algorithm
 * @author Kamil
 */
public class Main implements ConstansInterface {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Population p=new Population();
        System.out.println("Liczba Chromosomów:  "+NUMBER_OF_CHROMOSOMES);
        System.out.println("Liczba klas:  "+NUMBER_OF_CLASSES);
        System.out.println("Liczba dni z zajęciami lekcyjnymi:  "+NUMBER_OF_DAYS);
        System.out.println("Liczba nauczycieli:  "+NUMBER_OF_TEACHERS);
        
        int NUMBER_OF_GENERATIONS;
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        Integer valueOf = null;
        while ( valueOf==null )
        {
             System.out.println("Podaj liczbe generacji: ");
             try {
                valueOf = Integer.valueOf(odczyt.nextLine()) ;
             }
             catch (Exception ex) {
                 
             }
        }
        
        //NUMBER_OF_GENERATIONS = Integer.parseInt( odczyt.nextLine());
        NUMBER_OF_GENERATIONS=valueOf;
        
        p.symulacja(NUMBER_OF_GENERATIONS);

        Plans plans;
        ArrayList<Plans> planss=new ArrayList<>();
        for (ChromosomeDAO chromosomeDAO : p.getBestChromosome()) {
            plans=new Plans(chromosomeDAO);
            planss.add(plans);
        }


        System.out.println("Pokazac najlepsze chromosomy? (y/n) ");
        String answer  = odczyt.nextLine();
        switch (answer)
        {
            case "y":
                for (Plans plans1 : planss) {
                     plans1.print();
                }
                break;


         }       
           
    }

}
