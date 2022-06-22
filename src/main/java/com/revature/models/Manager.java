package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="managers")
public class Manager {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="pwd", nullable=false)
	private String pwd;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@OneToMany(mappedBy="resolver", fetch=FetchType.LAZY)
	List<Reimbursement> reimbursementList = new ArrayList<>();

	public Manager() {
		super();
	}

	public Manager(String username, String pwd, String firstName, String lastName, String email,
			List<Reimbursement> reimbursementList) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reimbursementList = reimbursementList;
	}

	public Manager(int id, String username, String pwd, String firstName, String lastName, String email,
			List<Reimbursement> reimbursementList) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reimbursementList = reimbursementList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reimbursement> getReimbursementList() {
		return reimbursementList;
	}

	public void setReimbursementList(List<Reimbursement> reimbursementList) {
		this.reimbursementList = reimbursementList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, pwd, reimbursementList, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(pwd, other.pwd)
				&& Objects.equals(reimbursementList, other.reimbursementList)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", username=" + username + ", pwd=" + pwd + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", reimbursementList=" + reimbursementList + "]";
	}
	
}
