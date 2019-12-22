public class Tas {
    int[] tas;
    int size;
    int nTas;// nombre element du Tas
    int nb_permutation;
    int capacites;
    Tas(int n) {
         nTas=0;
        tas = new int[n];
        nb_permutation=0;
        capacites=n;
    }
    int minimum() {
         return tas[0];
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
            System.out.println(tas[i]);
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
   public  int capacite(){
       return capacites;
   } 
    int  ajouter(int x) {
        
        int c=0;
       try{
        nb_permutation=0;
           int n=capacite();

        if (n==nTas) throw new Debordement();
            
            int i = nTas;
            tas[nTas]=x;
            while (i > 0 && tas[pere(i)] > x) {
                echanger(pere(i),i);
                i = pere(i);
                c++;
            }
        //tas[i] = x;
        nTas++;
       }
       catch(Debordement e){
                System.out.println("Attention le tas binaire est dejà plein ");
       }
       
       return c;
        }
    int size(){
        return nTas;
    }
    int extraire_min() {
        nb_permutation=0;
        int min;
       if (nTas < 1){
           System.out.println("le tas est vide");
           return -1;
              }
        min =tas[0];
        tas[0]= tas[nTas-1];
        nTas--;
        int p=0;
        p=Entasser(0);
        return p;
    }
    void echanger(int a,int b){
        int c;
        c=tas[a];
        tas[a]=tas[b];
        tas[b]=c;
        nb_permutation++;
    }
   int Entasser(int i){
       int p=0;
        int g = gauche(i) ;
        int d = droite(i) ;
        int min;
        min = i;
        if (tas[d]<tas[g]){
              if (d  < nTas && tas[d] < tas[i]) min = d;
        }
        else{
            if (g < nTas && tas[g] < tas[i]) 
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
 
