/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanowobiai;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static nanowobiai.ConstansInterface.NUMBER_OF_CHROMOSOMES;
import static nanowobiai.ConstansInterface.NUMBER_OF_CLASSES;
import static nanowobiai.ConstansInterface.NUMBER_OF_HOURS;
import static nanowobiai.ConstansInterface.NUMBER_OF_TEACHERS;
import viewClasses.Plans;
/**
 *
 * @author Kamil Sowa
 */
public class Populacja {
    Float[] RuletteWheel;
     private List<ChromosomeDAO> chromosomy=new ArrayList<ChromosomeDAO>();

    public Float[] getRuletteWheel() {
        return RuletteWheel;
    }

    public void setRuletteWheel(Float[] RuletteWheel) {
        this.RuletteWheel = RuletteWheel;
    }

    /**
     *
     * @return
     */
    public List<ChromosomeDAO> getChromosomy() {
        return chromosomy;
    }

    public void setChromosomy(List<ChromosomeDAO> chromosomy) {
        this.chromosomy = chromosomy;
    }
         
     
    public Populacja(){
        RuletteWheel=new Float[NUMBER_OF_CHROMOSOMES];
    }
   
    {
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
            ChromosomeDAO chromosom=new ChromosomeDAO(i,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);
            chromosom.createRandomMatrix();
            chromosomy.add(chromosom);
        }
    }
    public void symulacja()
    {
        ChromosomeDAO chromosom=new ChromosomeDAO(1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);

           chromosom.printTeachersClasses();

           for (ChromosomeDAO ch: chromosomy)
           {
            ch.rateChromosome();
            ch.printChromosome();
           }
           for (int i=0;i<1;i++){
              rateAllChromosome();
              if (setAllChromosomeProbabiltyOfExistance())   { 
                     createNewPopulationBasedOnRouletteWheel();
                }
              crossingOverAllPopulation();
          }
            
//          for (ChromosomeDAO ch: chromosomy)
//           {
//            ch.rateChromosome();
//            ch.printChromosome();
//           }
            
          // losujemy jaka czesc populacji krzyżować
          //crossingOverAllPopulation();
          // crossing, when rand 0-25 then 1-3, 25-75 then 2-2, 75-100 then 3-1
          System.out.println("NOWE CHROMOSOMY!!!!!!!!!!!!!!!!!!");
            for (ChromosomeDAO ch: chromosomy)
           {
            ch.rateChromosome();
            ch.printChromosome();
           }
            
    }
    
    public void crossingOverAllPopulation()
    {
        // losujemy jaka czesc populacji krzyżować
        int counter = NUMBER_OF_CHROMOSOMES;
        List<ChromosomeDAO> tempArray = new ArrayList<>();
        List<ChromosomeDAO> tempArrayShow = new ArrayList<>();
        Random rand=new Random();
       int randomowa;
        
        List<Integer> listOfId = new ArrayList<>();
       for(int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
       {
          listOfId.add(rand.nextInt(NUMBER_OF_CHROMOSOMES));
       }
        for(int i=0;i<NUMBER_OF_CHROMOSOMES;i+=2){
            
            ChromosomeDAO chr1 = chromosomy.get(listOfId.get(i));
            ChromosomeDAO chr2 = chromosomy.get(listOfId.get(i+1));
            randomowa = rand.nextInt(100);
            if(randomowa<25)
            {
//            System.out.println(" RAND < 25 ");
//  
//            System.out.println("PIERWSZY CHROMOSOM DO KRZYZOWANIA" );
//            chr1.printChromosome();
//            System.out.println("DRUGI CHROMOSOM DO KRZYZOWANIA" );
//            chr2.printChromosome();
            
            tempArrayShow.addAll(crossingOver(chr1,chr2,chr1.getId(),chr2.getId(),1));
            tempArray.addAll(tempArrayShow);
            
//            System.out.println("PO KRZYZOWANIU 1");
//            tempArrayShow.get(0).printChromosome();
//            System.out.println("PO KRZYZOWANIU  2");
//            tempArrayShow.get(1).printChromosome();
            tempArrayShow.clear();
            }
            else if(randomowa < 75)
            {
//                System.out.println(" RAND < 75 ");
//           
//            System.out.println("PIERWSZY CHROMOSOM DO KRZYZOWANIA" );
//            chr1.printChromosome();
//            System.out.println("DRUGI CHROMOSOM DO KRZYZOWANIA" );
            chr2.printChromosome();
            
           tempArrayShow.addAll(crossingOver(chr1,chr2,chr1.getId(),chr2.getId(),2));
            tempArray.addAll(tempArrayShow);
//            
//            System.out.println("PO KRZYZOWANIU 1");
//            tempArrayShow.get(0).printChromosome();
//            System.out.println("PO KRZYZOWANIU  2");
//            tempArrayShow.get(1).printChromosome();
            tempArrayShow.clear();
            }
            else
            {
//                System.out.println(" RAND < 100 ");
//            
//             System.out.println("PIERWSZY CHROMOSOM DO KRZYZOWANIA" );
//            chr1.printChromosome();
//            System.out.println("DRUGI CHROMOSOM DO KRZYZOWANIA" );
//            chr2.printChromosome();
            
           tempArrayShow.addAll(crossingOver(chr1,chr2,chr1.getId(),chr2.getId(),3));
            tempArray.addAll(tempArrayShow);
//            
//            System.out.println("PO KRZYZOWANIU 1");
//            tempArrayShow.get(0).printChromosome();
//            System.out.println("PO KRZYZOWANIU  2");
//            tempArrayShow.get(1).printChromosome();
            tempArrayShow.clear();
            }
            
        }
        chromosomy.clear();
//        for(int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
//        {
//        tempArray.get(i).setId(i);
//        }
        chromosomy.addAll(tempArray);
        
        // sprawdzenie powtórzeń
        repairingFunction();
        
        
        for (int i = 0; i < NUMBER_OF_CHROMOSOMES; i++) {
            chromosomy.get(i).setId(i);
        }
        
    }
    
    public void repairingFunction()
    {
        chromosomy.forEach((ch) -> {
            ch.repairFunction();
        });
    }
    public ArrayList<ChromosomeDAO> crossingOver(ChromosomeDAO chr1, ChromosomeDAO chr2, Integer id1, Integer id2, Integer rowCountFromParent1)
    {
        ChromosomeDAO chrNowy = new ChromosomeDAO(id1,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);
        ChromosomeDAO chrNowy2 = new ChromosomeDAO(id2,NUMBER_OF_TEACHERS , NUMBER_OF_HOURS, NUMBER_OF_CLASSES);
        for (int i=0;i<rowCountFromParent1;i++)
        {
            chrNowy.setCLassPlan(i, chr1.getMatrix().getSingleRow(i));
            chrNowy2.setCLassPlan(i, chr2.getMatrix().getSingleRow(i));
        }
        for(int i=rowCountFromParent1;i<chr1.getNumberOfCLasses();i++)
        {
            chrNowy.setCLassPlan(i, chr2.getMatrix().getSingleRow(i));
            chrNowy2.setCLassPlan(i, chr1.getMatrix().getSingleRow(i));
        }
        ArrayList<ChromosomeDAO> temp = new ArrayList<ChromosomeDAO>();
        temp.add(chrNowy);
        temp.add(chrNowy2);
        return temp;
    }

    
    public void printKoloRuletki()
    {
        int i=0;
        System.out.println("id " +"rate "+ "prob "+" rulet");
        for(ChromosomeDAO chr: chromosomy)
        {
            
            System.out.println(chr.getId()+"  " + chr.getRateOfChromosome()+"  "+ String.format("%.2f", chr.getProbabilityOfExistance())+"   "+String.format("%.2f", RuletteWheel[i]));
            i++;
        }
    }
    public boolean setAllChromosomeProbabiltyOfExistance()
    {
        //(Wartość najgorszego osobnika-Wartość danego osobnika+1)/(suma wartości wszystkich osobników+1)
        ChromosomeDAO worstChromosome;
        worstChromosome=getTheWorstChromosome();
        int sumOfRateOfAllChromosome=getTheSumOfRateOfAllChromosome();
        float probability;
        float sumProbabilty=0;
        float sumProbabiltyAfter=0;
        for(ChromosomeDAO chr: chromosomy)
        {
           probability=(float) (worstChromosome.getRateOfChromosome()-chr.getRateOfChromosome()+1)/(sumOfRateOfAllChromosome);
           //probability=(float) chr.getRateOfChromosome()/sumOfRateOfAllChromosome;
           sumProbabilty+=probability;
           chr.setProbabilityOfExistance(probability);
        }
        System.out.println("sumProb: "+sumProbabilty);
        
        for(ChromosomeDAO chr: chromosomy)
        {
           chr.setProbabilityOfExistance(chr.getProbabilityOfExistance()/sumProbabilty);
            //System.out.println(chr.getProbabilityOfExistance());
            sumProbabiltyAfter+=chr.getProbabilityOfExistance();
        }
        System.out.println("sumProbabiltyAfter: "+sumProbabiltyAfter);
        
        
        if (sumProbabiltyAfter>0.98 && sumProbabiltyAfter<1.02)
            return true;
        else
            return false;
    }

    private int getTheSumOfRateOfAllChromosome() {
        int sum=0;
        for(ChromosomeDAO chr: chromosomy)
        {
            sum+=chr.getRateOfChromosome();
        }
        return sum;
    }

    private ChromosomeDAO getTheWorstChromosome() {
        int worstRate=0;
        ChromosomeDAO worstChromosome = chromosomy.get(0);
        for(ChromosomeDAO chr: chromosomy)
        {
            if (worstRate< chr.getRateOfChromosome())
            { 
                worstRate=chr.getRateOfChromosome();
               worstChromosome=chr;
            }
        }
        return worstChromosome;
    }
    
    public void createNewRuletteWheel()
    {
       
        Float sumOfProbability = 0.0f;
        System.out.println();
        System.out.println();
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           RuletteWheel[i]=chromosomy.get(i).getProbabilityOfExistance()+sumOfProbability;
           sumOfProbability+= chromosomy.get(i).getProbabilityOfExistance();
        }
    }
    /**
     * Creates new population
     */
    public List<ChromosomeDAO> createNewPopulationBasedOnRouletteWheel()
    {
        createNewRuletteWheel();
        
        List<ChromosomeDAO> chromosomyNewPopulation=new ArrayList<ChromosomeDAO>();
        Random rand = new Random();
        Float randomNumber;
        for (int i=0;i<NUMBER_OF_CHROMOSOMES;i++)
        {
           
            randomNumber=rand.nextFloat();
            System.out.println("randomNumber "+randomNumber);
            for (int j=0;j<RuletteWheel.length;j++){
                if (randomNumber<RuletteWheel[j])
                {
                   // chromosomyNewPopulation.add(chromosomy.get(j));
                    chromosomyNewPopulation.add(new ChromosomeDAO(chromosomy.get(j)));
                    break;
                }
            }
        }
        System.out.println("choosen");
        for (ChromosomeDAO chr:chromosomyNewPopulation){
            System.out.println(chr.getProbabilityOfExistance()+ "  "+chr.getId());
        }
        System.out.println("");
        
        printKoloRuletki();

        chromosomy=chromosomyNewPopulation;
        
        for (int i = 0; i < NUMBER_OF_CHROMOSOMES; i++) {
            chromosomy.get(i).setId(i);
        }
        return chromosomyNewPopulation;
    }
    public void rateAllChromosome(){
        for (ChromosomeDAO chr:chromosomy){
            chr.rateChromosome();
        }
    }

            
}
