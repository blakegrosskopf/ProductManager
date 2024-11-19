public class TreeNode {
    Product product;
    TreeNode left, right, parent;
    boolean isRed;

    public TreeNode(Product product) {
        this.product = product;
        this.isRed = true; // New nodes are red by default in a Red-Black Tree.
    }
}