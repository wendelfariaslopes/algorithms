package programs.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ServiceUtil {

	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = GoogleAuthorizeUtil.authorize(GoogleAuthorizeUtil.SHEET);
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
				credential).setApplicationName(APPLICATION_NAME).build();
	}

	public static Drive getDriveService() throws IOException, GeneralSecurityException {
		Credential credential = GoogleAuthorizeUtil.authorize(GoogleAuthorizeUtil.DRIVE);
		return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
				credential).setApplicationName(APPLICATION_NAME).build();
	}

}