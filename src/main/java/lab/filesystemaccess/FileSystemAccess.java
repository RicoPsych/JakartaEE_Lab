package lab.filesystemaccess;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileSystemAccess{
    private final String PATH = "C:\\Users\\Hubert\\INFORMATYKA\\SEM7\\JakartaEE_Lab\\target\\avatars";


    public void writeAvatar(UUID id, InputStream avatar){
        try {
        Files.write(Path.of(PATH,id.toString()),avatar.readAllBytes());            
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeAvatar(UUID id, byte[] avatar){
        try {
        Files.write(Path.of(PATH,id.toString()),avatar);            
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }



    public byte[] getAvatar(UUID id){
        try {
            return Files.readAllBytes(Path.of(PATH,id.toString()));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void deleteAvatar(UUID id){
        try {
            Files.delete(Path.of(PATH,id.toString()));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}