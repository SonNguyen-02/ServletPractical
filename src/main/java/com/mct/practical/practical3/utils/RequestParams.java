package com.mct.practical.practical3.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestParams {

    @NotNull
    public static RequestParams parseRequest(@NotNull HttpServletRequest req) {
        RequestParams requestParams = new RequestParams();
        try {
            //noinspection unchecked
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            items.stream().filter(Objects::nonNull).forEach(item -> {
                if (item.isFormField()) {
                    requestParams.regularFields.put(item.getFieldName(), item.getString());
                } else {
                    requestParams.fileFields.put(item.getFieldName(), item);
                }
            });
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return requestParams;
    }

    private final Map<String, String> regularFields;
    private final Map<String, FileItem> fileFields;

    public RequestParams() {
        regularFields = new HashMap<>();
        fileFields = new HashMap<>();
    }

    public String getParam(String key) {
        String val = regularFields.get(key);
        return val.isEmpty() ? null : val;
    }

    public FileItem getFileParam(String key) {
        return fileFields.get(key);
    }

    @Override
    public String toString() {
        return "regular {" + regularFields + "}\nfile {" + fileFields + "}";
    }
}
