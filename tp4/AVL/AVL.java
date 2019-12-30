/**
 * AVL tree implementation in Java
 */
public class AVL<Key extends Comparable<? super Key>, Value>
{
  /**
   * Internal node
   */
  class Node
  {
    /**
     * Data
     */
    private Value value;

    /**
     * Key
     */
    private Key key;

    /**
     * Left child
     */
    public Node left;

    /**
     * Right child
     */
    public Node right;

    /**
     * Parent node
     */
    public Node parent;

    /**
     * Height of the subtree
     */
    public int height;

    /**
     * Creates a new node
     */
    public Node(Key key, Value value) {
      this.key = key;
      this.value = value;
      this.height = 1;
    }

    /**
     * Right rotation
     */
    public Node rotateRight() {
      Node temp = this.left;

      this.left = temp.right;
      this.computeHeight();

      temp.right = this;
      temp.computeHeight();

      temp.parent = this.parent;
      return temp;
    }

    /**
     * Left rotation
     */
    public Node rotateLeft() {
      Node temp = this.right;

      this.right = temp.left;
      this.computeHeight();

      temp.left = this;
      temp.computeHeight();

      return temp;
    }

    /**
     * Recomputes the height of the tree
     */
    public void computeHeight() {
      height = 1;
      if (left != null) {
        height += left.height;
      }
      if (right != null) {
        height += right.height;
      }
    }

    /**
     * Returns the balancing factor
     */
    private int getBalance()
    {
      int balance = 0;
      if (left != null) {
        balance += left.height;
      }
      if (right != null) {
        balance -= right.height;
      }

      return balance;
    }

    /**
     * Balances a node
     */
    private Node balance() {
      computeHeight();
      int balance = getBalance();
      if (balance > 1) {
        if (left.getBalance() < 0) {
          left = left.rotateLeft();
        }
        return rotateRight();
      } else if (balance < -1) {
        if (right.getBalance() > 0) {
          right = right.rotateRight();
        }
        return rotateLeft();
      }

      return this;
    }
  }

  /**
   * Root of the tree
   */
  private Node root;
  public int capacite=12000;
  public int size;
  
  public int getCapacite(){
    return capacite;
  }
  public int getSize(){
    return size;
  }
  /**
   * Actual implementation of insertion
   */
  private Node insert(Node where, Node node) {
    if (where == null) {
      return node;
    }

    int comp = where.key.compareTo(node.key);
    if (comp == 0) {
      where.value = node.value;
    } else if (comp < 0) {
      where.left = insert(where.left, node);
    } else {
      where.right = insert(where.right, node);
    }

    return where.balance();
  }

  /**
   * Actual implementation of deletion
   */
  public Node delete(Node node, Key key) {
    if (node == null) {
      //throw new IllegalArgumentException();
      return null;
    }

    int comp = node.key.compareTo(key);
    if (comp == 0) {
      if (node.left == null && node.right == null) {
        return null;
      } else if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      } else {
        Node left = node.right;
        while (left.left != null) {
          left = left.left;
        }

        left.right = deleteLeft(node.right);
        left.left = node.left;

        return left.balance();
      }
    } else if (comp < 0) {
      node.left = delete(node.left, key);
    } else {
      node.right = delete(node.right, key);
    }

    return node.balance();
  }

  /**
   * Deletes the leftmost node
   */
  private Node deleteLeft(Node node) {
    if (node.left == null) {
      return node.right;
    } else {
      node.left = deleteLeft(node.left);
      return node.balance();
    }
  }

  /**
   * Inserts a new value into the tree
   */
  public void insert(Key key, Value value) {
    size++;
    root = insert(root, new Node(key, value));
  }

  /**
   * Removes a value from the tree
   */
  public void delete(Key key) {
    root = delete(root, key);
  }

  /**
   * Prints the tree
   */
  public void dump(Node node, int level)
  {
    if (node == null) {
      return;
    }

    dump(node.left, level + 1);
    for (int i = 0; i < level; ++i) {
      System.out.print('\t');
    }
    System.out.println(node.key + " " + node.height);
    dump(node.right, level + 1);
  }

  /**
   * Entry point of the application
   */
 /* public static void main(String[] args) {
    AVL<Integer, Integer> tree = new AVL<>();

    for (int i = 0; i < 10; ++i) {
      tree.insert(i, i);
      tree.dump(tree.root, 0);
      System.out.println("====================");
    }

    tree.delete(5);
    for (int i = 0; i < 10; ++i) {
      if (i != 5) {
        tree.delete(i);
        tree.dump(tree.root, 0);
        System.out.println("====================");
      }
    }
  }*/  
}