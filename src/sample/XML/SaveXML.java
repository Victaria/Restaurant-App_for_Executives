package sample.XML;

import javax.xml.parsers.*;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.ParserConfigurationException;

import sample.Entities.*;

public class SaveXML {

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    public void writeProductsXMLFile(ObservableList<Products> productsList) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
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
                    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    // send DOM to file
                    tr.transform(new DOMSource(document),
                            new StreamResult(path + "ProductsXML.xml"));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                }
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDishesXMLFile(ObservableList<Dishes> dishesList) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element rootElement = document.createElement("dishes");
                document.appendChild(rootElement);

                for (Dishes dish : dishesList) {
                    e = document.createElement("dish");

                    Element dishId = document.createElement("id");
                    dishId.appendChild(document.createTextNode(String.valueOf(dish.getId())));
                    e.appendChild(dishId);

                    Element dishName = document.createElement("name");
                    dishName.appendChild(document.createTextNode(dish.getName()));
                    e.appendChild(dishName);

                    Element dishPrice = document.createElement("price");
                    dishPrice.appendChild(document.createTextNode(String.valueOf(dish.getPrice())));
                    e.appendChild(dishPrice);

                    Element dishWeight = document.createElement("weight");
                    dishWeight.appendChild(document.createTextNode(String.valueOf(dish.getWeight())));
                    e.appendChild(dishWeight);

                    Element dishSum = document.createElement("sum");
                    dishSum.appendChild(document.createTextNode(String.valueOf(dish.getSum())));
                    e.appendChild(dishSum);

                    rootElement.appendChild(e);
                }
                try {
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    tr.setOutputProperty(OutputKeys.INDENT, "yes");
                    tr.setOutputProperty(OutputKeys.METHOD, "xml");
                    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    // send DOM to file
                    tr.transform(new DOMSource(document),
                            new StreamResult(path + "DishesXML.xml"));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                }
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOrderDishesXMLFile(ObservableList<OrderDish> orderDishesList) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element rootElement = document.createElement("orderDishes");
                document.appendChild(rootElement);

                for (OrderDish orderDish : orderDishesList) {
                    e = document.createElement("orderDish");

                    Element orderDishId = document.createElement("id");
                    orderDishId.appendChild(document.createTextNode(String.valueOf(orderDish.getId())));
                    e.appendChild(orderDishId);

                    Element orderDishAmount = document.createElement("amount");
                    orderDishAmount.appendChild(document.createTextNode(String.valueOf(orderDish.getAmount())));
                    e.appendChild(orderDishAmount);

                    Element dishName = document.createElement("dishName");
                    dishName.appendChild(document.createTextNode(String.valueOf(orderDish.getDishName())));
                    e.appendChild(dishName);

                    Element orderId = document.createElement("orderId");
                    orderId.appendChild(document.createTextNode(String.valueOf(orderDish.getOrderId())));
                    e.appendChild(orderId);

                    rootElement.appendChild(e);
                }
                try {
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    tr.setOutputProperty(OutputKeys.INDENT, "yes");
                    tr.setOutputProperty(OutputKeys.METHOD, "xml");
                    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    // send DOM to file
                    tr.transform(new DOMSource(document),
                            new StreamResult(path + "OrderDishXML.xml"));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                }
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOrderXMLFile(ObservableList<Order> ordersList) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element rootElement = document.createElement("orders");
                document.appendChild(rootElement);

                for (Order order : ordersList) {
                    e = document.createElement("order");

                    Element orderId = document.createElement("id");
                    orderId.appendChild(document.createTextNode(String.valueOf(order.getId())));
                    e.appendChild(orderId);

                    Element orderTable = document.createElement("table");
                    orderTable.appendChild(document.createTextNode(String.valueOf(order.getTable())));
                    e.appendChild(orderTable);

                    Element orderSum = document.createElement("sum");
                    orderSum.appendChild(document.createTextNode(String.valueOf(order.getSum())));
                    e.appendChild(orderSum);

                    Element orderDate = document.createElement("date");
                    orderDate.appendChild(document.createTextNode(String.valueOf(order.getDate())));
                    e.appendChild(orderDate);

                    Element orderStaff = document.createElement("staffName");
                    orderStaff.appendChild(document.createTextNode(String.valueOf(order.getStaffName())));
                    e.appendChild(orderStaff);

                    rootElement.appendChild(e);
                }
                try {
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    tr.setOutputProperty(OutputKeys.INDENT, "yes");
                    tr.setOutputProperty(OutputKeys.METHOD, "xml");
                    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    // send DOM to file
                    tr.transform(new DOMSource(document),
                            new StreamResult(path + "OrdersXML.xml"));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                }
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeRecipeXMLFile(ObservableList<Recipe> recipes) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element rootElement = document.createElement("recipes");
                document.appendChild(rootElement);

                for (Recipe recipe : recipes) {
                    e = document.createElement("recipe");

                    Element recipeId = document.createElement("id");
                    recipeId.appendChild(document.createTextNode(String.valueOf(recipe.getId())));
                    e.appendChild(recipeId);

                    Element dishName = document.createElement("dishName");
                    dishName.appendChild(document.createTextNode(String.valueOf(recipe.getDishName())));
                    e.appendChild(dishName);

                    Element productName = document.createElement("productName");
                    productName.appendChild(document.createTextNode(String.valueOf(recipe.getProductName())));
                    e.appendChild(productName);

                    Element amount = document.createElement("amount");
                    amount.appendChild(document.createTextNode(String.valueOf(recipe.getAmount())));
                    e.appendChild(amount);

                    rootElement.appendChild(e);
                }
                try {
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    tr.setOutputProperty(OutputKeys.INDENT, "yes");
                    tr.setOutputProperty(OutputKeys.METHOD, "xml");
                    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    // send DOM to file
                    tr.transform(new DOMSource(document),
                            new StreamResult(path + "RecipeXML.xml"));

                } catch (TransformerException te) {
                    System.out.println(te.getMessage());
                }
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeStaffXMLFile(ObservableList<Staff> staffList) {
        try {
            Element e;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("staff");
            document.appendChild(rootElement);

                for (Staff staff : staffList) {
                    e = document.createElement("person");

                    Element stId = document.createElement("id");
                    stId.appendChild(document.createTextNode(String.valueOf(staff.getId())));
                    e.appendChild(stId);

                    Element stName = document.createElement("name");
                    stName.appendChild(document.createTextNode(staff.getName()));
                    e.appendChild(stName);

                    rootElement.appendChild(e);
                }
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(document),
                        new StreamResult(path + "StaffXML.xml"));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }




