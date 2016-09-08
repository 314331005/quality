package cn.com.jashon.export.domain;

import org.junit.Test;

import cn.com.jashon.core.query.OrderType;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.export.domain.ExportModel;

public class ExportModelTest {
	
	@Test
	public void testRunDetail() {
		ExportModel m = new ExportModel();
		m.setTag("run");
		m.setName("车辆使用信息");
		
		m.addOrder("vehicleType", OrderType.ASC);
		m.addOrder("vehicleNum", OrderType.ASC);
		m.addOrder("id", OrderType.ASC);
		
		m.addTitle("车辆类型", 20, "vehicleType");
		m.addTitle("车牌号码", 20, "vehicleNum");
		m.addTitle("车辆状态", 20, "status");
		m.addTitle("使用开始时间", 20, "startTime");
		m.addTitle("使用结束时间", 20, "endTime");
		m.addTitle("初始里程(KM)", 20, "startMile");
		m.addTitle("行驶里程(KM)", 20, "runMile");
		final String jsonString = JsonUtil.toJson(m);
		//{"tag":"run","name":"车辆使用信息","titles":[{"title":"车辆类型","width":20,"field":"vehicleType"}, {"title":"车牌号码","width":20,"field":"vehicleNum"}, {"title":"车辆状态","width":20,"field":"status"}, {"title":"使用开始时间","width":20,"field":"startTime"}, {"title":"使用结束时间","width":20,"field":"endTime"}, {"title":"初始里程(KM)","width":20,"field":"startMile"}, {"title":"行驶里程(KM)","width":20,"field":"runMile"}]}
		System.out.println(jsonString);
	}
	
	@Test
	public void testMaintenDetail() {
		ExportModel m = new ExportModel();
		m.setTag("mainten");
		m.setName("车辆保养信息");
		
		m.addOrder("vehicleType", OrderType.ASC);
		m.addOrder("vehicleNum", OrderType.ASC);
		m.addOrder("id", OrderType.ASC);
		
		m.addTitle("车辆类型", 20, "vehicleType");
		m.addTitle("车牌号码", 20, "vehicleNum");
		m.addTitle("保养类型", 20, "maintenType");
		m.addTitle("保养时里程(KM)", 20, "maintenMile");
		m.addTitle("下次保养里程", 20, "planMile");
		m.addTitle("保养开始时间", 20, "startTime");
		m.addTitle("保养结束时间", 20, "endTime");
		m.addTitle("保养内容", 120, "content");
		final String jsonString = JsonUtil.toJson(m);
		//{"tag":"run","name":"车辆使用信息","titles":[{"title":"车辆类型","width":20,"field":"vehicleType"}, {"title":"车牌号码","width":20,"field":"vehicleNum"}, {"title":"车辆状态","width":20,"field":"status"}, {"title":"使用开始时间","width":20,"field":"startTime"}, {"title":"使用结束时间","width":20,"field":"endTime"}, {"title":"初始里程(KM)","width":20,"field":"startMile"}, {"title":"行驶里程(KM)","width":20,"field":"runMile"}]}
		System.out.println(jsonString);
	}
	
	@Test
	public void testRepairDetail() {
		ExportModel m = new ExportModel();
		m.setTag("repair");
		m.setName("车辆维修信息");
		
		m.addOrder("vehicleType", OrderType.ASC);
		m.addOrder("vehicleNum", OrderType.ASC);
		m.addOrder("id", OrderType.ASC);
		
		m.addTitle("车辆类型", 20, "vehicleType");
		m.addTitle("车牌号码", 20, "vehicleNum");
		m.addTitle("车辆状态", 20, "status");
		m.addTitle("维修时里程(KM)", 20, "mile");
		m.addTitle("维修开始时间", 20, "startTime");
		m.addTitle("维修结束时间", 20, "endTime");
		m.addTitle("备注", 120, "content");
		final String jsonString = JsonUtil.toJson(m);
		//{"tag":"run","name":"车辆使用信息","titles":[{"title":"车辆类型","width":20,"field":"vehicleType"}, {"title":"车牌号码","width":20,"field":"vehicleNum"}, {"title":"车辆状态","width":20,"field":"status"}, {"title":"使用开始时间","width":20,"field":"startTime"}, {"title":"使用结束时间","width":20,"field":"endTime"}, {"title":"初始里程(KM)","width":20,"field":"startMile"}, {"title":"行驶里程(KM)","width":20,"field":"runMile"}]}
		System.out.println(jsonString);
	}
	
	@Test
	public void testVehicleInfo() {
		ExportModel m = new ExportModel();
		m.setTag("vehicle");
		m.setName("车辆信息");
		
		m.addOrder("vehicleType", OrderType.ASC);
		m.addOrder("vehicleNum", OrderType.ASC);
		m.addOrder("id", OrderType.ASC);
		
		m.addTitle("车辆类型", 20, "vehicleType");
		m.addTitle("车牌号码", 20, "vehicleNum");
		m.addTitle("登记时里程(KM)", 20, "mile");
		m.addTitle("当前里程(KM)", 20, "currMile");
		m.addTitle("GPS总里程(KM)", 20, "gpsMile");
		m.addTitle("保养里程(KM)", 20, "planMile");
		m.addTitle("警告里程(KM)", 20, "warnMile");
		m.addTitle("车辆状态", 20, "status");
		final String jsonString = JsonUtil.toJson(m);
		//{"tag":"run","name":"车辆使用信息","titles":[{"title":"车辆类型","width":20,"field":"vehicleType"}, {"title":"车牌号码","width":20,"field":"vehicleNum"}, {"title":"车辆状态","width":20,"field":"status"}, {"title":"使用开始时间","width":20,"field":"startTime"}, {"title":"使用结束时间","width":20,"field":"endTime"}, {"title":"初始里程(KM)","width":20,"field":"startMile"}, {"title":"行驶里程(KM)","width":20,"field":"runMile"}]}
		System.out.println(jsonString);
	}

}
