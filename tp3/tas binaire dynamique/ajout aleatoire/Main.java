public class Main {

        public static void main(String[] args) {
            int i, time_ind = 0, pop_ind = 0;
            // creation du tas.
           Tas a = new Tas();
            long before, after;
             // Analyse du temps pris par les opérations.
            Analyzer time_analysis = new Analyzer();
            // Analyse du nombre de permutation faites par les opérations.
            Analyzer permutation_analysis = new Analyzer();
            // Analyse de l'espace mémoire inutilisé.
            Analyzer memory_analysis = new Analyzer();
            // Booléen permettant de savoir si une allocation a été effectuée.
           boolean memoire_allocation;
           int n;
           n=0;
           int j;
        for(i = 12000; i>1 ; i--){
                // Ajout d'un élément et mesure du temps pris par l'opération.
                before = System.nanoTime();
                j = (int)(Math.random() * 1200);
                n=a.ajouter(j);
                after = System.nanoTime();
                memoire_allocation=true;
    
        // Enregistrement du temps pris par l'opération
                time_analysis.append(after - before);
                // Enregistrement du nombre de copies efféctuées par l'opération.
                // S'il y a eu réallocation de mémoire, il a fallu recopier tout le tableau.
                //permutation_analysis.append( (nombre_permutation);
                permutation_analysis.append( (memoire_allocation == true)?n:0 );
                   // Enregistrement de l'espace mémoire non-utilisé.
           //     memory_analysis.append( a.capacite() - a.size());
        }
          // Affichage de quelques statistiques sur l'expérience.
            System.err.println("Total cost : "+time_analysis.get_total_cost());
            System.err.println("Average cost : "+time_analysis.get_average_cost());
            System.err.println("Variance :"+time_analysis.get_variance());
            System.err.println("Standard deviation :"+time_analysis.get_standard_deviation());
            
    
            // Sauvegarde les données de l'expérience: temps et nombre de permutation effectuées par opération.
            time_analysis.save_values("../plots/dynamic_array_time_java.plot");
            permutation_analysis.save_values("../plots/dynamic_array_permutation_java.plot");
             //   memory_analysis.save_values("../plots/dynamic_array_memory_java.plot");
    
       // int i;
        //Arbre_binomial A1=new Arbre_binomial();
        //for(i=0;i<100000;i++)A1.inserer_cle(i);
        }
    
    }
    