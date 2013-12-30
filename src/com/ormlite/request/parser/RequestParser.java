package com.ormlite.request.parser;

import static com.ormlite.util.Constants.EXPRESS;
import static com.ormlite.util.Constants.LOCAL;
import static com.ormlite.util.Constants.ROUTE_STOPS_JSP;
import static com.ormlite.util.Constants.SCHOOL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ormlite.bean.RouteVO;
import com.ormlite.util.Constants;

public class RequestParser {

	public static List<RouteVO> parseRoutes() {
		Document doc;
		List<RouteVO> routes = new ArrayList<RouteVO>();
		try {
			doc = Jsoup.connect(Constants.ROUTE_LIST_URL).get();

			Elements elements = doc.select("a");
			boolean expressRoute = false;
			boolean localRoute = false;
			boolean schoolRoute = false;

			for (Element element : elements) {
				String name = element.attr("name");
				String href = element.attr("href");
				String text = element.text();
				if (EXPRESS.equals(name)) {
					expressRoute = true;
					localRoute = false;
					schoolRoute = false;
				}
				if (LOCAL.equals(name)) {
					expressRoute = false;
					localRoute = true;
					schoolRoute = false;
				}
				if (SCHOOL.equals(name)) {
					expressRoute = false;
					localRoute = false;
					schoolRoute = true;
				}

				if (expressRoute == true) {
					populateRouteList(routes, href, text, EXPRESS);
				}
				if (localRoute == true) {
					populateRouteList(routes, href, text, LOCAL);
				}
				if (schoolRoute == true) {
					populateRouteList(routes, href, text, SCHOOL);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return routes;
	}

	private static void populateRouteList(List<RouteVO> routeList, String href, String text, String routeType) {
		if (href != null && href.contains(ROUTE_STOPS_JSP)) {
			String[] split = href.split("\\?id=");
			if (split.length > 1) {
				RouteVO route = new RouteVO();
				route.setRouteId(split[1]);
				route.setRouteName(text);
				route.setRouteType(routeType);
				routeList.add(route);
			}
		}
	}

}
