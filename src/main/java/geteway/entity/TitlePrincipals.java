package geteway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "title_principals",
	indexes = {
		@Index(name = "idx_characters" , columnList = "characters")
	}
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TitlePrincipals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nconst;
	private Integer ordering;

	@Column(name = "category")
	private String category;
	@Lob
	private String job;

	@Column(columnDefinition = "TEXT")
	private String characters;
}
