import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HW16_CutAndMergeRingtone {
    final static int BUFFER_SIZE_RINGTONE1 = 30000; //bytes
    final static int BUFFER_SIZE_RINGTONE2 = 50000; //bytes
    public static void main(String[] args) {
        try {
            FileInputStream ringtone1 = new FileInputStream("ringtone1.mp3");
            FileInputStream ringtone2 = new FileInputStream("ringtone2.mp3");
            FileOutputStream finalRingtone = new FileOutputStream("finalRingtone.mp3");
            byte[] buffer1 = new byte[BUFFER_SIZE_RINGTONE1];
            ringtone1.read(buffer1);
            ringtone1.close();
            
            byte[] buffer2 = new byte[BUFFER_SIZE_RINGTONE2];
            ringtone2.read(buffer2);
            ringtone2.close();

            finalRingtone.write(buffer1);
            finalRingtone.write(buffer2);
            finalRingtone.close(); //a 4sec ringtone
            System.out.println("Ringtones cut and merged successfully!");
            // Observation: Since the finalRingtone was opened in music player, it was not accessable
            // java.io.FileNotFoundException: finalRingtone.mp3 (The process cannot access the file because it is being used by another process)
            // That's why we need to close the file/stream.
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
