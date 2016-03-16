package com.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "route")
public class Route {

    @DatabaseField(generatedId = true)
    private int id;
    private String routeName;
    @DatabaseField(index = true, unique = true)
    private String routePublicIdentifier;
    @DatabaseField
    private String routeDirection;
    @DatabaseField
    private String href;


    public int getId() {
        return id;
    }

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
