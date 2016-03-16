package com.ormlite.request.parser;


import com.ormlite.bean.RouteVO;
import com.ormlite.util.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ormlite.util.Constants.ROUTE_STOPS_JSP;

public class RouteRequestParser {

    public static List<RouteVO> parseRoutes() {
        Document doc;
        List<RouteVO> routes = new ArrayList<RouteVO>();
        try {
            doc = Jsoup.connect(Constants.ROUTE_LIST_URL).get();
            Elements elements = doc.select("div[data-role=content]").select("a");
            for(Element element : elements) {
                String href = element.attr("href");
                String text = element.text();

                if (href != null && href.contains(ROUTE_STOPS_JSP)) {
                    String[] split = href.split("\\?");
                    if (split.length > 1) {
                        String routeIdAndDirStr  = split[1];
                        String[] routeIdAndDir = routeIdAndDirStr.split("&");
                        if(routeIdAndDir.length >1) {
                            String routeIdStr =  routeIdAndDir[0];
                            String[] routeIds = routeIdStr.split("routePublicIdentifier=");
                            String routePublicIdentifier = routeIds[1];

                            String routeDirStr = routeIdAndDir[1];
                            String[] routeDirs = routeDirStr.split("routeDirectionName=");
                            String routeDir = routeDirs[1];

                            RouteVO route = new RouteVO();
                            route.setRoutePublicIdentifier(routePublicIdentifier);
                            route.setRouteName(text);
                            route.setHref(href);
                            route.setRouteDirection(routeDir);
                            routes.add(route);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public static void main(String[] args) {
        RouteRequestParser.parseRoutes();
    }
}
