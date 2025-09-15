package geteway.service;

import geteway.entity.Book;
import geteway.entity.Room;
import geteway.repository.RoomRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl {

	private final RoomRepository roomRepository;


	// Optimistic locking itu ketika version nya sudah berubah , dia auto gagal
	@Transactional
	public void bookingRoom(Long id){


		try {
			Room room = roomRepository.findById(id).orElseThrow();

			room.setQty(room.getQty()-1);

			roomRepository.saveAndFlush(room);

			log.info("cek --- :{} " , room);

		} catch (OptimisticLockException e) {
			log.warn("Gagal Reservasi room (optimistic lock): {}", e.getMessage());
		}

	}
}
