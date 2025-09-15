package geteway.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "room")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "room_name")
	private String roomName;

	private Boolean available;

	private Integer qty;

	@Version
	private Integer version;
}
