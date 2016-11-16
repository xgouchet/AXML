package fr.xgouchet.axml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CompressedXmlDomListener implements CompressedXmlParserListener {

    /**
     * @throws ParserConfigurationException if a DocumentBuilder can't be created
     */
    public CompressedXmlDomListener() throws ParserConfigurationException {
        mBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        mStack = new Stack<>();
    }

    public void startDocument() {
        mDocument = mBuilder.newDocument();
        mStack.push(mDocument);
    }

    public void endDocument() {
    }

    public void startPrefixMapping(String prefix, String uri) {
    }

    public void endPrefixMapping(String prefix, String uri) {
    }

    public void startElement(final String uri, final String localName,
                             final String qName, final Attribute[] attrs) {
        Element elt;

        // create elt
        if (isEmpty(uri)) {
            elt = mDocument.createElement(localName);
        } else {
            elt = mDocument.createElementNS(uri, qName);
        }

        // add attrs
        for (Attribute attr : attrs) {
            if (isEmpty(attr.getNamespace())) {
                elt.setAttribute(attr.getName(), attr.getValue());
            } else {
                elt.setAttributeNS(attr.getNamespace(), attr.getPrefix() + ':'
                        + attr.getName(), attr.getValue());
            }
        }

        // handle stack
        mStack.peek().appendChild(elt);
        mStack.push(elt);
    }

    public void endElement(String uri, String localName, String qName) {
        mStack.pop();
    }

    public void characterData(String data) {
        mStack.peek().appendChild(mDocument.createCDATASection(data));
    }

    public void text(String data) {
        mStack.peek().appendChild(mDocument.createTextNode(data));
    }

    public void processingInstruction(String target, String data) {
    }

    /**
     * @return the parsed document
     */
    public Document getDocument() {
        return mDocument;
    }

    private static boolean isEmpty(String text) {
        return (text == null) || "".equals(text);
    }

    private Stack<Node> mStack;
    private Document mDocument;
    private final DocumentBuilder mBuilder;
}
