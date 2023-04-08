package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import steps.Hooks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class ApiUtils {

    /**
     * If you need to get token before sending request you can use this method for passing token to request
     * @param env
     * @return
     */
    public static String getToken(String env) {
        return given().header("Cookie", ConfigReader.get("cookie",env))
                .queryParam("username","yourUsername")
                .queryParam("password","yourPassword")
                .when().post("https://tokenRequestUrl.com")
                .then().extract().response().asString();
    }

    public static Response sendRequest(RequestSpecification spec, String requestType, String endpoint) {
        switch (requestType.toUpperCase()) {
            case "POST":
                return spec.when().post(endpoint);
            case "GET":
                return spec.when().get(endpoint);
            case "DELETE":
                return spec.when().delete(endpoint);
            case "PUT":
                return spec.when().put(endpoint);
        }
        return spec.when().get(endpoint);
    }

    public static RequestSpecification setRequestAuthorization(String env) {
        return new RequestSpecBuilder().addHeader("Authorization", "Bearer " + getToken(env)).build();
    }


    public static String getTimeStamp() {
        return new SimpleDateFormat("HHmmss").format(new java.util.Date());
    }

    /**
     * In RestAssured we can use .log().all() method to print log to console
     * You can also modify that to get logs and print into a file.
     * It will print log for the request to 'scenarioLog.txt' file
     */
    public static void configRestAssuredLog() {
        try {
            PrintStream fileOutPutStream = new PrintStream("scenarioLog.txt");
            config = config().logConfig(new LogConfig().defaultStream(fileOutPutStream));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param message : part of mail body
     * @throws Exception
     */
    public static void sendingLogFileAndReportViaMail(String message) throws Exception {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        String message1 = "Log and Report from last test execution added at " + date + "\n\n" + "Results of last running tests:";
        String mailBody = message1 + "\n\n" + message;

        new Gmailer().sendMail("QA automation log " + date, mailBody,

                getLastModifiedFile("target", "log"),
                getLastModifiedFile("test-output"));
    }


    public static String getMessageForMail() {
        String message = "";
        for (String scenarioMessage : Hooks.scenarioMessages) {
            message += scenarioMessage + "\n";
        }
        return message;
    }

    /**
     * If you have more than one file with same extension this method will return the latest one
     * @param pathName
     * @param fileExtension
     * @return
     * @throws IOException
     */
    private static String getLastModifiedFile(String pathName, String... fileExtension) throws IOException {

        List<String> files;
        if (fileExtension.length > 0) {
            files = findFiles(Paths.get(pathName), fileExtension[0]);
        } else {
            files = findFiles(Paths.get(pathName));
        }

        long lastModified = 0;
        String lastFile = "";

        for (String s : files) {
            File file = new File(s);
            long l = file.lastModified();
            if (l > lastModified) {
                lastModified = l;
                lastFile = s;
            }
        }
        return lastFile;
    }

    /**
     * To list files for specified path and extension
     * @param path : directoryPath
     * @param fileExtension
     * @return
     * @throws IOException
     */
    private static List<String> findFiles(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public static List<String> findFiles(Path path)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .collect(Collectors.toList());
        }
        return result;
    }

    /**
     * You can change property value in a properties file
     * @param key : propertyKey
     * @param newValue
     */
    public static void changeEndpointProperty(String key, String newValue) {
        String path = "src/main/resources/endpoints.properties";
        try {
            FileInputStream in = new FileInputStream(path);
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(path);
            props.setProperty(key, newValue);
            props.store(out, null);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String fileRead() {
        String file = null;
        try {
            File myObj = new File("scenarioLog.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                file += data + "\n";
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

}
