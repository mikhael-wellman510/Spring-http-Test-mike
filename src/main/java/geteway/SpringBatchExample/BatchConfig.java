package geteway.SpringBatchExample;

import geteway.entity.TitlePrincipals;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

	private final DataSource dataSource;
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	@Bean
	public JdbcPagingItemReader<TitlePrincipals>reader(){

		JdbcPagingItemReader<TitlePrincipals> reader = new JdbcPagingItemReader<>();

		reader.setDataSource(dataSource);
		reader.setPageSize(10000);
		reader.setMaxItemCount(5000000);

		MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
		provider.setSelectClause("id, tconst, ordering, nconst, category, job, characters");
		provider.setFromClause("from title_principals");
		provider.setSortKeys(Collections.singletonMap("id" , Order.ASCENDING));


		reader.setQueryProvider(provider);
		reader.setRowMapper((rs, rowNum) ->{
			TitlePrincipals tp = new TitlePrincipals();
			tp.setId(rs.getLong("id"));
			tp.setNconst(rs.getString("nconst"));
			tp.setOrdering(rs.getInt("ordering"));
			tp.setCategory(rs.getString("category"));
			tp.setJob(rs.getString("job"));
			tp.setCharacters(rs.getString("characters"));

			return tp;
		});

		return reader;
	}


	@Bean
	public FlatFileItemWriter<TitlePrincipals> writer() {


		String outputDir = "C:/Users/Mikhael Wellman/Documents/MICROSERVICE/gateway/resource";
		new File(outputDir).mkdirs();

		return new FlatFileItemWriterBuilder<TitlePrincipals>()
				.name("titlePrincipalsWriters")
				.resource(new FileSystemResource(outputDir + "export-title-principals.csv")) // output file
				.delimited()
				.delimiter(",")
				.names("id", "nconst", "ordering" , "category" , "job", "characters")
				.build();
	}

	@Bean
	public Step exportStep(JdbcPagingItemReader<TitlePrincipals> reader,
	                       FlatFileItemWriter<TitlePrincipals> writer) {
		return new StepBuilder("exportStep" , jobRepository)
				.<TitlePrincipals, TitlePrincipals>chunk(10000 , transactionManager)
				.reader(reader)
				.writer(writer)
				.listener(new ProgressListener())
				.build();
	}

	@Bean
	public Job exportJob(Step exportStep) {
		return new JobBuilder("exportJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(exportStep)
				.build();
	}
}
