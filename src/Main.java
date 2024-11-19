public class Main {
    public static void main(String[] args) {
        ProductManager m = new ProductManager();
        m.loadProducts("dir/amazon-product-data.csv");

        System.out.println("Search Results:");
        // Perform three different searches
        m.searchProduct("P1");
        m.searchProduct("P500");
        m.searchProduct("P2500");

        System.out.println("\nInsertions:");
        // Perform one valid insertion
        Product newProduct = new Product("P10005", "New Product", "Electronics", 49.99);
        m.insertProduct(newProduct);

        // Perform one edge case insertion: duplicate product ID
        Product duplicateProduct = new Product("P10005", "Duplicate Product", "Test", 99.99);
        m.insertProduct(duplicateProduct); // This will trigger the duplicate error message
    }
}
