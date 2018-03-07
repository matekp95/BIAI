/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biai_console;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil Sowa
 */
public class Plans {
    private ChromosomeDAO chromosome;
    private List<Plan> plans;
    private Integer numberOfClasses;
    private Integer numberOfHours;
    private Integer numberofTeachers;
    private List<String> teacherNames;
    private List<Integer> rates;
    private int rateOfTheDay;
    
    public Plans(ChromosomeDAO chromosome){
        this.chromosome=chromosome;
        this.numberOfClasses=chromosome.getNumberOfClasses();
        this.numberOfHours=chromosome.getNumberOfHours();
        this.numberofTeachers=chromosome.getNumberofTeachers();
        this.rates=new ArrayList<Integer>();
        this.plans=new ArrayList<Plan>();
        teacherNames=new ArrayList<String>();
        teacherNames.add("A");
        teacherNames.add("B");
        teacherNames.add("C");
        teacherNames.add("D");
    }
    public void createPlans(){
        Plan plan;
        for (int i=1;i<numberOfClasses+1;i++){
            plan= new Plan(chromosome.getNumberOfHours(), 1);
            
            for (int j=0;j<numberofTeachers;j++){
                 for (int z=0;z<numberOfHours;z++){
                     if (chromosome.getMatrix().getField(j, z)!=null && chromosome.getMatrix().getField(j, z)==i){
                         plan.setField(teacherNames.get(j), z, 0);
                     }
                }
            }
            plans.add(plan);
        }
        
        
    }
     public void ratePlanInAllDays()
    {
        // 1. czy zajecia sa od 8                1p. 
        // 2. czy sa przerwy miedzy zajeciami    -1p.
        // 3. jak dluga przerwa miedzy zajeciami -2p.
          //  plan.rateDay();
        int sum;
       // int sumaDlaWszystkichKlasPon=0;
        for (Plan plan : plans) 
        {
            for (int numberOfDay = 0; numberOfDay < 1; numberOfDay++) 
            {
                sum = plan.rateDay(numberOfDay);
                rates.add(sum);
                rateOfTheDay+=sum;
                //System.out.println("Sum of day" + numberOfDay + " : " + sum);
            }
            sum=0;
        }
        
       
    }
    public void print(){
        int i=0;
        for (Plan plan : plans){
            plan.matrixPrint();
            System.out.println(rates.get(i));
            i++;
            System.out.println();
        }
        System.out.println("Rate of the day:" +rateOfTheDay);
    }
}
