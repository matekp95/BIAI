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
    
    public Plans(ChromosomeDAO chromosome){
        this.chromosome=chromosome;
        this.numberOfClasses=chromosome.getNumberOfClasses();
        this.numberOfHours=chromosome.getNumberOfHours();
        this.numberofTeachers=chromosome.getNumberofTeachers();
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
    public void print(){
        for (Plan plan : plans){
            plan.matrixPrint();
            System.out.println();
        }
    }
}
