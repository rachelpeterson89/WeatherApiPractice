package com.rpete.weatherapi.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class WeatherController {
	
	private static HttpURLConnection connection;
	BufferedReader reader;
	String line;
	StringBuffer responseContent = new StringBuffer();
	
	@GetMapping("/weather")
	public String getWeather(Model model) {
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Minneapolis&units=imperial&appid=36d90c006e27a50453c14229ce3ab55e");
			connection = (HttpURLConnection) url.openConnection();
			
			// Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			// Response from server
			int status = connection.getResponseCode();
			// Testing status, 200 means connected to server!
//			System.out.println(status);
			
			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			System.out.println(responseContent.toString());
			
			JSONObject jsonObject = new JSONObject(responseContent.toString());
			System.out.println("Result after Reading JSON Response:");
			System.out.println("City- " + jsonObject.getString("name"));
			String city = jsonObject.getString("name");
			model.addAttribute("city", city);
			
			JSONArray jsonArray = jsonObject.getJSONArray("weather");
//			System.out.println(jsonArray);
//			System.out.println(jsonArray.length());
			
			JSONObject jsonObject2 = jsonArray.getJSONObject(0);
//			System.out.println(jsonObject2);
			System.out.println("Description: " + jsonObject2.getString("description"));
			String desc = jsonObject2.getString("description");
			model.addAttribute("desc", desc);
			
			System.out.println("Description 2: " + jsonObject2.getString("main"));
			System.out.println("Icon: " + jsonObject2.getString("icon"));
			String icon = jsonObject2.getString("icon");
			model.addAttribute("icon", icon);
			
			JSONObject jsonObject3 = jsonObject.getJSONObject("main");
//			System.out.println(jsonObject3);
			System.out.println("Temperature: " + jsonObject3.getFloat("temp"));
			float floatTemp = jsonObject3.getFloat("temp");
			int currentTemp = (int) Math.floor(floatTemp);
			model.addAttribute("currentTemp", currentTemp);

			return "index.jsp";
		} catch (MalformedURLException e){
			e.printStackTrace();
			return "Oops, there was an issue...";
		} catch (IOException e) {
			e.printStackTrace();
			return "Oops, there was an issue...";
		} 
	}
	
//	@Autowired
//	private RestTemplate restTemplate;
//	
	
//	@GetMapping("/weather")
//	public Object getWeather() {
////		String proxy = "https://cors-anywhere.herokuapp.com/";
//		String url = "http://api.openweathermap.org/data/2.5/weather?q=Minneapolis&units=imperial&appid=36d90c006e27a50453c14229ce3ab55e";
////		URL obj = new URL(url);
////		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
////		con.setRequestMethod("GET");
////		con.setRequestProperty("User-Agent", "Mozilla/5.0");
////		BufferedReader in = new BufferedReader(
////				new InputStream)
//		ResponseEntity<Object> object = restTemplate.getForEntity(url, Object.class);
////		
////		for (int i = 0; i < ((CharSequence) object).length(); i++) {
////			System.out.println(i);
////		}
////		String jsonString = restTemplate.getForEntity(url, String);
////		System.out.println(object.toString());
////		
////		JSONObject jsonObject = new JSONObject(object.toString());
////		System.out.println(jsonObject);
//		return object;
//	}
	
//	@RequestMapping("")
//	public String index() {
//		return "index.jsp";
//	}
}
