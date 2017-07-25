package genmicro

import java.util.Date;

/**
 * This domain class is used to store the data of the user in the website database
 * it maps to omsuserr table of the website database
 * @author Armando Jaramillo Magallon
 */
class User {
	
	Long uid
	String userId
	String userType
	String userName
	String email
	String custCode
	String overrideCustCode
	String shipToCode
	String overrideShipToCode
	String clientCode
	String costCenter
	String overrideCostCenter
	Long buyLimit
	Long approveLimit
	String defaultApprover
	String overrideApprover
	String password
	String status
	Date modifyDate
	String modifyId
	public User(){
		
	}
	
	//construct method used with the data gathered from the csv file
	public User(Long uid, String userId, String userType, String userName, String email, String custCode, String overrideCustCode, String shipToCode, String overrideShipToCode, String clientCode, String costCenter, String overrideCostCenter, Long buyLimit, Long approveLimit, String defaultApprover, String overrideApprover, String password, String status, Date modifyDate, String modifyId){
		this.uid = uid
		this.userId = userId
		this.userType = userType
		this.userName = userName
		this.email = email
		this.custCode = custCode
		this.overrideCustCode = overrideCustCode
		this.shipToCode = shipToCode
		this.overrideShipToCode = overrideShipToCode
		this.clientCode = clientCode
		this.costCenter = costCenter
		this.overrideCostCenter = overrideCostCenter
		this.buyLimit = buyLimit
		this.approveLimit = approveLimit
		this.defaultApprover = defaultApprover
		this.overrideApprover = overrideApprover
		this.password = password
		this.status = status
		this.modifyDate = modifyDate
		this.modifyId = modifyId
	}
	
	static mapping = {
		table "omsuserr"
		version false
		id generator: 'assigned', name: 'uid', column: 'uid'
		userId column: 'userid'
	}

	//costCenter can be null because not all microsites have this feature
	static constraints = {
		costCenter(nullable:true)
    }
	
	def String toString() {
		return "genmicro.User[userid=${userId}, userName=${userName}]"
	}
}
