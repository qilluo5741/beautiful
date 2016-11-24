package com.xtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="statistics")
public class statistics {
	@Id
	@GeneratedValue(generator="uuid_")
	@GenericGenerator(name="uuid_",strategy="uuid")
	@Column(length=40)
	private String statisticid;
	private int statisticcount;
	private String statisticsno;
	public String getStatisticid() {
		return statisticid;
	}
	public void setStatisticid(String statisticid) {
		this.statisticid = statisticid;
	}
	public int getStatisticcount() {
		return statisticcount;
	}
	public void setStatisticcount(int statisticcount) {
		this.statisticcount = statisticcount;
	}
	public String getStatisticsno() {
		return statisticsno;
	}
	public void setStatisticsno(String statisticsno) {
		this.statisticsno = statisticsno;
	}
}
