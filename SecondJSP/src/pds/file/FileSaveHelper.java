package pds.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 *    지정한 데이터를 특정 디렉토리에 저장해주는 기능을 제공하는 클래스
 * - 현재 시간과 임의의 숫자를 이용하여 파일명을 생성하고 InputStream으로부터 데이터를 읽어와 파일에 씀
 */
public class FileSaveHelper {
	private static Random random = new Random();

	public static String save(String directory, InputStream is)
			throws IOException {
		
		long currentTime = System.currentTimeMillis();
		int randomValue = random.nextInt(50);
		String fileName = Long.toString(currentTime) + "_"	+ Integer.toString(randomValue);

		File file = new File(directory, fileName);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			byte[] data = new byte[8096];
			int len = -1;
			while ((len = is.read(data)) != -1) {
				os.write(data, 0, len);
			}
		} finally {
			if (os != null)	try {	os.close();	} catch (IOException e) {	}
		}
		return file.getName();
			
	}
}
