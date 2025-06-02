package in.achyuta.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IES_PLANS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanEntity {
	@Id
	@Column(name = "PLAN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer planId;
	
	@Column(name = "PLAN_CATEGORY")
    private String planCategory;
	
	@Column(name = "PLAN_NAME")
    private String planName;
	
	@Column(name = "PLAN_START_DATE")
    private LocalDate planStartDate;
	
	@Column(name = "PLAN_END_DATE")
    private LocalDate planEndDate;
	
	@Column(name = "PLAN_STATUS")
    private String activeSw;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

}
