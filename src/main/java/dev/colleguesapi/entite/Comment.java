package dev.colleguesapi.entite;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int matriculeComment;
	@Column(name="TEXT")
	private String text;
	@Column(name="DATE_EMISSION")
	private LocalDateTime dateEmit;
	
	@ManyToOne
	@JoinColumn(name="COLLEGUE_MATRICULE")
	private Collegue collegue;
	
	public Comment() {}
	
	public Comment(String text) {
		super();
		this.text = text;
	}

	public LocalDateTime getDateEmit() {
		return dateEmit;
	}

	public void setDateEmit(LocalDateTime dateEmit) {
		this.dateEmit = dateEmit;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMatriculeComment() {
		return matriculeComment;
	}

	public void setMatriculeComment(int matriculeComment) {
		this.matriculeComment = matriculeComment;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}


}
