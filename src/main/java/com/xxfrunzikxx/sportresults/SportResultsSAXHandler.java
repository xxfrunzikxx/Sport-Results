package com.xxfrunzikxx.sportresults;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Grigoriy Petrov (Frunzik)
 * Date: 2013, 10
 * Time: 15:28
 */
public class SportResultsSAXHandler extends DefaultHandler {
    private Stack<String> path = new Stack<>();
    private boolean suitable = false;
    private String elementTextValue = null;
    private String teamOne;
    private String teamTwo;
    private String status;
    private String winner;
    private String score;
    private String sport;
    private String tournament;
    private String category;

    private String teamOneGoals;
    private String teamTwoGoals;

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        path.push(qName);
        if (qName.equalsIgnoreCase("Name") && (atts.getValue("language").equals("en"))) {
            suitable = true;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//
        elementTextValue = new String(ch, start, length);
        if (suitable) {
            suitable = false;
            if (path.get(path.size() - 2).equals("Sport")) {
                sport = elementTextValue;
            } else if (path.get(path.size() - 2).equals("Category")) {
                category = elementTextValue;
            } else if (path.get(path.size() - 2).equals("Tournament")) {
                tournament = elementTextValue;
            } else if (path.get(path.size() - 2).equals("Team1")) {
                teamOne = elementTextValue;
            } else if (path.get(path.size() - 2).equals("Team2")) {
                teamTwo = elementTextValue;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (elementTextValue != null) {
            String element = qName;
            String parentElement = "";
            if (!qName.equals("LivescoreData"))
                parentElement = path.get(path.size() - 2);

            if (element.equals("Status")) {
                status = elementTextValue;
            } else if (element.equals("Winner")) {
                winner = elementTextValue;
            } else if (element.equals("Team1") && (parentElement.equals("Score"))) {
                teamOneGoals = elementTextValue;
            } else if (element.equals("Team2") && (parentElement.equals("Score"))) {
                teamTwoGoals = elementTextValue;
            } else if (element.equals("Match")) {
                Match match = new Match(sport, category, tournament, teamOne, teamTwo, teamOneGoals + "-" + teamTwoGoals);
                System.out.println(match);
            }


            //System.out.println("<" + path.peek() + "> " + elementTextValue);
            elementTextValue = null;
        }
        path.pop();


    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
