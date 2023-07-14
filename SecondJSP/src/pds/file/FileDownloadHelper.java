package pds.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileDownloadHelper {

	public static void copy(String filePath, OutputStream os)	throws IOException {
		FileInputStream is = null;
		try {

			// 실제 경로의 파일을 읽어서 클라이언트로 보냄
			is = new FileInputStream("C:\\workspaces\\JSP\\SecondJSP\\WebContent\\09_upload_class\\uploadData\\"+filePath);
			byte[] data = new byte[8096];
			int len = -1;
			while ((len = is.read(data)) != -1) {
				os.write(data, 0, len);
			}
		} finally {
			if (is != null)		try {	is.close();} catch (IOException e) {		}
		}
	}
}
