package lk.notes.app.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class NoteSaveDto {

    private String description;
    private String title;
    private MultipartFile image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
