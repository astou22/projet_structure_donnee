public class Main {

        public static void main(String[] args) {
            int i;
            // creation du tas binomial
             BinomialHeap ta=new BinomialHeap();
            long before, after;
             // Analyse du temps pris par les opérations.
            Analyzer time_analysis = new Analyzer();
            // Analyse de l'espace mémoire inutilisé.
            Analyzer memory_analysis = new Analyzer();
        for(i = 0; i <12000 ; i++){
                before = System.nanoTime();
                ta.insert(i);
                after = System.nanoTime();
               // Enregistrement du temps pris par l'opération
                time_analysis.append(after - before);
               // Enregistrement de l'espace mémoire non-utilisé.
                memory_analysis.append( ta.getCapacite() - ta.getSize());
        }
          // Affichage de quelques statistiques sur l'expérience.
            System.err.println("Total cost : "+time_analysis.get_total_cost());
            System.err.println("Average cost : "+time_analysis.get_average_cost());
            System.err.println("Variance :"+time_analysis.get_variance());
            System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());
            
    
            // Sauvegarde les données de l'expérience: temps et mémoire 
            time_analysis.save_values("../plots/tas_binomiaux_ajout_time_java.plot");
           memory_analysis.save_values("../plots/tas_binomiaux_ajout_memory_java.plot");
    
       
        }
    
    }
    