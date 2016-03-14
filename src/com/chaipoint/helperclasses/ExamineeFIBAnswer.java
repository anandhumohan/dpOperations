/**
 * 
 */
package com.chaipoint.helperclasses;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * @author M1024159
 * Fill in the blanks answered by the examinee
 */
@Entity
@Table(name="EXAMINEE_FIB_ANSWER")
public class ExamineeFIBAnswer {
    @EmbeddedId
	private ExamineeFIBAnswerKey examineeFIBAnswerKey;
    @Column(name="ANSWER")
	private String answer;
    @Column(name="SCORE")
	private double score;

	/**
	 * 
	 */
	public ExamineeFIBAnswer() {
	}

	/**
	 * @param examineeFIBAnswerKey
	 * @param answer
	 * @param score
	 */
	public ExamineeFIBAnswer(ExamineeFIBAnswerKey examineeFIBAnswerKey,
			String answer, double score) {
		this.examineeFIBAnswerKey = examineeFIBAnswerKey;
		this.answer = answer;
		this.score = score;
	}

	/**
	 * @return the examineeFIBAnswerKey
	 */
	public ExamineeFIBAnswerKey getExamineeFIBAnswerKey() {
		return examineeFIBAnswerKey;
	}

	/**
	 * @param examineeFIBAnswerKey
	 *            the examineeFIBAnswerKey to set
	 */
	public void setExamineeFIBAnswerKey(
			ExamineeFIBAnswerKey examineeFIBAnswerKey) {
		this.examineeFIBAnswerKey = examineeFIBAnswerKey;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime
				* result
				+ ((examineeFIBAnswerKey == null) ? 0 : examineeFIBAnswerKey
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamineeFIBAnswer other = (ExamineeFIBAnswer) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (examineeFIBAnswerKey == null) {
			if (other.examineeFIBAnswerKey != null)
				return false;
		} else if (!examineeFIBAnswerKey.equals(other.examineeFIBAnswerKey))
			return false;
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(other.score))
			return false;
		return true;
	}

}
