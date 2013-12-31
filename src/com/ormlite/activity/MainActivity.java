package com.ormlite.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.ormlite.bean.RouteVO;
import com.ormlite.database.DatabaseHelper;
import com.ormlite.model.Route;
import com.ormlite.request.parser.RequestParser;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHelper databaseHelper = getHelper();
		try {
			Dao<com.ormlite.model.Route, Integer> routeDao = databaseHelper.getRouteDao();
			insertRouteData(routeDao);
			showRouteData(routeDao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showRouteData(Dao<com.ormlite.model.Route, Integer> routeDao) {
		try {
			listView = (ListView) findViewById(R.id.list);
			List<Route> savedRoutes = routeDao.queryForAll();
			List<String> values = new ArrayList<String>();
			for (Route route : savedRoutes) {
				values.add(route.getRouteName());
			}			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
			listView.setEmptyView((TextView) findViewById(android.R.id.empty));
			listView.setAdapter(adapter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void insertRouteData(Dao<com.ormlite.model.Route, Integer> routeDao) {
		List<RouteVO> routeVOList = RequestParser.parseRoutes();
		try {
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
