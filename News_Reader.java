package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class News_Reader {
    public void getNews() {
        URL rssUrl = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What news do you want? ");
        String keyword = scanner.next();
        String inputUrl = "https://news.google.com/rss/search?q=" + keyword;
        try {
            rssUrl = new URL(inputUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = builder.newDocument();
        String news = null;
        int i = 0;
        try {
            doc = builder.parse(rssUrl.openStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeList items = doc.getElementsByTagName("item");
        for(i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            String S = getValue(item, "title");
            String T = getValue(item, "link");
            System.out.println("Title: " + S + "\n" + "Link" + T);
        }
    }

    private String getValue(Element parent, String nodeName) {
        return parent.getElementsByTagName(nodeName).item(0).getFirstChild().toString();
    }
}
