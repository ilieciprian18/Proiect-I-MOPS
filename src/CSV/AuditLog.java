package CSV;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog {
    public static void AuditLogService(String filePath,String comanda) throws IOException {
        try{
           FileWriter file = new FileWriter(filePath,true);
           BufferedWriter buffwriter = new BufferedWriter(file);
           PrintWriter csvWriter = new PrintWriter(buffwriter);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);

            csvWriter.println(date+","+comanda);
            csvWriter.flush();
            csvWriter.close();
           // csvWriter.flush();
        }
        catch (FileNotFoundException exception){
            System.out.println("Nu exista fisierul de AuditLog");
        }
    }
}
