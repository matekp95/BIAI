/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
           Plans pl=new Plans(p.getChromosomy().get(0));
           p.getChromosomy().get(0).printChromosome();
           pl.print();
          
//           
//           PlansCreator pc=new PlansCreator(p);
//           pc.toString();
          
           
    }

}
