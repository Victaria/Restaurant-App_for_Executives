package sample.SCRUD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sample.Entities.*;
import sample.XML.ValidationXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;

public class EntitiesLoader {
    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";
    private String filePath = "D:\\Disk_D\\VTPart2\\src\\sample\\XML\\";

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDishList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();
    private EntityEditor editor = new EntityEditor();

    ValidationXML validationXML = new ValidationXML();

    public  ObservableList<Products> loadProductXMLFile() {
        try {
            validationXML.validate(path + "ProductsXML.xml", filePath + "Product.xsd");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "ProductsXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList productNodes = doc.getElementsByTagName("product");

                for (int i = 0; i < productNodes.getLength(); i++) {
                    Node productNode = productNodes.item(i);

                    if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element productElement = (Element) productNode;

                        String id = productElement.getElementsByTagName("id").item(0).getTextContent();
                        String name = productElement.getElementsByTagName("name").item(0).getTextContent();
                        String price = productElement.getElementsByTagName("price").item(0).getTextContent();
                        String amount = productElement.getElementsByTagName("amount").item(0).getTextContent();

                        Products product = new Products(Integer.valueOf(id), name, Double.valueOf(price), Integer.valueOf(amount));

                        productsList.add(product);
                    }

                }
                return productsList;
                } catch (ParserConfigurationException | FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } catch (SAXException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public  ObservableList<Dishes> loadDishesXMLFile() {
        try {
            validationXML.validate(path + "DishesXML.xml", filePath + "Dish.xsd");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "DishesXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList dishNodes = doc.getElementsByTagName("dish");

                for (int i = 0; i < dishNodes.getLength(); i++) {
                    Node dishNode = dishNodes.item(i);

                    if (dishNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element dishElement = (Element) dishNode;

                        String id = dishElement.getElementsByTagName("id").item(0).getTextContent();
                        String name = dishElement.getElementsByTagName("name").item(0).getTextContent();
                        String price = dishElement.getElementsByTagName("price").item(0).getTextContent();
                        String weight = dishElement.getElementsByTagName("weight").item(0).getTextContent();
                        String sum = dishElement.getElementsByTagName("sum").item(0).getTextContent();

                        Dishes dish = new Dishes(Integer.valueOf(id), name, Double.valueOf(price), Double.valueOf(weight), Double.valueOf(sum));

                        dishesList.add(dish);
                    }

                }
                return dishesList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<OrderDish> loadOrderDishesXMLFile() {
        try {
            validationXML.validate(path + "OrderDishXML.xml", filePath + "OrderDish.xsd");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "OrderDishXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList orderDishNodes = doc.getElementsByTagName("orderDish");

                for (int i = 0; i < orderDishNodes.getLength(); i++) {
                    Node orderDishNode = orderDishNodes.item(i);

                    if (orderDishNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element orderDishElement = (Element) orderDishNode;

                        String id = orderDishElement.getElementsByTagName("id").item(0).getTextContent();
                        String amount = orderDishElement.getElementsByTagName("amount").item(0).getTextContent();
                        String dishName = orderDishElement.getElementsByTagName("dishName").item(0).getTextContent();
                        String orderId = orderDishElement.getElementsByTagName("orderId").item(0).getTextContent();

                        OrderDish orderDish = new OrderDish(Integer.valueOf(id), Integer.valueOf(amount), dishName, Integer.valueOf(orderId));

                        orderDishList.add(orderDish);
                    }

                }
                return orderDishList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Order> loadOrderXMLFile() {
        try {
            validationXML.validate(path + "OrdersXML.xml", filePath + "Order.xsd");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "OrdersXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList orderNodes = doc.getElementsByTagName("order");

                for (int i = 0; i < orderNodes.getLength(); i++) {
                    Node orderNode = orderNodes.item(i);

                    if (orderNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element orderElement = (Element) orderNode;

                        String id = orderElement.getElementsByTagName("id").item(0).getTextContent();
                        String table = orderElement.getElementsByTagName("table").item(0).getTextContent();
                        String sum = orderElement.getElementsByTagName("sum").item(0).getTextContent();
                        String date = orderElement.getElementsByTagName("date").item(0).getTextContent();
                        String staffName = orderElement.getElementsByTagName("staffName").item(0).getTextContent();

                        Order order = new Order(Integer.valueOf(id), Integer.valueOf(table), Double.valueOf(sum), LocalDate.parse(date), staffName);

                        orderList.add(order);
                    }

                }
                return orderList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Recipe> loadRecipeXMLFile() {
        try {
            validationXML.validate(path + "RecipeXML.xml", filePath + "Recipe.xsd");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "RecipeXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList recipeNodes = doc.getElementsByTagName("recipe");

                for (int i = 0; i < recipeNodes.getLength(); i++) {
                    Node recipeNode = recipeNodes.item(i);

                    if (recipeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element recipeElement = (Element) recipeNode;

                        String id = recipeElement.getElementsByTagName("id").item(0).getTextContent();
                        String dishName = recipeElement.getElementsByTagName("dishName").item(0).getTextContent();
                        String productName = recipeElement.getElementsByTagName("productName").item(0).getTextContent();
                        String amount = recipeElement.getElementsByTagName("amount").item(0).getTextContent();

                        Recipe recipe = new Recipe(Integer.valueOf(id), dishName, productName, Integer.valueOf(amount));

                        recipeList.add(recipe);
                    }

                }
                return recipeList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Staff> loadStaffXMLFile() {
        try {
          validationXML.validate(path + "StaffXML.xml", filePath + "Staff.xsd");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);

            DocumentBuilder builder = null;
            try {
                builder = factory.newDocumentBuilder();

                FileInputStream fis = new FileInputStream(path + "StaffXML.xml");
                InputSource is = new InputSource(fis);

                Document doc = builder.parse(is);
                NodeList staffNodes = doc.getElementsByTagName("person");

                for (int i = 0; i < staffNodes.getLength(); i++) {
                    Node staffNode = staffNodes.item(i);

                    if (staffNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element staffElement = (Element) staffNode;

                        String id = staffElement.getElementsByTagName("id").item(0).getTextContent();
                        String name = staffElement.getElementsByTagName("name").item(0).getTextContent();

                        Staff staff = new Staff(Integer.valueOf(id), name);

                        staffList.add(staff);
                    }

                }
                return staffList;
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (SAXException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


