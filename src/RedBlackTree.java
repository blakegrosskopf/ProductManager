public class RedBlackTree {
    private TreeNode root;
    private boolean silentMode = false; // Default is "off", meaning messages are shown.

    public RedBlackTree() {
        root = null;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    public void insert(Product product) {
        TreeNode newNode = new TreeNode(product);
        if (root == null) {
            root = newNode;
            root.isRed = false; // Root is always black.
            if (!silentMode) {
                System.out.println("Product ID '" + product.getProductId() + "' inserted successfully.");
            }
            return;
        }
        if (insertRec(root, newNode)) {
            fixInsert(newNode); // Only fix the tree if a new node was successfully added.
            if (!silentMode) {
                System.out.println("Product ID '" + product.getProductId() + "' inserted successfully.");
            }
        }
    }

    private boolean insertRec(TreeNode current, TreeNode newNode) {
        if (newNode.product.getProductId().compareTo(current.product.getProductId()) < 0) {
            if (current.left == null) {
                current.left = newNode;
                newNode.parent = current;
                return true; // Successful insertion
            } else {
                return insertRec(current.left, newNode);
            }
        } else if (newNode.product.getProductId().compareTo(current.product.getProductId()) > 0) {
            if (current.right == null) {
                current.right = newNode;
                newNode.parent = current;
                return true; // Successful insertion
            } else {
                return insertRec(current.right, newNode);
            }
        } else {
            System.out.println("Error: Duplicate Product ID '" + newNode.product.getProductId() + "'. Product not added.");
            return false; // Duplicate found
        }
    }



    private void fixInsert(TreeNode node) {
        // Implementation of Red-Black Tree balancing logic after insertion.
    }

    public Product search(String productId) {
        TreeNode node = searchRec(root, productId);
        return node != null ? node.product : null;
    }

    private TreeNode searchRec(TreeNode current, String productId) {
        if (current == null || current.product.getProductId().equals(productId)) {
            return current; // Found the node or reached a null (not found)
        }
        if (productId.compareTo(current.product.getProductId()) < 0) {
            return searchRec(current.left, productId); // Search in the left subtree
        } else {
            return searchRec(current.right, productId); // Search in the right subtree
        }
    }
}