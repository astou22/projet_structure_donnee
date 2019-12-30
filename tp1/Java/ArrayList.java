import java.util.Arrays;

/**
 Cette classe est un proxy pour les ArrayList, c'est à dire les tableaux dynamiques en Java.
 On utilise cette classe afin d'avoir le contrôle sur la gestion de la mémoire.
 */
public class ArrayList<T> {

    /**
        Constructeur de la classe des tableaux dynamiques.
        Complexité en temps/espace, pire et meilleur cas : O(1)
     */
    public ArrayList() {
        this.capacity = 4;
        this.data = new Object[this.capacity];
    }

    /**
     Ajoute une valeur dans le tableau.
     Complexité en temps/espace, pire cas : O(size)
     Complexité en temps/espace, meilleur cas : O(1)
     Complexité amortie : O(1)
     @param x est la valeur que l'on souhaite ajouter.
     @returns true si le tableau a été agrandit, false sinon
     */
    boolean append(T x){
        boolean memory_allocation = do_we_need_to_enlarge_capacity();
        if( memory_allocation ){
            enlarge_capacity();
        }
        data[size++] = x;
        return memory_allocation;
    }

    /**
     Supprime la dernière valeur du tableau.
     Complexité en temps, pire cas : O(size)
     Complexité en temps, meilleur cas : O(1)
     Complexité amortie : O(1)
     @returns true si le tableau a été réduit, false sinon
     */
    boolean pop_back(){
        boolean memory_reduction = do_we_need_to_reduce_capacity();
        if(size != 0){
            if( memory_reduction )
		        reduce_capacity();
            size--;
        }
        return memory_reduction;
    }

    /**
     Renvoie la valeur située à la position donnée par l'utilisateur.
     Complexité en temps/espace, pire cas : O(1)
     @param pos est la l'indice de la case on l'utilisateur veut connaître la valeur.
     @returns la valeur située à la position donnée par l'utilisateur.
     */
    @SuppressWarnings("unchecked")
    T get(int pos){
        return (T)data[pos];
    }

    /**
     Renvoie le nombre d'éléments stockés dans le tableau.
     Complexité en temps/espace, pire cas : O(1)
     @returns le nombre d'éléments stockés dans le tableau.
     */
    int size(){
        return size;
    }

    /**
     Renvoie la capacité de stockage du tableau.
     Complexité en temps/espace, pire cas : O(1)
     @returns le nombre d'éléments stockés dans le tableau.
    */
    int capacity(){
        return capacity;
    }
    
    /**
     Cette fonction détermine la règle selon laquelle un espace mémoire plus grand sera alloué ou non.
     @returns true si le tableau doit être agrandi, false sinon.
     */
    private boolean do_we_need_to_enlarge_capacity() {
        //return size >= (capacity * 3)/4;
	 return size==capacity;
    }

    /**
       Cette fonction augmente la capacité du tableau.
    */
    private void enlarge_capacity(){
        capacity *= 2;
        //capacity += Math.sqrt(capacity);
        data = java.util.Arrays.copyOf(data, capacity);

       
    }

    /**
     Cette fonction détermine la règle selon laquelle un espace mémoire plus petit sera alloué ou non.
     @returns true si le tableau doit être réduit, false sinon.
     */
    private boolean do_we_need_to_reduce_capacity(){
        return size <= capacity/4 && size >4;
    }
  
  /**
     Cette fonction reduit la capacité du tableau.
  */
  void reduce_capacity(){
      data = java.util.Arrays.copyOf(data, capacity/2);
      capacity /= 2;
  }
  
  // Tableau dynamique en Java. Sa capacité réelle est masquée, mais on peut avoir un contrôle dessus.
  private Object[] data;
  // Capacité réelle du tableau data.
  private int capacity;
  // Nombre réel d'éléments dans le tableau.
  private int size;
}
