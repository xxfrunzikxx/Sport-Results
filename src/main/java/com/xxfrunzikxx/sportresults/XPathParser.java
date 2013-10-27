package com.xxfrunzikxx.sportresults;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Grigoriy Petrov (Frunzik)
 * Date: 2013, 10
 * Time: 22:41
 */
public class XPathParser {


    public static void main(String[] args) {
        try {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("data\\big-data.xml");

            ArrayList<Match> matchesList = XPathAnalizer.getList(document);

            for(Match m : matchesList){
                System.out.println(m);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.print(ex);

        }
    }

}
