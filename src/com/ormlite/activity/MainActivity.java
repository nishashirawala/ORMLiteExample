package com.ormlite.activity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.Menu;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.ormlite.bean.RouteVO;
import com.ormlite.database.DatabaseHelper;
import com.ormlite.model.Route;
import com.ormlite.request.parser.RequestParser;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		insertRouteData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void insertRouteData() {
		List<RouteVO> routeVOList = RequestParser.parseRoutes();
		DatabaseHelper databaseHelper = getHelper();
		try {
			Dao<com.ormlite.model.Route, Integer> routeDao = databaseHelper.getRouteDao();
			List<Route> savedRoutes = routeDao.queryForAll();
			Map<String, Route> map = convertToMap(savedRoutes);
			for (RouteVO routeVO : routeVOList) {
				Route route = map.get(routeVO.getRouteId());
				if(route == null) {
					route = new Route();
				}
				route.setRouteId(routeVO.getRouteId());
				route.setRouteName(routeVO.getRouteName());
				route.setRouteType(routeVO.getRouteType());
				routeDao.createOrUpdate(route);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, Route> convertToMap(List<Route> routeList) {
		Map<String, Route> map = new HashMap<String, Route>();
		if(routeList != null && routeList.size()>0) {
			for (Route route : routeList) {
				map.put(route.getRouteId(), route);
			}
		}
		return map;
	}
}
