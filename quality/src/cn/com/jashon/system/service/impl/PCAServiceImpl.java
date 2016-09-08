package cn.com.jashon.system.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.lang.Strings;

import cn.com.jashon.core.service.impl.BaseServiceImpl;
import cn.com.jashon.core.utils.Constant;
import cn.com.jashon.core.utils.JsonUtil;
import cn.com.jashon.system.domain.PCACode;
import cn.com.jashon.system.service.IPCAService;
import cn.com.jashon.system.utils.Codes;

@IocBean(name = "pcaService", args = { "refer:dao" })
public class PCAServiceImpl extends BaseServiceImpl implements IPCAService {

	public PCAServiceImpl(Dao dao){
		this.dao = dao;
	}

	public Map<String, String> loadPCA() {
		final Map<String, String> pcaAllMap = new LinkedHashMap<String, String>();
		final List<PCACode> pList = new ArrayList<PCACode>(32);
		final Map<String, List<PCACode>> pcMap = new LinkedHashMap<String, List<PCACode>>(32);
		final Map<String, List<PCACode>> caMap = new LinkedHashMap<String, List<PCACode>>(512);
		
		dao.each(PCACode.class, Cnd.orderBy().asc("code"), new Each<PCACode>(){
			String code = null;
			String name = null;
			
			@Override
			public void invoke(int index, PCACode pca, int length)
					throws ExitLoop, ContinueLoop, LoopException {
				code = pca.getCode();
				name = pca.getName();
				
				pcaAllMap.put(code, name);

				if(code.endsWith("0000")) { // province
					pList.add(pca);
				} else if(code.endsWith("00")) { // city
					putPCA2Map(pca, pcMap);
				} else { // area
					putPCA2Map(pca, caMap);
				}
			}
		});
		
		final Map<String, String> pcJsonMap = new LinkedHashMap<String, String>(32);
		final Map<String, String> caJsonMap = new LinkedHashMap<String, String>(512);
		putJson2Map(pcMap, pcJsonMap);
		putJson2Map(caMap, caJsonMap);
		
		Codes.update(Constant.SC_CODE_PCA_ALL, pcaAllMap);
		Codes.update(Constant.SC_CODE_PCA_P, Constant.SC_CODE_PCA_P60, JsonUtil.toJson(pList));
		Codes.update(Constant.SC_CODE_PCA_PC, pcJsonMap);
		Codes.update(Constant.SC_CODE_PCA_CA, caJsonMap);
		
		return pcaAllMap;
	}
	
	private void putPCA2Map(PCACode pca, Map<String, List<PCACode>> map) {
		if(pca == null) return;
		final String pcode = pca.getPcode();
		if(Strings.isBlank(pcode)) return;
		
		if(!map.containsKey(pcode)) {
			map.put(pcode, new ArrayList<PCACode>());
		}
		map.get(pcode).add(pca);
	}
	
	private void putJson2Map(Map<String, List<PCACode>> srcMap, Map<String, String> destMap) {
		for(Map.Entry<String, List<PCACode>> m : srcMap.entrySet()) {
			destMap.put(m.getKey(), JsonUtil.toJson(m.getValue()));
		}
	}
	
}
