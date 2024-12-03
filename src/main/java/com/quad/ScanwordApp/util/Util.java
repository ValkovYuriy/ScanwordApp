package com.quad.ScanwordApp.util;

import com.quad.ScanwordApp.model.Dictionary;
import com.quad.ScanwordApp.model.json.DictionaryData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Util {

    private static JdbcTemplate jdbcTemplate;
    private static SessionFactory sessionFactory;


	private static final String DB_URL = "jdbc:postgresql://localhost:5432/ScanwordApp";

	private static final String USER = "ydek";

	private static final String PASS = "remote";

    public static void connectWithJdbcTemplate(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASS);
		jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void connectWithHibernate(){
        Configuration config = new Configuration();
        config.addAnnotatedClass(Dictionary.class);
        config.configure();
        sessionFactory = config.buildSessionFactory();
    }

    public static void insertDataUsingHibernate(String dict) throws IOException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<DictionaryData> dictionaryData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/dictionaries/clean_dictionaries/" + dict + ".dict")));
        while(br.ready()){
            String[] line = br.readLine().split(" ",2);
            dictionaryData.add(new DictionaryData(line[0], line[1]));
        }
        Dictionary dictionary = Dictionary.builder()
                .name(dict)
                .dictionaryData(dictionaryData)
                .build();
        session.save(dictionary);
        session.getTransaction().commit();
    }

    public static void insertDataUsingJdbcTemplate(){

//        Path directoryPath = Paths.get("src/main/resources/images");
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
//
//
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
    }

    public static void clearingDictionaries(){
        Path dirtyDictionaries = Paths.get("src/main/resources/dictionaries/dirty_dictionaries");
        Path cleanDictionaries = Paths.get("src/main/resources/dictionaries/clean_dictionaries");

        try {
            Files.list(dirtyDictionaries).forEach(dirtyDictionary ->{
                Path cleanDictionary = cleanDictionaries.resolve(dirtyDictionaries.relativize(dirtyDictionary));
                try(
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/dictionaries/dirty_dictionaries/" + dirtyDictionary.getFileName())));
                        FileWriter fw = new FileWriter("src/main/resources/dictionaries/clean_dictionaries/" + cleanDictionary.getFileName());
                ){
                    HashSet<String> uniqueLines = new HashSet<>();
                    while(br.ready()){
                        String[] line = br.readLine().split(" ",2);
                        if(uniqueLines.add(line[0])){
                            fw.write(line[0] + " " + line[1].trim() + "\n");
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
