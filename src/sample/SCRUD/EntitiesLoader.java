package sample.SCRUD;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ObjectFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sample.Entities.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;

public class EntitiesLoader {
    private Products product;
    private Dishes dish;
    private OrderDish orderDish;
    private Order order;
    private Recipe recipe;
    private Staff staff;

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";
    private String filePath = "D:\\Disk_D\\VTPart2\\src\\sample\\XML\\";

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDishList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();
    private EntityEditor editor = new EntityEditor();

    public ObservableList<Products> loadProductFile() {
        try {
            File file = new File(path + "Products.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                product = new Products(Integer.parseInt(cols[0]), cols[1], Double.parseDouble(cols[2]), Integer.parseInt(cols[3]));
                productsList.add(product);
                line = reader.readLine();
            }
        } catch (
                FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }
        return productsList;
    }

    public ObservableList<Dishes> loadDishesFile() {
        try {
            File file = new File(path + "Dishes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                dish = new Dishes(Integer.parseInt(cols[0]), cols[1], Double.parseDouble(cols[2]), Double.parseDouble(cols[3]), Double.parseDouble(cols[4]));
                dishesList.add(dish);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }
        return dishesList;
    }


    public ObservableList<OrderDish> loadOrderDishFile() {
        try {
            File file = new File(path + "OrderDish.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                orderDish = new OrderDish(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), cols[2], Integer.parseInt(cols[3]));
                orderDishList.add(orderDish);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }

        return orderDishList;
    }

    public ObservableList<Order> loadOrderFile() {
        try {
            File file = new File(path + "Orders.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                order = new Order(Integer.parseInt(cols[0]), Integer.parseInt(cols[1]), Double.parseDouble(cols[2]), LocalDate.parse(cols[3]), cols[4]);
                orderList.add(order);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }

        return orderList;
    }


    public ObservableList<Recipe> loadRecipeFile() {
        try {
            File file = new File(path + "Recipe.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                recipe = new Recipe(Integer.parseInt(cols[0]), cols[1], cols[2], Integer.parseInt(cols[3]));
                recipeList.add(recipe);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }
        return recipeList;
    }


    public ObservableList<Staff> loadStaffFile() {
        try {
            File file = new File(path + "Staff.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                staff = new Staff(Integer.parseInt(cols[0]), cols[1]);
                staffList.add(staff);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            editor.printAlert("File not found.");
        } catch (IOException e) {
            editor.printAlert("It's smt. wrong with File.");
        }
        return staffList;
    }

    public void writeProductsFile(ObservableList<Products> product) {
        try (FileWriter writer = new FileWriter(path + "Products.txt", false)) {
            for (Products products : product) {
                writer.write(products.getId() + ";" + products.getName() + ";" + products.getPrice() + ";" + products.getAmount() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeDishesFile(ObservableList<Dishes> dish) {
        try (FileWriter writer = new FileWriter(path + "Dishes.txt", false)) {
            for (Dishes dishes : dish) {
                writer.write(dishes.getId() + ";" + dishes.getName() + ";" + dishes.getPrice() + ";" + dishes.getWeight() + ";" + dishes.getSum() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeOrderFile(ObservableList<Order> order) {
        try (FileWriter writer = new FileWriter(path + "Orders.txt", false)) {
            for (Order orders : order) {
                writer.write(orders.getId() + ";" + orders.getTable() + ";" + orders.getSum() + ";" + orders.getDate() + ";" + orders.getStaffName() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeOrderDishFile(ObservableList<OrderDish> orderDishes) {
        try (FileWriter writer = new FileWriter(path + "OrderDish.txt", false)) {
            for (OrderDish orderDish : orderDishes) {
                writer.write(orderDish.getId() + ";" + orderDish.getAmount() + ";" + orderDish.getDishName() + ";" + orderDish.getOrderId() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeRecipeFile(ObservableList<Recipe> recipes) {
        try (FileWriter writer = new FileWriter(path + "Recipe.txt", false)) {
            for (Recipe recipe : recipes) {
                writer.write(recipe.getId() + ";" + recipe.getDishName() + ";" + recipe.getProductName() + ";" + recipe.getAmount() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeStaffFile(ObservableList<Staff> staffs) {
        try (FileWriter writer = new FileWriter(path + "Staff.txt", false)) {
            for (Staff staff : staffs) {
                writer.write(staff.getId() + ";" + staff.getName() + "\n");
            }
        } catch (IOException e) {
            editor.printAlert("File writing is not possible.");
        }
    }

    public void writeProductsXMLFile(ObservableList<Products> productsList) throws ParserConfigurationException {
        Element e;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element rootElement = document.createElement("products");
        document.appendChild(rootElement);

        for (Products product : productsList) {
            e = document.createElement("product");

            Element prId = document.createElement("id");
            prId.appendChild(document.createTextNode(String.valueOf(product.getId())));
            e.appendChild(prId);

            Element prName = document.createElement("name");
            prName.appendChild(document.createTextNode(product.getName()));
            e.appendChild(prName);

            Element prPrice = document.createElement("price");
            prPrice.appendChild(document.createTextNode(String.valueOf(product.getPrice())));
            e.appendChild(prPrice);

            Element prAmount = document.createElement("amount");
            prAmount.appendChild(document.createTextNode(String.valueOf(product.getAmount())));
            e.appendChild(prAmount);

            rootElement.appendChild(e);
        }
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Product.xsd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(document),
                    new StreamResult(path+"ProductsXML.xml"));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        }

    }


}


