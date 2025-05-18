import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HW15_CopyAndCreateImage {
    final static int BUFFER_SIZE_1KB = 1024; //bytes
    final static int BUFFER_SIZE_8KB = 8192; //bytes
    public static void main(String[] args) {
        copyImageUsingFileInputOutputStreamAndLoop(); //each byte at a time
        copyImageUsingFileInputOutputStreamAndLoopWithBuffer(BUFFER_SIZE_1KB);
        copyImageUsingFileInputOutputStreamAndLoopWithBuffer(BUFFER_SIZE_8KB); // little overhead which BOS does efficiently
        copyImageUsingBufferedInputOutputStream();
    }

    private static void copyImageUsingFileInputOutputStreamAndLoop() {
        long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream("image.jpg");
            FileOutputStream fos = new FileOutputStream("image_copy1.jpg");
            int byteRead = 0;
            while((byteRead = fis.read()) != -1) {
                fos.write(byteRead);
            }
            long end = System.currentTimeMillis();
            System.out.println("FileI/OStream using loop (no buffer):" + (end - start) + " ms");
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void copyImageUsingFileInputOutputStreamAndLoopWithBuffer(final int BUFFER_SIZE) {
        long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream("image.jpg");
            FileOutputStream fos = new FileOutputStream(BUFFER_SIZE + "image_copy2.jpg");
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            long end = System.currentTimeMillis();
            System.out.println("FileI/OStream using loop and buffer(" + BUFFER_SIZE + "KB):" + (end - start) + " ms");
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void copyImageUsingBufferedInputOutputStream() {
        long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream("image.jpg");
            BufferedInputStream bis = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream("image_copy3.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[BUFFER_SIZE_8KB]; // using DEFAULT buffer as used in BufferedOutputStream
            int bytesRead = 0;
            while ((bytesRead = bis.read(buffer, 0, BUFFER_SIZE_8KB)) != -1) { // no support for bis.read(buffer) <- no buffering in such case since it belongs to FilterInputStream
                bos.write(buffer, 0, bytesRead); // no support for bos.write(buffer) <- no buffering in such case since it belongs to FilterOutputStream
            }
            long end = System.currentTimeMillis();
            System.out.println("BufferedI/OStream using loop and buffer(" + BUFFER_SIZE_8KB + "KB):" + (end - start) + " ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Output:
 * -------
 * FileI/OStream using loop (no buffer):39045 ms
 * FileI/OStream using loop and buffer(1024KB):78 ms
 * FileI/OStream using loop and buffer(8192KB):23 ms
 * BufferedI/OStream using loop and buffer(8192KB):19 ms
 * 
 * Output2 (excluding using loop):
 * ------------------------------
 * FileI/OStream using loop and buffer(1024KB):70 ms
 * FileI/OStream using loop and buffer(8192KB):16 ms
 * BufferedI/OStream using loop and buffer(8192KB):15 ms
 */