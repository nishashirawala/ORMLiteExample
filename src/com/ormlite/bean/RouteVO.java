package com.ormlite.bean;

public class RouteVO {

    private String routeName;
    private String routePublicIdentifier;
    private String routeDirection;
    private String href;


    public String getRoutePublicIdentifier() {
        return routePublicIdentifier;
    }

    public void setRoutePublicIdentifier(String routePublicIdentifier) {
        this.routePublicIdentifier = routePublicIdentifier;
    }

    public String getRouteDirection() {
        return routeDirection;
    }

    public void setRouteDirection(String routeDirection) {
        this.routeDirection = routeDirection;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
