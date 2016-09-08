var ioc = {
	log : {
		type : "org.nutz.aop.interceptor.LoggingMethodInterceptor"
	},
	txREAD_COMMITED : {
		type : "org.nutz.aop.interceptor.TransactionInterceptor",
		args : [2]
	}
	$aop : {
		type : "org.nutz.ioc.aop.config.impl.JsonAopConfigration",
		fields : {
			itemList : [
				["cn\\.com\\.whye\\..*\\.service\\..*", ".*", "ioc:log"],
				["cn\\.com\\.whye\\..*\\.service\\..*", ".*", "ioc:txREAD_COMMITED"]
			]
		}
	}
};