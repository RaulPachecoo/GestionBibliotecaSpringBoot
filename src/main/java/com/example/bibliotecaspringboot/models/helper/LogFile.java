package com.example.bibliotecaspringboot.models.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFile {
    private static String path="ficheros/logs";
    private static String file=path+"/historial"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("_yyyyMMdd"))+".log";
    /**
     * Graba en el fichero log para el día actual el mensaje recibido
     * el mensaje tambien es grabado en la tabla historico de la BD
     * @param msgLog texto a guarda en el fichero log
     * @throws IOException en el caso de que no pueda accederse adecuadamente al fichero
     */
    public static void saveLOG(String msgLog) throws Exception {
        saveLOGsinBD(msgLog);
    }
    /**
     * Graba en el fichero log para el día actual el mensaje recibido
     * @param msgLog texto a guarda en el fichero log
     * @throws IOException en el caso de que no pueda accederse adecuadamente al fichero
     */
    public static void saveLOGsinBD(String msgLog) throws IOException {
        Files.createDirectories(Path.of(path));
        Path path = Paths.get(file);
        if (path.toFile().exists())
            Files.writeString(path,msgLog+"\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        else Files.writeString(path,msgLog+"\n",StandardCharsets.UTF_8,StandardOpenOption.CREATE);
    }
}
