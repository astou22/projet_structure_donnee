import java.util.ArrayList; // import the ArrayList class

public class Tas {
    //int[] tas;
    ArrayList<Integer> tas ;
    int nTas;// nombre element du Tas
    int nb_permutation;
    Tas() {
         nTas=0;
         tas= new ArrayList<Integer>();
        nb_permutation=0;
    }
    int minimum() {
         return tas.get(0);
             }
    int pere(int i) {
    return (i-1)/ 2;
    }
    int gauche(int i) {
    return 2 * i + 1;
    }
    int droite(int i) {
    return 2 * i + 2;
    }
    public void affiche(){
        int i;
        for(i = 0; i < nTas ; i++){
            System.out.println(tas.get(i));
        }  
    }
    public int nombre_permutation(){
        return nb_permutation;
    }
    boolean estUneFeuille(int i) {
    return (2 * i + 1 <= nTas);
    }
    boolean aUnFilsDroit(int i) {
    return (2 * i + 2 < nTas);
    }
    int  ajouter(int x) {
        
        int c=0;
        nb_permutation=0;
           
            
            int i = nTas;
            tas.add(x);
            while (i > 0 && tas.get(pere(i)) > x) {
                echanger(pere(i),i);
                i = pere(i);
                c++;
            }
        //tas[i] = x;
        nTas++;
       
       return c;
        }
    int size(){
        return nTas;
    }
    int extraire_min() {
        nb_permutation=0;
        int min=0;
       if (nTas < 1){
           System.out.println("le tas est vide");
           return -1;
              }
        min =tas.get(0);
        tas.set(0, tas.get(nTas-1));
        nTas--;
        min=Entasser(0);
        return min;
    }
    void echanger(int a,int b){
        int c;
        c=tas.get(a);
        tas.set(a,tas.get(b));
        tas.set(b,c);
        nb_permutation++;
    }
   int Entasser(int i){
       int p=0;
        int g = gauche(i) ;
        int d = droite(i) ;
        int min;
        min = i;
        if (tas.get(d)<tas.get(g)){
              if (d  < nTas && tas.get(d) < tas.get(i)) min = d;
        }
        else{
            if (g < nTas && tas.get(g) < tas.get(i)) 
                    min = g;
         }
        
        if  (min != i){
            echanger(i,min);
            Entasser(min);
            p++;
                }
        
      return p;
    }

}
 
