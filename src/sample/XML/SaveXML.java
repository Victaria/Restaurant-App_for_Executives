package sample.XML;

import javax.xml.parsers.*;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.ParserConfigurationException;

import sample.Entities.Products;
import sample.Entities.Staff;

public class SaveXML {

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";
    private String filePath = "D:\\Disk_D\\VTPart2\\src\\sample\\XML\\";

    ValidationXML validationXML = new ValidationXML();

    public void writeProductsXMLFile(ObservableList<Products> productsList) {
        try {
            validationXML.validate(path + "ProductsXML.xml", filePath + "Product.xsd");

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

    public void writeStaffXMLFile(ObservableList<Staff> staffList) {
        try {
            validationXML.validate(path + "StaffXML.xml", filePath + "Staff.xsd");

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




