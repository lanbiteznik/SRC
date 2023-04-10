import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static final String BASE_URL = "https://dummyjson.com/";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Console Menu");
            System.out.println("1. View Users");
            System.out.println("2. View Carts");
            System.out.println("3. View Products");
            System.out.println("4. Search Users");
            System.out.println("5. Search Carts");
            System.out.println("6. Search Products");
            System.out.println("7. Save Images Locally");
            System.out.println("8. Quit");

            System.out.print("\nEnter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    viewCarts();
                    break;
                case 3:
                    viewProducts();
                    break;
                case 4:
                    searchUsers();
                    break;
                case 5:
                    searchCarts();
                    break;
                case 6:
                    searchProducts();
                    break;
                case 7:
                    saveImagesLocally();
                    break;
                case 8:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    public static void viewUsers() {
        try {
            URL url = new URL(BASE_URL + "users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner responseScanner = new Scanner(conn.getInputStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (responseScanner.hasNextLine()) {
                responseBuilder.append(responseScanner.nextLine());
            }
            responseScanner.close();

            conn.disconnect();

            String jsonResponse = responseBuilder.toString();
            int startIndex = jsonResponse.indexOf("[");
            int endIndex = jsonResponse.lastIndexOf("]") + 1;
            jsonResponse = jsonResponse.substring(startIndex, endIndex);

            System.out.println(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewCarts() {
        try {
            URL url = new URL(BASE_URL + "carts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewProducts() {
        try {
            URL url = new URL(BASE_URL + "products");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search term: ");
        String term = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL + "users?search=" + term);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner responseScanner = new Scanner(conn.getInputStream());
            while (responseScanner.hasNextLine()) {
                System.out.println(responseScanner.nextLine());
            }
            responseScanner.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchCarts() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search term: ");
        String term = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL + "carts?search=" + term);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner responseScanner = new Scanner(conn.getInputStream());
            while (responseScanner.hasNextLine()) {
                System.out.println(responseScanner.nextLine());
            }
            responseScanner.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchProducts() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search term: ");
        String term = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL + "products?search=" + term);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            Scanner responseScanner = new Scanner(conn.getInputStream());
            while (responseScanner.hasNextLine()) {
                System.out.println(responseScanner.nextLine());
            }
            responseScanner.close();

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImagesLocally() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter image URL: ");
        String imageUrl = scanner.nextLine();

        try {
            URL url = new URL(imageUrl);
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
            out.close();

            System.out.println("Image saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
