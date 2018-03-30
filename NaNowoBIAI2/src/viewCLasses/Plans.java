/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewCLasses;

import java.util.ArrayList;
import java.util.List;
import nanowobiai2.ChromosomeDAO;

/**
 *
 * @author Kamil Sowa
 */
public class Plans {
    private ChromosomeDAO chromosome;
    private List<Plan> plans;
    private Integer numberOfClasses;
    private Integer numberOfHours;
    private Integer numberOfDays;
    private Integer numberofTeachers;
    private List<String> teacherNames;
    private List<Integer> rates;
    private int rateOfTheDay;
    
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
 
    public void print(){
        int i=0;
        for (Plan plan : plans){
            System.out.println("Plany dla chromosomu"+chromosome.getId());
            plan.matrixPrint();
            i++;
            System.out.println();
        }
    }
}
