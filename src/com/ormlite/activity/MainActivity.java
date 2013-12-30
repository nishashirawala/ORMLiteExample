package com.ormlite.activity;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.ormlite.bean.RouteVO;
import com.ormlite.database.DatabaseHelper;
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
			for (RouteVO routeVO : routeVOList) {
				com.ormlite.model.Route route = new com.ormlite.model.Route();
				route.setRouteId(routeVO.getRouteId());
				route.setRouteName(routeVO.getRouteName());
				route.setRouteType(routeVO.getRouteType());
				routeDao.create(route);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
