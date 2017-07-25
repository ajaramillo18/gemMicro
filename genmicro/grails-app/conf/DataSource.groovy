dataSource {
	dbCreate = "none" // one of 'none', 'create', 'create-drop', 'update'
	dialect = "org.hibernate.dialect.InformixDialect"
	logSql = true
}

hibernate {
	hbm2ddl.auto='none'
}

// environment specific settings
environments {
	development {
		dataSource {
			pooled = true
			driverClassName = "com.informix.jdbc.IfxDriver"
			username = "webiddev"
			password = "web4@100"	
			url = "jdbc:informix-sqli://mxdev.b0510.grainger.com:1537/opprod:INFORMIXSERVER=ifx_mxdevtcp"
			logSql = true
		}
	}
	
	test {
		dataSource {
			jndiName = "java:jdbc/enduraPool"
			logSql = false
		}
	}
	
	production {
		dataSource {
			jndiName = "java:jdbc/enduraPool"
			logSql = false
		}
	}
}