public class Main {

    public static void main(String[] args) {
        // creation du tas.
       BinomialHeap ta = new BinomialHeap();
        long before, after;
         // Analyse du temps pris par les opérations.
        Analyzer time_analysis = new Analyzer();
        // Analyse du nombre de permutation faites par les opérations.
        Analyzer permutation_analysis = new Analyzer();
	// Analyse de l'espace mémoire inutilisé.
	Analyzer memory_analysis = new Analyzer();
       int j,i,a=0;
    for(i = 1; i<12000 ; i++){
            // Ajout d'un élément et mesure du temps pris par l'opération.
            before = System.nanoTime();
            j = (int)(Math.random() * 1);
            if(j!=0) {
                ta.insert(i);
                }
            else {a=ta.extractMin();}
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
        // Sauvegarde les données de l'expérience: temps et nombre de permutation effectuées par opération.
        time_analysis.save_values("../plots/tas_binomiaux_ajout_suppression_time_java.plot");
	    memory_analysis.save_values("../plots/tas_bimomiaux_ajout_suppression_memory_java.plot");
    }

}
