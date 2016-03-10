package com.ormlite.bean;

// VO to store route information
public class RouteVO {

    private String routeId;

    private String routeType;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }


    private String routeName;
    private String routePublicIdentifier;
    private String routeDirection;

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
}
