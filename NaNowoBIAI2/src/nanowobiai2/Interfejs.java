/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import viewCLasses.Plans;
import viewCLasses.PlansCreator;
/**
 *
 * @author Kamil
 */
public class Interfejs {

    public static void main(String[] args) {
           Populacja p=new Populacja();
           p.symulacja();
           Plans plans;
           ArrayList<Plans> planss=new ArrayList<>();
           for (ChromosomeDAO chromosomeDAO : p.getBestChromosome()) {
               plans=new Plans(chromosomeDAO);
               planss.add(plans);
            
           }
           System.out.println("inter");
           System.out.println("inter");
           System.out.println("inter");
           System.out.println("inter");
           
           for (Plans plans1 : planss) {
               plans1.print();
           }
           //p.getChromosomy().get(0).printChromosome();
         
          
//           
//           PlansCreator pc=new PlansCreator(p);
//           pc.toString();
          
           
    }

}
