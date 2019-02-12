import java.util.Scanner;

public class Prog1 {

  public static void main (String argv[]){
    System.out.println ("Enter a simplified arithmetic post-fix expression:");
    
    Scanner scanner = new Scanner(System.in);
    String expression = scanner.nextLine();

    String[] expArray = expression.split(" ");

    String id = expArray[expArray.length -1];
    TreeNode root;
    if (id.equals("+")) root = new TreeNode(TreeNode.ADD);
    else if (id.equals("*")) root = new TreeNode(TreeNode.MUL);
    else {
      System.err.println(id + " is not an operator.");
      return;
    }

    TreeNode newNode;

    for (int i = expArray.length -2; i >= 0; --i){
      id = (expArray[i]);
      if (id.equals("+")){
        newNode = new TreeNode(TreeNode.ADD);
        root.addNode(newNode);
      }
      else if (id.equals("*")){
        newNode = new TreeNode(TreeNode.MUL);
        root.addNode(newNode);
      } 
      else{
        newNode = new TreeNodeNum(TreeNode.NUM, Integer.parseInt(id));
        root.addNode(newNode);
      }
    }
    
    System.out.println("The tree representation: ");
    root.printNode();

    System.out.println("\nThe value: " + root.eval());

    TreeNode secondTree = root.clone();
    secondTree.alterIDs();
    System.out.println("\nThe new tree: ");
    secondTree.printNode();
    System.out.println("The value of the new tree: " + secondTree.eval());
    System.out.println("\nThe original tree: ");
    root.printNode();
    System.out.println("The value of the original tree: " + root.eval());
    return;
  }
}
