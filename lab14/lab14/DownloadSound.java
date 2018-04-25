package lab14;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import java.net.URL;

public class DownloadSound {

    /* Pattern string used to match the class name. */
    static final String CLASS_PATTERN = "class[\\s]+([\\w]+).*\\{";

    /* Temporarily stores the file when first downloaded to this name. */
    static final String TMP_NAME = "GZ2TPEFHXMB4.txt";

    /** Downloads the code from URL and stores it into a file located at
     *  TMP_NAME.
     */
    public static void downloadCode(String url) {
        System.out.println(">> Downloading from " + url);
        try {
            URL remote = new URL(url);
            ReadableByteChannel channel = Channels.newChannel(remote.openStream());
            FileOutputStream out = new FileOutputStream(TMP_NAME);
            out.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            out.close();
            channel.close();
        } catch (IOException e) {
            System.out.println("Error connecting to the url: " + url);
            System.exit(1);
        }
    }

    /** Returns the code stored in file located at SOURCE and deletes the
     *  SOURCE file. Throws an IOException if the source file is not found.
     */
    public static String readCode(String source) {
        String output = "";
        try {
            InputStream stream = new FileInputStream(source);
            String separator = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            output = reader.lines().collect(Collectors.joining(separator));

            // Delete the file when read in.
            Files.deleteIfExists(Paths.get(source));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return output;
    }

    /** Writes the contents of CODE to a file named CLASSNAME.java. Throws an
     *  IOException if anything goes wrong.
     */
    public static void saveCode(String className, String code) {
        System.out.println(">> Saving code to " + className + ".java");
        try {
            Path writePath = Paths.get(className + ".java");
            Files.write(writePath, code.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /** Returns the class name parsed from the java CODE string. If no class
     *  name is found, prints an error and exits the program with error code 1
     */
    public static String parseClassName(String code) {
        Pattern pattern = Pattern.compile(CLASS_PATTERN);
        Matcher matcher = pattern.matcher(code);
        String output = "";
        if (matcher.find()) {
            output = matcher.group(1);
            System.out.println(">> Class found: " + output);
        } else {
            System.out.println("ERROR: Malformed java file! No class name was found.");
            System.exit(1);
        }
        return output;
    }

	public static void main(String[] args) {
        if (args.length < 1 || args.length > 1) {
            System.out.println("ERROR: Please provide a sound URL");
            System.exit(1);
        }

        // Save the code from the w e b.
        downloadCode(args[0]);
        String code = readCode(TMP_NAME);
        String className = parseClassName(code);
        saveCode(className, code);
	}
}
