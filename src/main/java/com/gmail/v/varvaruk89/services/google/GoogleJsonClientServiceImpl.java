package com.gmail.v.varvaruk89.services.google;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class GoogleJsonClientServiceImpl implements GoogleJsonClientService {

    @Value("${application.name}")
    private String appName;

    @Value("${json.path}")
    public String jsonFile;

    @Value("${courses.names}")
    public String coursesNamesLine;

    /**
     * Directory to store user credentials for this application.
     */
    private static java.io.File dataStoreDir = new java.io.File(
            System.getProperty("user.home"), ".credentials/turboSmsMessenger");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory dataStoreFactory;

    /**
     * Global instance of the JSON factory.
     */
    private JsonFactory jacksonFactory =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport httpTransport;

    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY, SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            dataStoreFactory = new FileDataStoreFactory(dataStoreDir);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */

    public Set<String> getCoursesNames() {

        return new HashSet<>(Arrays.asList(coursesNamesLine.split(",")));
    }

    @Override
    public Credential getCredential() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("C:\\Java\\DIPLOM\\restgoogle\\test.json");

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(jacksonFactory, new InputStreamReader(fileInputStream));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        httpTransport, jacksonFactory, clientSecrets, SCOPES)
                        .setDataStoreFactory(dataStoreFactory)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + dataStoreDir.getAbsolutePath());
        return credential;


    }

    @Override
    public Drive getDriveService() throws IOException {
        Credential credential = getCredential();
        return new Drive.Builder(
                credential.getTransport(), credential.getJsonFactory(), credential)
                .setApplicationName(appName)
                .build();
    }

    @Override
    public Sheets getSheetsService() throws IOException {
        Credential credential = getCredential();
        assert credential != null;
        Sheets.Builder builder = new Sheets.Builder(credential.getTransport(), credential.getJsonFactory(), credential);
        return builder
                .setApplicationName(appName)
                .build();
    }

    @Override
    public Map<String, String> getSpreadsheetsAll() throws IOException {
        Map<String, String> map = new HashMap<>();

        FileList result = getDriveService().files().list()
                .setQ("mimeType='application/vnd.google-apps.spreadsheet'")
                .setFields("nextPageToken, files(id, name)")
                .execute();
        assert result != null;
        for (com.google.api.services.drive.model.File file : result.getFiles()) {
            for (String coursesName : getCoursesNames()) {
                if (file.getName().toLowerCase().startsWith(coursesName.toLowerCase())) {

                    map.put(file.getId(), file.getName());
                    //  System.out.printf("Found file: %s (%s)\n", file.getName(), file.getId());


                }


            }
        }
        return map;
    }


    @Override
    public List<Student> getBySpreadsheetsId(String spreadsheetId) throws IOException {

        List<Student> studentList = new ArrayList<>();
        Sheets service = getSheetsService();
        String range = "A:F";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();

        for (List row : values) {
            if (row != null & row.size() > 1) {
                Student student = new Student();
                if (row.size() > 5) {
                    student.setNotation(row.get(5).toString());
                }
                student.setName(row.get(0).toString());
                student.setPhone(row.get(1).toString());
                student.setSum(row.get(2).toString());
                student.setComment(row.get(3).toString());

                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public List<Group> synchronizationGroupOfGOOGLE() throws IOException {
        Map<String, String> allSpreadsheet = getSpreadsheetsAll();
        List<Group> groupList = new ArrayList<>();
        for (Map.Entry m : allSpreadsheet.entrySet()) {
            Group group = new Group();
            group.setName(m.getValue().toString());
            List<Student> studentList = getBySpreadsheetsId(m.getKey().toString());
            group.setStudents(studentList);
            groupList.add(group);
        }


        return groupList;
    }


}
