/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewClasses;

import java.util.ArrayList;
import java.util.List;
import nanowobiai.Populacja;

/**
 *
 * @author Kamil Sowa
 */
public class PlansCreator {
     private List<Plans> plansList=new ArrayList<Plans>();
     private Populacja population;

    public List<Plans> getPlansList() {
        return plansList;
    }

    public void setPlansList(List<Plans> plansList) {
        this.plansList = plansList;
    }

    public Populacja getPopulation() {
        return population;
    }

    public void setPopulation(Populacja population) {
        this.population = population;
    }

    public PlansCreator(Populacja population) {
        this.population = population;
        Plans plans;
        for (int i = 0; i < population.getChromosomy().size(); i++) {
            plans=new Plans(population.getChromosomy().get(i));
            plansList.add(plans);
        }
        
    }
    @Override
    public String toString()
    {
        for (int i = 0; i < plansList.size(); i++) {
            System.out.println("plan chromosomu"+i);
            plansList.get(i).print();
        }
       
        return "";
    }
    
}
