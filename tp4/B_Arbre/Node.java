public class Node {

    /**
     * number of nodes
     */
    public int numberOfNodes;              // number of nodes
    /**
     * the array that holds the keys value.
     */
    public int key[];                      // the array that holds the keys value.
    /**
     * the array that holds the references of the keys in the node.
     */
    public Node children[];                // the array that holds the references of the keys in the node.
    /**
     * the variable to deterimed if the node is is Leaf or not.
     */
    public boolean isLeaf;                   // the variable to deterime if the node is is Leaf or not.

    /**
     * The constructor of the node class
     * The node can have at most 3 keys. We have 4 references for each node, and assign the node to be isLeaf.
     */
    public Node() {
        key = new int[3];             // The node can have at most 3 keys
        children = new Node[4];       // We have 4 references for each node
        isLeaf = true;                 // assign the node to be Leaf.
    }

   /*public void splitChild(int i, Node y) 
{ 
    // Create a new node which is going to store (t-1) keys 
    // of y 
    BTreeNode z = new BTreeNode(y->t, y->leaf); 
    z.n = t - 1; 
  
    // Copy the last (t-1) keys of y to z 
    for (int j = 0; j < t-1; j++) 
        z.keys[j] = y.keys[j+t]; 
  
    // Copy the last t children of y to z 
    if (y->leaf == false) 
    { 
        for (int j = 0; j < t; j++) 
            z.C[j] = y.C[j+t]; 
    } 
  
    // Reduce the number of keys in y 
    y.n = t - 1; 
  
    // Since this node is going to have a new child, 
    // create space of new child 
    for (int j = n; j >= i+1; j--) 
        C[j+1] = C[j]; 
  
    // Link the new child to this node 
    C[i+1] = z; 
  
    // A key of y will move to this node. Find the location of 
    // new key and move all greater keys one space ahead 
    for (int j = n-1; j >= i; j--) 
        keys[j+1] = keys[j]; 
  
    // Copy the middle key of y to this node 
    keys[i] = y->keys[t-1]; 
  
    // Increment count of keys in this node 
    n = n + 1; 
} */
}
