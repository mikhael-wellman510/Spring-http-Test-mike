package geteway.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "transaction_log")
public class TransactionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_account")
	private String fromAccount;

	@Column(name = "to_account")
	private String toAccount;

	private Long amount;
	private String status;
}
