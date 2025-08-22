package geteway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class DownloadPdfService {

		private final AsyncHttpClient asyncHttpClient;
		private final ObjectMapper objectMapper;
		public CompletableFuture<?>getPdf(){

				String url = "https://localhost:8000/getPdf";

				return asyncHttpClient.prepareGet(url)
						.execute(new AsyncCompletionHandler<Object>() {
							@Override
							public @Nullable Object onCompleted(@Nullable Response response) throws Exception {
								log.info("hasil {} " , response.getResponseBody());
								return response.getResponseBodyAsBytes();
							}
						}).toCompletableFuture();


		}

}
