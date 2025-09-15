package geteway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bankdataset")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BankDataSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String domain;
	private String location;
	private Integer value;
	@Column(name = "transaction_count")
	private Integer transactionCount;

	@Column(name = "created_at")
	private LocalDate createdAt;
}
