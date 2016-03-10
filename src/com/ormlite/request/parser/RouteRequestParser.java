package com.ormlite.request.parser;


import com.ormlite.bean.RouteVO;
import com.ormlite.util.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouteRequestParser {

    public static List<String> parseRoutes() {
        Document doc;
        List<RouteVO> routes = new ArrayList<RouteVO>();
        try {
            doc = Jsoup.connect(Constants.ROUTE_LIST_URL).get();
            Elements elements = doc.select("div[data-role=content]");
            System.out.println(elements.size());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        RouteRequestParser.parseRoutes();
    }
}
