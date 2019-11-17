package sample.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sample.Entities.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductHandler extends DefaultHandler {

    private List<Products> productsList = new ArrayList<>();
    private Products product;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("product".equals(qName)){
            product = new Products(null);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("product".equals(qName)){
            productsList.add(product);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        product.setId(Integer.valueOf(new String(ch, start, length)));
        product.setName(new String(ch, start, length));
        product.setPrice(Integer.valueOf(new String(ch, start, length)));
        product.setAmount(Integer.valueOf(new String(ch, start, length)));
    }

    public List<Products> getProductsList() {
        return productsList;
    }
}
