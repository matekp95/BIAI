/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewCLasses;

import java.util.ArrayList;
import java.util.List;
import chromosomeClasses.ChromosomeDAO;

/**
 *
 * @author Kamil Sowa
 */
public class Plans {
    /**
     * chromosome 
     */
    private ChromosomeDAO chromosome;
    /**
     * list of plans for Plans
     */
    private List<Plan> plans;
    /**
     * number of classes
     */
    private Integer numberOfClasses;
    /**
     * number of Days
     */
    private Integer numberOfHours;
    /**
     * number of days
     */
    private Integer numberOfDays;
    /**
     * number of teachers
     */
    private Integer numberofTeachers;
    /**
     * list of trachers names
     */
    private List<String> teacherNames;
    /**
     * list of rates
     */
    private List<Integer> rates;
    /**
     * rate of the day
     */
    private int rateOfTheDay;
    
    /**
     *Constructor
     * @param chromosome chromosome
     */
    public Plans(ChromosomeDAO chromosome){
        this.chromosome=chromosome;
        this.numberOfClasses=chromosome.getNumberOfCLasses();
        this.numberOfHours=chromosome.getNumberOfHours();
        this.numberofTeachers=chromosome.getNumberOfTeachers();
        this.numberOfDays=chromosome.getNumberOfDays();
        this.rates=new ArrayList<Integer>();
        this.plans=new ArrayList<Plan>();
        teacherNames=new ArrayList<String>();
        teacherNames.add("A");
        teacherNames.add("B");
        teacherNames.add("C");
        teacherNames.add("D");
        teacherNames.add("E");
        teacherNames.add("F");
        teacherNames.add("G");
        teacherNames.add("H");
        teacherNames.add("I");
        teacherNames.add("J");
        createPlans();
    }
    /**
     * Creates plans for every class based on chromosome
     */
    private void createPlans(){
        Plan plan;
        int day;
        int hour;
        for (int i=0;i<numberOfClasses;i++){
            plan= new Plan(numberOfHours, numberOfDays);
            
            for (int j=1;j<numberofTeachers+1;j++){
                 for (int z=0;z<numberOfHours*numberOfDays;z++){
                     if (chromosome.getMatrix().getField(i, z)!=null && chromosome.getMatrix().getField(i, z)==j){
                         hour=z%numberOfHours;
                         day=z/numberOfHours;
                         plan.setField(teacherNames.get(j-1), hour, day);
                     }
                }
            }
            plans.add(plan);
        }
        
        
    }
 
    /**
     *Prints plans for all classes
     */
    public void print(){
        System.out.println("Plany dla chromosomu: "+chromosome.getId()+" funkcja ocemy: "+chromosome.getRateOfChromosome());
        int i=0;
        for (Plan plan : plans){
            System.out.println("Plan dla klasy: "+i++);
            plan.matrixPrint();
            System.out.println();
        }
    }
}
