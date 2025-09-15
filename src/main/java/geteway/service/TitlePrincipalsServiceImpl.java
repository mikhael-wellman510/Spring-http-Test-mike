package geteway.service;

import geteway.entity.TitlePrincipals;
import geteway.repository.TitlePrincipalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class TitlePrincipalsServiceImpl {

	private final TitlePrincipalsRepository titlePrincipalsRepository;

	public TitlePrincipals findFirstByCharacter(String name){

		return titlePrincipalsRepository.findFirstByCharacters(name).orElse(null);

	}

	public TitlePrincipals findByCharacters(String name){

		return titlePrincipalsRepository.findByCharacters(name).orElse(null);
	}

	public TitlePrincipals findByIdAndCharacters(Long id , String characters , Integer type){

		if(type == 1){

			Optional<TitlePrincipals> get = titlePrincipalsRepository.findByIdAndCharacters(id,characters);
			log.info("Hasil : {} " , get);
			return get.orElse(null);
		}

		Optional<TitlePrincipals> get = titlePrincipalsRepository.findByCharacters(characters);

		return get.orElse(null);

	}

	public TitlePrincipals findById(String name) {

		return titlePrincipalsRepository.findByNconst(name).orElse(null);
	}

	public TitlePrincipals updatedByCharacter(String name) {

		CompletableFuture<TitlePrincipals> update = CompletableFuture.supplyAsync(()->{
			TitlePrincipals tp = titlePrincipalsRepository.findByCharacters(name)
					.orElseThrow(() -> new RuntimeException("not found"));
			tp.setCharacters("New Characters");


			return titlePrincipalsRepository.save(tp);
		});

		CompletableFuture<TitlePrincipals> find = CompletableFuture.supplyAsync(()->{

			TitlePrincipals fd = titlePrincipalsRepository.findByNconst("nm825091323").orElseThrow(() -> new RuntimeException("Not Found"));
			return titlePrincipalsRepository.findByCharacters(name).orElseThrow(()->new RuntimeException("Not Founds char"));
		});

		update.thenAccept(tp -> {
			log.info("Success !! {} " , tp);
		}).exceptionally(ex->{
			log.info("Err 1 : {} " , ex.getMessage());
			return null;
		});

		find.thenAccept(tp -> {
			log.info("Succes 2 {}" , tp);
		}).exceptionally(ex -> {
			log.info("Err 2: {} " , ex.getMessage());
			return null;
		});

		return null;


	}
}
