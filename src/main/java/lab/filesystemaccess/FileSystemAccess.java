package lab.filesystemaccess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FileSystemAccess{
    private String PATH;//= "D:\\Informatyka\\SEM7\\JavaEE\\git-lab1\\JakartaEE_Lab\\target\\avatars";

    @Inject
    public FileSystemAccess(){
        Properties appProps = new Properties();
        InputStream is = this.getClass().getResourceAsStream("../filesystem.properties");
        try {
            appProps.load(is);   
            PATH = appProps.getProperty("path");
        } catch (Exception e) {
            PATH = "";
            //throw new IOException("Could not load configuration for filesystemaccess");
        }
    }

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



    public Optional<byte[]> getAvatar(UUID id){
        try {
            return Optional.of(Files.readAllBytes(Path.of(PATH,id.toString())));
        } catch (IOException ex) {
            return Optional.empty();
//            throw new IllegalStateException(ex);
        }
    }

    public void deleteAvatar(UUID id){
        System.out.println(PATH);
        try {
            Files.delete(Path.of(PATH,id.toString()));
        } catch (IOException ex) {
            //throw new IllegalStateException(ex);
        }
    }
}