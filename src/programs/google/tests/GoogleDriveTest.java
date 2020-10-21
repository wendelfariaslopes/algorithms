package programs.google.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import programs.google.DriveServiceUtil;
import programs.google.ServiceUtil;

public class GoogleDriveTest {

	public static void main(String[] args) throws IOException, GeneralSecurityException {

		 createFolder();

		//createFileInFolder();
		
		//movingFilesBetweenFolder();

	}

	public static void createFolder() throws IOException, GeneralSecurityException {

		Drive driveService = ServiceUtil.getDriveService();

		File fileMetadata = new File();
		fileMetadata.setName("Invoices");
		fileMetadata.setMimeType("application/vnd.google-apps.folder");

		File file = driveService.files().create(fileMetadata).setFields("id").execute();
		System.out.println("Folder ID: " + file.getId());

	}

	public static void listFiles() throws IOException, GeneralSecurityException {

		Drive driveService = DriveServiceUtil.getDriveService();

		// Print the names and IDs for up to 10 files.
		FileList result = driveService.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)")
				.execute();
		List<File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf("%s (%s)\n", file.getName(), file.getId());
			}
		}
	}

	public static void createFileInFolder() throws IOException, GeneralSecurityException {

		Drive driveService = DriveServiceUtil.getDriveService();

		String folderId = "1db27CVXPTQZZSbFnNHByJ2B_3gJldTxh";
		File fileMetadata = new File();
		fileMetadata.setName("photo.jpg");
		fileMetadata.setParents(Collections.singletonList(folderId));
		
		java.io.File filePath = new java.io.File(
				"C:\\Users\\Utilisateur\\git\\algorithms\\src\\algorithms\\ai\\ml\\image\\enrico-enquadrado-green.jpg");
		FileContent mediaContent = new FileContent("image/jpeg", filePath);
		File file = driveService.files().create(fileMetadata, mediaContent).setFields("id, parents").execute();
		System.out.println("File ID: " + file.getId());

	}

	public static void movingFilesBetweenFolder() throws IOException, GeneralSecurityException {

		Drive driveService = DriveServiceUtil.getDriveService();
		
		String fileId = "1i-N_IqsESAsobTs4O0fwn1YjAvx_loJw";
		String folderId = "1sgWSyD1qEbun-wTwqctWVki9htCQbhWW";
		// Retrieve the existing parents to remove
		File file = driveService.files().get(fileId)
		    .setFields("parents")
		    .execute();
		StringBuilder previousParents = new StringBuilder();
		for (String parent : file.getParents()) {
		  previousParents.append(parent);
		  previousParents.append(',');
		}
		// Move the file to the new folder
		file = driveService.files().update(fileId, null)
		    .setAddParents(folderId)
		    .setRemoveParents(previousParents.toString())
		    .setFields("id, parents")
		    .execute();

	}
	
	public static void download() throws IOException, GeneralSecurityException {

		Drive driveService = DriveServiceUtil.getDriveService();
		
		String fileId = "1i-N_IqsESAsobTs4O0fwn1YjAvx_loJw";
		OutputStream outputStream = new ByteArrayOutputStream();
		driveService.files().get(fileId)
		    .executeMediaAndDownloadTo(outputStream);
		
	
	}

}
