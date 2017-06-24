package com.worksApproval.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
/**
 * 用户表
 * @author rocky
 *
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -2567614267053765516L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name", nullable = false, length = 50, unique = true)
	private String username;//昵称
	@Column(name = "password", nullable = false, length = 20)
	private String password;//密码
	@Column(name = "real_name")
	private String realName;//真实姓名

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday")
	private Date birthday;// 出生年月日

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date")
	private Date registerDate;//注册时间
	@Column(name = "gender", length = 1, columnDefinition = "int default 1")
	// 默认是1表示male
	private Integer gender;//性别
	@Column(name = "phone_number", length = 11)
	private String phoneNumber;//手机号码
	@Column(name = "email", length = 50)
	private String email;//邮箱

	@Column(name = "address", length = 125)
	private String address;//地址
	//加入一对多,一的这段的集合,然后考虑一下是否排序,如果不排序,后续改排序怎么改,不排序适合新增多还是查询多
	@Lob
	@Type(type = "text")
	@Column(name = "profile")
	private String profile;//个性签名

	
	//因为无需从N的一端去查,所以在UserRole中不用配置
	@ManyToOne(targetEntity=UserRole.class)//默认是fetch=FetchType.EAGER
	@JoinColumn(name="role_id")
	//@Cascade(CascadeType.ALL)这句的意思是当一个userinfo对象新增,删除,更新时UserROle表中关联的数据也要做相同的处理?
	private UserRole userRole;//角色
 
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


	public Integer getGender() {
		return gender;
	}


	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password="
				+ password + ", realName=" + realName + ", birthday="
				+ birthday + ", registerDate=" + registerDate + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", address=" + address + ", profile=" + profile
				+ ", userRole=" + userRole + "]";
	}


	
	
}
