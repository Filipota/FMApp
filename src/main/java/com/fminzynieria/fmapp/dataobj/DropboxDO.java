package com.fminzynieria.fmapp.dataobj;

import java.io.File;

public class DropboxDO {

    /**
     * File upload
     **/
    public static class Upload {
        private String filePath;
        private File file;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public File getMultipartFile() {
            return file;
        }

        public void setMultipartFile(File file) {
            this.file = file;
        }
    }

    /**
     *  
     * * Dropbox Controller Response
     *      
     **/
    public static class Response {
        private int statusCode;
        private String message;

        public Response(int statusCode, String message) {
            super();
            this.statusCode = statusCode;
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
