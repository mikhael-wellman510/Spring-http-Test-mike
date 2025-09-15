package geteway.SpringBatchExample;


import geteway.entity.TitlePrincipals;
import geteway.repository.TitlePrincipalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExportControllers {

	private final JobLauncher jobLauncher;
	private final Job exportJob;
	private final TitlePrincipalsRepository titlePrincipalsRepository;


	// Todo -> ini di gunakan supaya tidak heap , karena kalau jutaan data di simpan di list  , pasti berat
	@PostMapping("/usersExp")
	public ResponseEntity<?>exportUser() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters params = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis()) // supaya bisa run ulang
				.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(exportJob, params);

		return ResponseEntity.ok("Export Status  : " + jobExecution.getStatus());
	}

	// Todo -> ini akan heap , karena menampung 10jt data di list
	@GetMapping("/testingMemory")
	public void heapTest(){
		Runtime rt = Runtime.getRuntime();

		System.out.println("Max Memory : " + rt.maxMemory() / 1024 / 1024 + " MB");

		// load data
		List<TitlePrincipals> users = titlePrincipalsRepository.findFirst10Million();
		System.out.println("Total : " + users.size());

		long used = (rt.totalMemory() - rt.freeMemory()) / 1024 / 1024;
		System.out.println("Heap terpakai : " + used + " MB");
	}
}
