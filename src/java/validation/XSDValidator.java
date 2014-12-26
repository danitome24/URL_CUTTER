/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class XSDValidator {

    private final String xmlPath;
    private final String xsdPath;

    public XSDValidator(String xsdPath, String xmlPath) {
        this.xmlPath = xmlPath;
        this.xsdPath = xsdPath;
    }

    public boolean validateXML() throws ParserConfigurationException, SAXException, IOException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }

}
