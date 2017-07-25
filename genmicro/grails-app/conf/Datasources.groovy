datasources = {
	datasource(name: 'stores') {
		domainClasses([genmicro.Omsuserr])
		environments(['development'])
		readOnly(false)
		driverClassName('com.informix.jdbc.IfxDriver')
		url('jdbc:informix-sqli://dvsmx501.gcom.grainger.com:1550/opprod:INFORMIXSERVER=wwg_mty1_tcp')
		username('webiddev')
		password('8z6@Ro76')
		logSql(true)
		dialect(org.hibernate.dialect.InformixDialect)
	}
	
	datasource(name: 'store') {
		domainClasses([genmicro.User])
		environments(['test', 'production'])
		readOnly(false)
		logSql(false)
		dialect(org.hibernate.dialect.InformixDialect)
		jndiName("java:jdbc/storePool")
	}
}