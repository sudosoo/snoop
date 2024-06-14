package com.api.pladder.domain.entity.image.enums;


import lombok.Getter;

@Getter
public enum FileExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif"),
    PDF("pdf"),
    M4A("m4a"),
    MP3("mp3");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public static FileExtension extractExtension(String ext) {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension.getExtension().equalsIgnoreCase(ext)) {
                return fileExtension;
            }
        }
        return null;
    }

    public static boolean hasExtension(String ext) {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension.getExtension().equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }


}
