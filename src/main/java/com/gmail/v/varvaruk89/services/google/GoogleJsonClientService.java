package com.gmail.v.varvaruk89.services.google;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface GoogleJsonClientService {
    Credential getCredential() throws IOException;
    Drive getDriveService() throws IOException;
    Sheets getSheetsService() throws IOException;
    Map<String,String> getSpreadsheetsAll() throws IOException;
    List<Student> getBySpreadsheetsId(String spreadsheetId) throws IOException;
    List<Group> synchronizationGroupOfGOOGLE() throws IOException;
}