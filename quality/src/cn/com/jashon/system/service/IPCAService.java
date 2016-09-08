package cn.com.jashon.system.service;

import java.util.Map;

import cn.com.jashon.core.service.IBaseService;

public interface IPCAService extends IBaseService {

	public Map<String, String> loadPCA();
	
}
