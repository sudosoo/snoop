package com.api.pladder.domain.entity.image.enums;


import lombok.Getter;

@Getter
public enum ImageExtension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif"),
    PDF("pdf");

    private final String extension;

    ImageExtension(String extension) {
        this.extension = extension;
    }

    public static ImageExtension extractExtension(String ext) {
        for (ImageExtension imageExtension : ImageExtension.values()) {
            if (imageExtension.getExtension().equalsIgnoreCase(ext)) {
                return imageExtension;
            }
        }
        return null;
    }

    public static boolean hasExtension(String ext) {
        for (ImageExtension imageExtension : ImageExtension.values()) {
            if (imageExtension.getExtension().equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }
}
