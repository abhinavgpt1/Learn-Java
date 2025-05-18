import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HW17_DataInputStreamDataOutputStream {
    public static void main(String[] args) {
        int productId = 12345;
        String productName = "Asus ZenBook";
        double productPrice = 799.99;
        boolean productAvailable = true;
        int productId2 = 98765;
        String productName2 = "Dell Inspiron";
        double productPrice2 = 599.99;
        boolean productAvailable2 = false;

        try {
            FileOutputStream fos = new FileOutputStream("sampleLaptopDetails.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            // laptop 1 details
            dos.writeInt(productId);
            dos.writeUTF(productName);
            dos.writeDouble(productPrice);
            dos.writeBoolean(productAvailable);

            // laptop 2 details
            dos.writeInt(productId2);
            dos.writeUTF(productName2);
            dos.writeDouble(productPrice2);
            dos.writeBoolean(productAvailable2);
            dos.close(); // close the output stream

            FileInputStream fis = new FileInputStream("sampleLaptopDetails.txt");
            DataInputStream dis = new DataInputStream(fis);
            System.out.println("available bytes for reading: " + dis.available()); //55 bytes
            // System.out.println(dis.readAllBytes().length); //55 bytes.
            // Post 55 bytes, and even an attempt to read more is not possible, which
            // otherwise will result in EOFException.

            // Loop to read all the data
            try {
                while (true) {
                    int id = dis.readInt();
                    System.out.println("productId:" + id);
                    String name = dis.readUTF();
                    System.out.println("productName:" + name);
                    double price = dis.readDouble();
                    System.out.println("productPrice:" + price);
                    boolean available = dis.readBoolean();
                    System.out.println("productAvailable:" + available);
                    System.out.println("------------------------------");
                }
            } catch (EOFException e) {
                dis.close();
            } finally {
                System.out.println("This close() is needed if while loop is written as \"while (dis.available() > 0)\" since, we need to close the stream anyways.");
                dis.close();
            }

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
 * available bytes for reading: 55
 * productId:12345
 * productName:Asus ZenBook
 * productPrice:799.99
 * productAvailable:true
 * ------------------------------
 * productId:98765
 * productName:Dell Inspiron
 * productPrice:599.99
 * productAvailable:false
 * ------------------------------
 * This close() is needed if while loop is written as "while (dis.available() > 0)" since, we need to close the stream anyways.
 */