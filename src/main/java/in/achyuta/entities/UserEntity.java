package in.achyuta.entities;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IES_USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;
	
	@Column(name = "FULL_NAME")
	private String userFullName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	@Column(name = "USER_PWD")
	private String userPwd;
	
	@Column(name = "USER_PHNO")
	private Long userPhn;
	
	@Column(name = "USER_GENDER")
	private String userGender;
	
	@Column(name = "USER_DOB")
	private  LocalDate userDob;
	
	@Column(name = "USER_SSN")
	private Long userSSN;
	
	@Column(name = "ACTIVE_SW")
	private String activeSw;
	
	@Column(name = "ACC_STATUS")
	private String accSts;
	
	@Column(name = "ROLE_ID")
	private Integer rollId;
	
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	 private List<PlanEntity> plans;
	
//	private String createdBy;
//	private String updatedBy;
	
	
	
	
=======
public class UserEntity {
>>>>>>> develop

}
