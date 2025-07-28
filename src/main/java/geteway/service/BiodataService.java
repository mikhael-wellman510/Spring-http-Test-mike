package geteway.service;

import geteway.dto.BiodataDto;
import geteway.dto.PagginatedResponse;
import geteway.entity.Biodata;
import geteway.repository.BiodataRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BiodataService {

    private final BiodataRepository biodataRepository;


    public PagginatedResponse findBiodataByName(Integer page , Integer size , String title){
        Pageable pageable = PageRequest.of(page,size);

        Specification<Biodata> specification = (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null){
                log.info("Masuk ke Name");
               Predicate names = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+title.toLowerCase()+"%");
               Predicate address = criteriaBuilder.like(criteriaBuilder.lower(root.get("collage")), "%"+title.toLowerCase()+"%");

               predicates.add(criteriaBuilder.or(names,address));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }));

        Page<Biodata> biodata = biodataRepository.findAll(specification,pageable);

        List<BiodataDto> listBio = new ArrayList<>();

        for (Biodata bio : biodata.getContent()){
            listBio.add(BiodataDto.builder()
                            .id(bio.getId())
                            .name(bio.getName())
                            .address(bio.getAddress())
                            .hobby(bio.getHobby())
                            .collage(bio.getCollage())
                            .age(bio.getAge())
                    .build());
        }



        return new PagginatedResponse(listBio ,page ,size,biodata.getTotalElements());
    }


    @Scheduled(cron = "0 43 12 * * *", zone = "Asia/Jakarta")
    @Transactional
    public void deleted(){
        int days = 5;
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days).toLocalDate().atStartOfDay();
        biodataRepository.deleteByBatchTimeBefore(cutoff);
    }
}
