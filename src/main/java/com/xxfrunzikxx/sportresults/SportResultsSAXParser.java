package com.xxfrunzikxx.sportresults;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Grigoriy Petrov (Frunzik)
 * Date: 2013, 10
 * Time: 16:43
 */
public class SportResultsSAXParser {

    public static void main(String[] args) {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            for (int i = 0; i < 50; i++) {

                saxParser.parse(new File("data\\big-big-data.xml"), new SportResultsSAXHandler());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
