var ioc = {
	dataSource : {
		type : "com.mchange.v2.c3p0.ComboPooledDataSource",
		fields : {
			driverClass : "com.mysql.jdbc.Driver",
			jdbcUrl : "jdbc:mysql://10.0.0.111:3306/quality?useUnicode=true&characterEncoding=UTF-8",
			user : "root",
			password : "root"
		},
		events : {
			depose : 'close'
		}
	},
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [{refer : "dataSource"}]
	}/*,
	ds_sqlserver : {
		type : "com.mchange.v2.c3p0.ComboPooledDataSource",
		fields : {
			driverClass : "net.sourceforge.jtds.jdbc.Driver",
			jdbcUrl : "jdbc:jtds:sqlserver://10.10.20.188:1433/CCRC2014001",
			user : "sa",
			password : "admin@123"
		},
		events : {
			depose : 'close'
		}
	},
	dao_sqlserver : {
		type : "org.nutz.dao.impl.NutDao",
		args : [{refer : "ds_sqlserver"}]
	}*/
};