/**
 * 
 */
package com.training.users;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 * @author Pratik Das
 *
 */
@Service
@Slf4j
public class NotifyIntegrator {
	
	@Value("${api.url.notifier}")
	private String notiferApiURL;
	
	public Integer sendEmail(final String firstName, final String messageText, final String email) {
		log.info("notifier url {}",notiferApiURL);
		EmailNotification emailNotification = EmailNotification.builder().messageText(messageText).email(email).build();;

		WebClient webClient = getWebClient(notiferApiURL);
		
		String response = webClient.post()
        .uri("/notify/email/send")
        .body(Mono.just(emailNotification), EmailNotification.class)
        .retrieve()
        .bodyToMono(String.class).block();
		

		
		log.info("jsonResponse  {}", response);
		return 0;
	}
	
	
	private WebClient getWebClient(final String baseUrl) {
		TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.doOnConnected(connection -> {
					connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
					connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				});

		WebClient client = WebClient.builder()
				.baseUrl(baseUrl)
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();
		return client;
	}


}
