package sample.XML;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.xml.sax.XMLReader;

//SAX and external XSD
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;

public class ParseXML {

    private ParseXML() {}

    public static boolean validateWithExtXSDUsingSAX(String xml, String xsd)
            throws ParserConfigurationException, IOException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);

            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            SAXParser parser = null;
            try {
                factory.setSchema(schemaFactory.newSchema(new Source[]{new StreamSource(xsd)}));
                parser = factory.newSAXParser();
            } catch (SAXException se) {
                System.out.println("SCHEMA : " + se.getMessage());  // problem in the XSD itself
                return false;
            }

            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(
                    new ErrorHandler() {
                        public void warning(SAXParseException e) throws SAXException {
                            System.out.println("WARNING: " + e.getMessage()); // do nothing
                        }

                        public void error(SAXParseException e) throws SAXException {
                            System.out.println("ERROR : " + e.getMessage());
                            throw e;
                        }

                        public void fatalError(SAXParseException e) throws SAXException {
                            System.out.println("FATAL : " + e.getMessage());
                            throw e;
                        }
                    }
            );
            reader.parse(new InputSource(xml));
            return true;
        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (IOException io) {
            throw io;
        } catch (SAXException se) {
            return false;
        }
    }

    public static void main (String args[]) throws Exception{
        System.out.println
                (ParseXML.validateWithExtXSDUsingSAX
                        ("D:\\Disk_D\\VTPart2\\DataBase\\ProductsXML.xml", "D:\\Disk_D\\VTPart2\\src\\sample\\XML\\Product.xsd"));
    /*
      output :
               true
    */
    }
    }

