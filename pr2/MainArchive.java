import java.io.InputStream;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainArchive
{
	public static void main(String[] args)
	{
		FileInputStream fileInputStream = null;
		File file = new File("/home/nahuel/Documents/distribuidos/pr2/download.jpeg");
		byte[] fileArray = new byte[(int) file.length()];
		byte[] fileArray2 = new byte[(int) file.length()+20];

		try {
			// Con este código se obtienen los bytes del archivo.
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileArray);
			fileInputStream.close();
			/*String s = new String(fileArray2, StandardCharsets.UTF_8);
			System.out.println(s);
			*/
			byte[] bytes = "holacomoes".getBytes();
			for(int i =0;i<fileArray.length;i++){
				fileArray2[i]=fileArray[i];
			}

			int j=0;
			for(int i =fileArray.length;i<fileArray.length+10;i++){
				fileArray2[i]=bytes[j];
				j++;
			}
			// Con este código se agregan los bytes al archivo.
			FileOutputStream fileOuputStream = new FileOutputStream("/home/nahuel/Documents/distribuidos/pr2/download2.jpeg");
			fileOuputStream.write(fileArray2);
			fileOuputStream.close();
		} catch (Exception e) {
			System.out.println("hubo un error");
			System.out.println(e);
		}
	}
}