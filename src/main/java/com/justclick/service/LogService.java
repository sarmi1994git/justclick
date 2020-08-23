package com.justclick.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.justclick.entity.Agent;

@Service
public class LogService {
	@Autowired
	private AgentService agentService;
	private Logger log = LoggerFactory.getLogger(LogService.class);
	@Value("${path.json.file}")
	private String filePath = "";
	public void logRequest(HttpHeaders headers) {
		Gson gson = new Gson();
		try {
			log.info(headers.toString());
			FileWriter writer = new FileWriter(filePath, true);
			gson.toJson(headers, writer);
			writer.write("\n");
			writer.flush();
			writer.close();
			log.info("Archivo guardado con éxito");
		} catch (JsonIOException e) {
			e.printStackTrace();
			log.error("Error al escribir archivo json: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Error al escribir archivo json: " + e.getMessage());
		}

	}

	//Seg Min Hor DiaMes Mes DiaSemana
	//0 5 * * * *  ejecuta cada hora en el quinto minuto
	@Scheduled(cron = "${expression.cron.job}")
	public void job() {
		log.info("Inicio tarea programada");
		Gson gson = new Gson();
		try {
			/*----------------------------------------------*/
			log.info("Eliminación de datos anteriores de ElasticSearch");
			agentService.deleteAll();
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(Paths.get(filePath));
			lines.forEach(line -> {
				Agent agent = new Agent();
				HttpHeaders header = gson.fromJson(line, HttpHeaders.class);
				agent = this.getUserAgent(header.get("user-agent").get(0));
				agentService.save(agent);

			});
			log.info("Datos cargados a ElasticSerch con éxito");
			/*----------------------------------------------*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Fin tarea programada");
	}

	public Agent getUserAgent(String userAgent) {
		String osType = "Unknown";
		String osVersion = "Unknown";
		String browserType = "Unknown";
		String browserVersion = "Unknown";
		String deviceType = "Unknown";
		Agent agent = new Agent();
		try {
			// Operating System and Version
			if (userAgent.indexOf("Windows NT") >= 0) {
				osType = "Windows";
				osVersion = userAgent.substring(userAgent.indexOf("Windows NT ")+11, userAgent.indexOf(";"));

			} else if (userAgent.indexOf("Mac OS") >= 0) {
				osType = "Mac";
				osVersion = userAgent.substring(userAgent.indexOf("Mac OS ")+7, userAgent.indexOf(")"));

				if(userAgent.indexOf("iPhone") >= 0) {
					deviceType = "iPhone";
				} else if(userAgent.indexOf("iPad") >= 0) {
					deviceType = "iPad";
				}

			} else if (userAgent.indexOf("X11") >= 0) {
				osType = "Unix";
				osVersion = "Unknown";

			} else if (userAgent.indexOf("Android") >= 0) {
				osType = "Android";
				osVersion = userAgent.substring(userAgent.indexOf("Android ")+8, userAgent.indexOf("Android ")+13);
			}
			
			// Browser type and Version
			if (userAgent.contains("Edge/")) {
				browserType = "Edge";
				browserVersion = userAgent.substring(userAgent.indexOf("Edge")).split("/")[1];

			} else if (userAgent.contains("Safari/") && userAgent.contains("Version/")) {
				browserType = "Safari";
				browserVersion = userAgent.substring(userAgent.indexOf("Version/")+8).split(" ")[0];

			} else if (userAgent.contains("OPR/") || userAgent.contains("Opera/")) {
				browserType = "Opera";
				browserVersion = userAgent.substring(userAgent.indexOf("OPR")).split("/")[1];

			} else if (userAgent.contains("Chrome/")) {
				browserType = "Chrome"; 
				browserVersion = userAgent.substring(userAgent.indexOf("Chrome")).split("/")[1];
				browserVersion = browserVersion.split(" ")[0];

			} else if (userAgent.contains("Firefox/")) {
				browserType = "Firefox"; 
				browserVersion = userAgent.substring(userAgent.indexOf("Firefox")).split("/")[1];
			} 
			
			agent.setOsType(osType);
			agent.setOsVersion(osVersion);
			agent.setBrowserType(browserType);
			agent.setBrowserVersion(browserVersion);
			agent.setDeviceType(deviceType);
		} catch (Exception e) {
			log.error("Error: " + e.getMessage());
		}
		
		return agent;
	}

}
