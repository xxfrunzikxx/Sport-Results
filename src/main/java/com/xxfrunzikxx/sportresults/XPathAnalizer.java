package com.xxfrunzikxx.sportresults;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Grigoriy Petrov (Frunzik)
 * Date: 2013, 10
 * Time: 23:08
 */
public class XPathAnalizer {

    public static ArrayList<Match> getList(Document source) throws ParserConfigurationException, SAXException {
        ArrayList<Match> list = new ArrayList<Match>();
        Match match;
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            XPathExpression expression = xPath.compile("/LivescoreData/Sport/Category/Tournament/Match");
            NodeList matches = (NodeList) expression.evaluate(source, XPathConstants.NODESET);

            for (int i = 0; i < matches.getLength(); i++) {
                XPathExpression expr = xPath.compile("../Name[@language='en']/text()");
                String tournamentName = expr.evaluate(matches.item(i));
                expr = xPath.compile("../../Name[@language='en']/text()");
                String categoryName = expr.evaluate(matches.item(i));
                expr = xPath.compile("../../../Name[@language='en']/text()");
                String sportName = expr.evaluate(matches.item(i));
                expr = xPath.compile("./Team1/Name[@language='en']/text()");
                String team1Name = expr.evaluate(matches.item(i));
                expr = xPath.compile("./Team2/Name[@language='en']/text()");
                String team2Name = expr.evaluate(matches.item(i));


                String score;
                expr = xPath.compile("./Status/Name[@language='en']/text()");
                if (expr.evaluate(matches.item(i)).equals("Not started")) {
                    score = "Not started";
                } else {
                    expr = xPath.compile("./Scores/Score/Team1/text()");
                    score = expr.evaluate(matches.item(i));
                    expr = xPath.compile("./Scores/Score/Team2/text()");
                    score = score.concat(":" + expr.evaluate(matches.item(i)));
                }

                match = new Match(sportName, categoryName, tournamentName, team1Name, team2Name, score);
                list.add(match);


            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return list;
    }
}
