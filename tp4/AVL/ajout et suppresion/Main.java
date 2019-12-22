public class Main {

        public static void main(String[] args) {
            int i;
            // creation de l'arbre AVL
             AVL<Integer, Integer> A = new AVL<>();
            long before, after;
             // Analyse du temps pris par les opérations.
            Analyzer time_analysis = new Analyzer();
            // Analyse du nombre de permutation faites par les opérations.
            Analyzer permutation_analysis = new Analyzer();
            // Analyse de l'espace mémoire inutilisé.
            Analyzer memory_analysis = new Analyzer();
            // Booléen permettant de savoir si une allocation a été effectuée.
           boolean memoire_allocation;
           int j;
        for(i =0 ; i<12000 ; i++){
                // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                j = (int)(Math.random() * 2);
                if(j==1){
                        A.insert(i,i);
                    }
                else A.delete(i);
                after = System.nanoTime();
                memoire_allocation=true;
             // Enregistrement du temps pris par l'opération
                time_analysis.append(after - before);
                   // Enregistrement de l'espace mémoire non-utilisé.
                memory_analysis.append( A.getCapacite() - A.getSize());
        }
          // Affichage de quelques statistiques sur l'expérience.
            System.err.println("Total cost : "+time_analysis.get_total_cost());
            System.err.println("Average cost : "+time_analysis.get_average_cost());
            System.err.println("Variance :"+time_analysis.get_variance());
            System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());
            
    
            // Sauvegarde les données de l'expérience: temps et nombre de permutation effectuées par opération.
            time_analysis.save_values("../plots/avl_ajout_suppression_time_java.plot");
           // permutation_analysis.save_values("../plots/dynamic_array_permutation_java.plot");
             memory_analysis.save_values("../plots/avl_ajout_suppression_memory_java.plot");
    
        }
    
    }
    