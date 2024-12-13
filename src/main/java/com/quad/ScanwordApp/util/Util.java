package com.quad.ScanwordApp.util;

import com.quad.ScanwordApp.model.Dictionary;
import com.quad.ScanwordApp.model.Scanword;
import com.quad.ScanwordApp.model.json.DictionaryData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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
        config.addAnnotatedClass(Scanword.class);
        config.configure();
        sessionFactory = config.buildSessionFactory();
    }

    public static void addDataWithHibernate(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Dictionary dictionary = session.get(Dictionary.class,UUID.fromString("c25edb2e-e3cb-4695-b282-8c262ed32232"));
//        Scanword scanword = Scanword.builder()
//                .name("Тест")
//                .width(9)
//                .height(8)
//                .preview(new byte[10])
//                .dictionary(dictionary)
//                .isCreated(true)
//                .numberOfHints(1)
//                .content(List.of
//                        (Cell.builder()
//                                .row(0)
//                                .col(0)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("РАЗЛОМ")
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(1)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ОЗОРСТВО")
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(2)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(3)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("КОНКЛАВ")
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(4)
//                                .isTask(false)
//                                .letter('К')
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(5)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("МЕЛОМАН")
//                        .build(),
//                        Cell.builder()
//                                .row(0)
//                                .col(6)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                         Cell.builder()
//                                .row(0)
//                                .col(7)
//                                .isTask(false)
//                                .letter('П')
//                        .build(),
//                         Cell.builder()
//                                .row(0)
//                                .col(8)
//                                .isTask(false)
//                                .letter('Т')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(0)
//                                .isTask(false)
//                                .letter('Р')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(1)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(2)
//                                .isTask(false)
//                                .letter('З')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(3)
//                                .isTask(false)
//                                .letter('Л')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(4)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(5)
//                                .isTask(false)
//                                .letter('М')
//                        .build(),
//                        Cell.builder()
//                                .row(1)
//                                .col(6)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ОПТ")
//                        .build(),
//                         Cell.builder()
//                                .row(1)
//                                .col(7)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                         Cell.builder()
//                                .row(1)
//                                .col(8)
//                                .isTask(true)
//                                 .taskType(TaskType.IMAGE)
//                                 .taskId(UUID.fromString("c30fab28-75b3-44d1-a926-26d87ffd65d3"))
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(0)
//                                .isTask(false)
//                                .letter('Е')
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(1)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("РЕГАТА")
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(2)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(3)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("НЕДРА")
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(4)
//                                .isTask(false)
//                                .letter('Н')
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(5)
//                                .isTask(false)
//                                .letter('Е')
//                        .build(),
//                        Cell.builder()
//                                .row(2)
//                                .col(6)
//                                .isTask(false)
//                                .letter('Д')
//                        .build(),
//                         Cell.builder()
//                                .row(2)
//                                .col(7)
//                                .isTask(false)
//                                .letter('Р')
//                        .build(),
//                         Cell.builder()
//                                .row(2)
//                                .col(8)
//                                .isTask(false)
//                                 .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(0)
//                                .isTask(false)
//                                .letter('Г')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(1)
//                                .isTask(false)
//                                .letter('Е')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(2)
//                                .isTask(false)
//                                .letter('Р')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(3)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(4)
//                                .isTask(false)
//                                .letter('К')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(5)
//                                .isTask(false)
//                                .letter('Л')
//                        .build(),
//                        Cell.builder()
//                                .row(3)
//                                .col(6)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("РИЗА")
//                        .build(),
//                         Cell.builder()
//                                .row(3)
//                                .col(7)
//                                .isTask(false)
//                                .letter('Ф')
//                        .build(),
//                         Cell.builder()
//                                .row(3)
//                                .col(8)
//                                 .isTask(true)
//                                 .taskType(TaskType.MELODY)
//                                 .taskId(UUID.fromString("c523bc2a-83c4-4d0d-8c19-52b07e93d010"))
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(0)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(1)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ГЕРАКЛ")
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(2)
//                                .isTask(false)
//                                .letter('С')
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(3)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ЛОРЕН")
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(4)
//                                .isTask(false)
//                                .letter('Л')
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(5)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(4)
//                                .col(6)
//                                .isTask(false)
//                                .letter('Р')
//                        .build(),
//                         Cell.builder()
//                                .row(4)
//                                .col(7)
//                                .isTask(false)
//                                .letter('Е')
//                        .build(),
//                         Cell.builder()
//                                .row(4)
//                                .col(8)
//                                 .isTask(false)
//                                 .letter('Н')
//                        .build(),
//                         Cell.builder()
//                                .row(5)
//                                .col(0)
//                                .isTask(false)
//                                .letter('Т')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(1)
//                                .isTask(false)
//                                .letter('Е')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(2)
//                                .isTask(false)
//                                .letter('Т')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(3)
//                                .isTask(false)
//                                .letter('Р')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(4)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(5)
//                                .isTask(false)
//                                .letter('М')
//                        .build(),
//                        Cell.builder()
//                                .row(5)
//                                .col(6)
//                                .isTask(false)
//                                .letter('И')
//                        .build(),
//                         Cell.builder()
//                                .row(5)
//                                .col(7)
//                                .isTask(false)
//                                .letter('Н')
//                        .build(),
//                         Cell.builder()
//                                .row(5)
//                                .col(8)
//                                 .isTask(false)
//                                 .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(0)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(1)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ТЕТРАМИНО")
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(2)
//                                .isTask(false)
//                                .letter('В')
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(3)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("ВАЗОН")
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(4)
//                                .isTask(false)
//                                .letter('В')
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(5)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                        Cell.builder()
//                                .row(6)
//                                .col(6)
//                                .isTask(false)
//                                .letter('З')
//                        .build(),
//                         Cell.builder()
//                                .row(6)
//                                .col(7)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                         Cell.builder()
//                                .row(6)
//                                .col(8)
//                                 .isTask(false)
//                                 .letter('Н')
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(0)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("КОН")
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(1)
//                                .isTask(false)
//                                .letter('К')
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(2)
//                                .isTask(false)
//                                .letter('О')
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(3)
//                                .isTask(false)
//                                .letter('Н')
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(4)
//                                .isTask(true)
//                                .taskType(TaskType.VERBAL)
//                                .word("НАНА")
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(5)
//                                .isTask(false)
//                                .letter('Н')
//                        .build(),
//                        Cell.builder()
//                                .row(7)
//                                .col(6)
//                                .isTask(false)
//                                .letter('А')
//                        .build(),
//                         Cell.builder()
//                                .row(7)
//                                .col(7)
//                                .isTask(false)
//                                .letter('Н')
//                        .build(),
//                         Cell.builder()
//                                .row(7)
//                                .col(8)
//                                 .isTask(false)
//                                 .letter('А')
//                        .build()
//                        ))
//                .build();



        // Сохраняем объект в базе данных
        //session.save(scanword);

        // Подтверждаем транзакцию
        transaction.commit();
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

    public static void clearMainDictionary(){
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/dictionaries/Glavny.dict"),"windows-1251"));
                FileWriter fw = new FileWriter("src/main/resources/dictionaries/clean_dictionaries/Главный.dict");
        ){
            HashSet<String> uniqueLines = new HashSet<>();
            while(br.ready()){
                String[] line = br.readLine().split(" ",2);
                if(uniqueLines.add(line[0]) && line[0].length() <= 10){
                    fw.write(line[0] + " " + line[1].trim() + "\n");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
                        if(uniqueLines.add(line[0]) && line[0].length() <= 10){
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
