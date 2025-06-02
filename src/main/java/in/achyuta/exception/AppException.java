package in.achyuta.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppException {
	
	private String exceptionCode;
	private String exceptionDesc;
	private LocalDateTime exceptionDate;

}
