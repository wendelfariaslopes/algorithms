package programs.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleAuthorizeUtil {
	
	public static final String DRIVE = "drive";
	public static final String SHEET = "sheet";
	
	private static final String TOKENS_DIRECTORY_PATH = "C:\\Users\\Utilisateur\\git\\algorithms\\src\\programs\\google\\";
	
    public static Credential authorize() throws IOException, GeneralSecurityException {
        
        String fileCredentials = "C:\\Users\\Utilisateur\\git\\algorithms\\src\\programs\\google\\tests\\credentials.json";
        
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileCredentials), StandardCharsets.UTF_8);
       
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), isr);

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), 
        		JacksonFactory.getDefaultInstance(), 
        		clientSecrets, 
        		scopes)
        		 .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        	//	.setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType("offline")
                .build();
        
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        
       // Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return credential;
    }
    
    public static Credential authorize(String api) throws IOException, GeneralSecurityException {
        
        String fileCredentials = "C:\\Users\\Utilisateur\\git\\algorithms\\src\\programs\\google\\"+api+"\\credentials_"+api+".json";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileCredentials), StandardCharsets.UTF_8);
       
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), isr);

        List<String> scopes = getScopes(api);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), 
        		JacksonFactory.getDefaultInstance(), 
        		clientSecrets, 
        		scopes)
        		 .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH + api)))
                .setAccessType("offline")
                .build();
        
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return credential;
    }
    
    private static List<String> getScopes(String api){
    	if(DRIVE.equals(api)) {
    		return Arrays.asList(DriveScopes.DRIVE);
    	} else if(SHEET.equals(api)) {
    		return Arrays.asList(SheetsScopes.SPREADSHEETS);
    	} else {
    		return Arrays.asList(SheetsScopes.SPREADSHEETS);
    	}
    }

}