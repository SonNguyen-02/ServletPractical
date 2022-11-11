package com.mct.practical.practical3.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.UUID;

public class UploadManager {

    private static final long MAX_FILE_SIZE = 2 * FileUtils.ONE_MB;

    public static boolean upload(@NotNull FileItem file, @NotNull String path, String name) {
        if (validateFile(file) != null) {
            return false;
        }
        try {
            String separator = path.endsWith(File.separator) ? "" : File.separator;
            Path uploadPath = Paths.get(path + separator + name);
            Files.createDirectories(Paths.get(path));
            Files.deleteIfExists(uploadPath);
            file.write(uploadPath.toFile());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void delete(@NotNull String path, String name) {
        try {
            String separator = path.endsWith(File.separator) ? "" : File.separator;
            Path uploadPath = Paths.get(path + separator + name);
            Files.deleteIfExists(uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public static String validateFile(FileItem file) {
        String error;
        if (file == null) {
            return "Please choose image!";
        }
        if ((error = isValidFormat(file)) != null) {
            return error;
        }
        if ((error = isValidSize(file)) != null) {
            return error;
        }
        return null;
    }

    @Nullable
    private static String isValidFormat(@NotNull FileItem file) {
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return "File is not an image!";
        }
        if (!Arrays.asList("png", "jpeg", "jpg").contains(contentType.split("/")[1])) {
            return "The file extensions must in [png, jpeg, jpg]!";
        }
        return null;
    }

    @Nullable
    private static String isValidSize(@NotNull FileItem file) {
        if (file.getSize() < 0) {
            return "The file size invalid";
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            return "The file size invalid ("
                    + FileUtils.byteCountToDisplaySize(file.getSize())
                    + " > "
                    + FileUtils.byteCountToDisplaySize(MAX_FILE_SIZE)
                    + ")";
        }
        return null;
    }

    @NotNull
    public static String generateFileName() {
        String ext = ".jpg";
        String name = (LocalDate.now() +
                "_" + LocalTime.now().get(ChronoField.MILLI_OF_SECOND) +
                "_" + UUID.randomUUID())
                .replaceAll("-", "_").substring(0, 32);
        return name + ext;
    }

}
