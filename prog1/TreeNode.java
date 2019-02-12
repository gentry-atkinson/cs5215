/* This is a simple TreeNode that implements the Node interface. */

public class TreeNode implements Node, Cloneable {
  public static final int NUM = 0;
  public static final int ADD = 1;
  public static final int MUL = 2;

  protected Node[] children;
  protected int id;

  public TreeNode(int i) {
    id = i;
  }

  public void addChild(Node n, int i) {
    if (i >= 2){
      System.err.println("Target Node can only have 2 children.");
      return;
    } else if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public boolean addNode(Node n){
    if (children == null){
      children = new Node[1];
      children[0] = n;
      return true;
    }

    TreeNode left = (TreeNode)children[0];
    if (left != null && left.addNode(n)) return true;
    else if (children.length == 1) {
      Node c[] = new Node[2];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
      children[1] = n;
      return true;
    }
    TreeNode right = (TreeNode)children[1];
    if (right != null && right.addNode(n)){
      return true;
    }
    return false;
  }

  public int eval(){
    try {
      if (children.length< 2){
        throw new TreeNotFull("This tree is not full.");
      }
      else {
        TreeNode left = (TreeNode)children[0];
        TreeNode right = (TreeNode)children[1];
        if (id == ADD){
          return (left.eval() + right.eval());
        }
        else if (id == MUL){
          return (left.eval() * right.eval());
        }
      }
    }
    catch (TreeNotFull e){
      System.err.println("This tree is not full");
    }
    return 0;
  }  

  public Node getChild(int i) {
    return children[i];
  }

  public int getNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  /* You can override these two methods in subclasses of TreeNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return "Node: " + id; }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
	TreeNode n = (TreeNode)children[i];
	if (n != null) {
	  n.dump(prefix + " ");
	}
      }
    }
  }

  public TreeNode clone(){
    TreeNode newNode = new TreeNode(id);
    
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
	TreeNode n = (TreeNode)children[i];
	if (n != null) {
	  TreeNode newChild = n.clone();
          newNode.addChild(newChild, i);
        }
      }
    }
    return newNode;
  }

  public void printNode (int tier){
    for (int i = 0; i < tier; ++i) System.out.print(" ");
    if (id == ADD) System.out.println("+");
    else if (id == MUL) System.out.println("*");
    else {
      System.err.println("Node has bad ID on tier " + tier);
      return;
    }
    if (children == null || children.length < 2){
      System.err.println("Tree is not complete on tier " + tier);
      return;
    }

    TreeNode left = (TreeNode)children[0];
    TreeNode right = (TreeNode)children[1];

    right.printNode(tier + 1);
    left.printNode(tier + 1);
    return;
  }
  public void alterIDs (){
    if (id == ADD) id = MUL;
    else if (id == MUL) id = ADD;
    else System.err.println("Node has bad ID value to alter.");

    TreeNode left = (TreeNode)children[0];
    TreeNode right = (TreeNode)children[1];
 
    left.alterIDs();
    right.alterIDs();

    return;
  } 

  public void printNode (){
    printNode(0);
  }
  
  private class TreeNotFull extends Exception{
    public TreeNotFull (String m){
      super(m);
    }
  }
}
