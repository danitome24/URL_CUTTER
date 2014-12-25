/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAXparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class SaxHandler extends DefaultHandler {
     private StringBuilder output = new StringBuilder();

     /**
     * Report a startElement event.
     *
     * @param   uri namespace
     * @param   localName   name of element, sans namespace
     * @param   qName       name of element, with namespace
     * @param   attributes  element's collection of attributes
     *
     * @throws  SAXException    general SAX error or warning
     */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes atts)
     throws SAXException
    {
        output.append("startElement(\"").append(qName).append("\", {");
        for (int i = 0; i < atts.getLength(); i++)
        {
            output.append("(\"").append(atts.getQName(i))
                    .append("\", \"")
                    .append(atts.getValue(i))
                    .append("\")");
            
            if (i != atts.getLength() - 1)
                output.append(", ");
        }
        output.append("});\n");
    }

    /**
     * Report a characters event.
     *
     * @param   ch      characters
     * @param   start   start position in the character array
     * @param   length  number of characters to use from the character array
     *
     * @throws  SAXException    general SAX error or warning
     */
    @Override
    public void characters(char[] ch, int start, int length)
     throws SAXException
    {
        String content = new String(ch, start, length).trim();
        if ( content.length() > 0 )
            output.append("characters(\"")
                    .append( content )
                    .append("\");\n");
    }


    /**
     * Report an endElement event.
     *
     * @param   uri         namespace
     * @param   localName   name of element, sans namespace
     * @param   qName       name of element, with namespace
     *
     * @throws  SAXException    general SAX error or warning
     */
    @Override
    public void endElement(String uri, String localName, String qName)
     throws SAXException
    {
        output.append("endElement(\"")
            .append(qName)
            .append("\");\n");
    }


    /**
     * Report a startDocument event.
     */
    @Override
    public void startDocument() throws SAXException
    {
        output.append("\nstartDocument();\n");
    }


    /**
     * Report an endDocument event.
     *
     * @throws  SAXException    general SAX error or warning
     */
    @Override
    public void endDocument() throws SAXException
    {
        output.append("endDocument();\n");
    }
    
    public String getOutput() {
        return output.toString();
    }
}

