package at.tewan.mcide.util;

import java.io.File;
import java.nio.file.Files;

public class FileUtil {

    private static class FileException extends RuntimeException {

        String message;

        FileException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }

    public static File constructDirectory(String... directories) {

        String returnedFilePath = "";

        for(String dir : directories) {
            returnedFilePath = returnedFilePath.concat(dir + System.getProperty("file.separator"));
        }

        File returnedFile = new File(returnedFilePath);

        if(!returnedFile.exists()) {
            returnedFile.mkdir();
        }

        if(returnedFile.isFile()) {
            throw new FileException("Directory '" + returnedFilePath + "' is a File!");
        }

        return returnedFile;
    }
}
