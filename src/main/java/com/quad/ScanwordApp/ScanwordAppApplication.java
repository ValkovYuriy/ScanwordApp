package com.quad.ScanwordApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScanwordAppApplication {


//	private static JdbcTemplate jdbcTemplate;
//
//	private static final String DB_URL = "jdbc:postgresql://localhost:5432/ScanwordApp";
//	private static final String USER = "ydek";
//	private static final String PASS = "remote";

	public static void main(String[] args) {

//		// Шаг 2: Подключаемся к базе данных
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl(DB_URL);
//		dataSource.setUsername(USER);
//		dataSource.setPassword(PASS);
//
//		jdbcTemplate = new JdbcTemplate(dataSource);
//
//		Path directoryPath = Paths.get("src/main/resources/images");
//		BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/dataForImages.txt"));
//		ArrayList<String> files = new ArrayList<>();
//		ArrayList<String[]> data = new ArrayList<>();
//		// Используем DirectoryStream для цикла по файлам
//		try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
//			for (Path entry : stream) {
//				if (Files.isRegularFile(entry)) {
//					files.add(entry.getFileName().toString());
//					data.add(bf.readLine().split(","));
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		byte[] fileContent;
//		// Шаг 1: Считываем JPG файл в массив байтов
//		for(int i = 0; i < files.size(); i++){
//			try {
//				fileContent = Files.readAllBytes(Paths.get("src/main/resources/images/" + files.get(i)));
//				String sql = "INSERT INTO image (image, question, answer) VALUES (?,?,?)";
//				jdbcTemplate.update(sql, fileContent,data.get(i)[0],data.get(i)[1]);
//				System.out.println("Супер");
//			} catch (IOException e) {
//				System.err.println("Error reading the file: " + e.getMessage());
//				return;
//			}
//		}


		// Шаг 3: Вставляем данные в базу данных

//		UUID id = UUID.fromString("bbd05bd4-07a8-401d-bcb0-6c2b38ca7d23");
//		String sql = "SELECT melody FROM melody WHERE id = ?";
//		byte[] fileContent = jdbcTemplate.queryForObject(sql,new Object[]{id}, byte[].class);
//		// Сохраняем массив байтов в файл
//		try (FileOutputStream fos = new FileOutputStream(outputPath)) {
//			fos.write(fileContent);
//			System.out.println("MP3 file successfully downloaded to: " + outputPath);
//		} catch (IOException e) {
//			System.err.println("Error writing the file: " + e.getMessage());
//		}


        SpringApplication.run(ScanwordAppApplication.class, args);
	}

}
