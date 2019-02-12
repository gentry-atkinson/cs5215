public class TreeNodeNum extends TreeNode {
  private int value;

  public TreeNodeNum (int i, int v){
    super(i);
    value = v;
  }

  public TreeNodeNum clone(){
    TreeNodeNum newNode = new TreeNodeNum(id, value);
    return newNode;
  }

  public int eval(){
    return value;
  }

  public boolean addNode(Node n){
    return false;
  }

  public void printNode (int tier){
    for (int i = 0; i < tier; ++i) System.out.print(" ");
    System.out.println(value);
    return;
  }

  public void alterIDs(){
    value *= 2;
    return;
  }
}
