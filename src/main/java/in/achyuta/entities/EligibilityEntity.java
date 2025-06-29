package in.achyuta.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ELIGIBILITY_DTLS")
public class EligibilityEntity {
	@Column(name = "ELIG_TRACE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer egdTraceaId;
	private String planStatus;
	private Double benefitAmt;

}
