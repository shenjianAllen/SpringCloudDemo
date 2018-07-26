package com.cloud;

import org.springframework.stereotype.Component;

//因为未配置spring扫描，所以在此单独将此类注入到容器中
@Component
public class SchedualServiceHiHystricImpl implements SchedualServiceHi {

	@Override
	public String sayHiFromClientOne(String name) {
		return "sorry "+name;
	}

}
