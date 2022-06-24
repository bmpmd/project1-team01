package com.revature.models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reimbursements")
public class Reimbursement {

	// id, amount, submitted, resolved, description,
	// receipt, author, resolver, status_id, type_id
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="submitted")
	private Timestamp submitted;
	
	@Column(name="resolved")
	private Timestamp resolved;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="author")
	private Employee author;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="resolver")
	private Manager resolver;
	
	@Enumerated(EnumType.STRING)
	private ReimbursementStatus status;
	
	@Enumerated(EnumType.STRING)
	private ReimbursementType type;

	public Reimbursement() {
		super();
	}

	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description,
			Employee author, Manager resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
			 Employee author, Manager resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getAuthor() {
		return author;
	}

	public void setAuthor(Employee author) {
		this.author = author;
	}

	public Manager getResolver() {
		return resolver;
	}

	public void setResolver(Manager resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		result = prime * result
				+ Objects.hash(amount, author, description, id, resolved, resolver, status, submitted, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(resolved, other.resolved)
				&& Objects.equals(resolver, other.resolver) && status == other.status
				&& Objects.equals(submitted, other.submitted) && type == other.type;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}
	
}
