import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProductManager {
    private RedBlackTree tree = new RedBlackTree();

    public void loadProducts(String filePath) {
        tree.setSilentMode(true); // Suppress messages during loading
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header
            int idCounter = 1; // Start counter at 1
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (parts.length > 1) {
                    parts[1] = parts[1].replace("\"", "");
                }

                double price = 0.00;
                if (parts.length > 3) {
                    String priceString = parts[3].trim();
                    if (!priceString.isEmpty() && !priceString.equalsIgnoreCase("Total Price:")) {
                        try {
                            String cleanedPrice = priceString.replace("$", "").replace(",", "").trim();
                            price = Double.parseDouble(cleanedPrice);
                        } catch (NumberFormatException e) {
                            price = 0.00;
                        }
                    }
                }

                String id = parts.length > 0 ? parts[0] : "Unknown ID";
                String uniqueId = "P" + idCounter++;
                String description = parts.length > 1 ? parts[1] : "No Description";
                String category = parts.length > 2 ? parts[2] : "No Category";

                Product product = new Product(uniqueId, description, category, price);
                tree.insert(product); // Insert without showing success messages
            }
        } catch (IOException e) {
            System.out.println("Error while reading products from " + e.getMessage());
            e.printStackTrace();
        } finally {
            tree.setSilentMode(false); // Re-enable messages after loading
        }
    }

    public void insertProduct(Product product) {
        tree.insert(product); // Use normal insert
    }

    public void searchProduct(String productId) {
        Product result = tree.search(productId);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Product not found for ID: " + productId);
        }
    }
}
