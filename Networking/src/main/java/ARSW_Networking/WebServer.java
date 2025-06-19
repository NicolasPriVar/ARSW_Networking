package ARSW_Networking;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class WebServer {
    private static final int PORT = 8080;
    private static final String ROOT = "F:/Universidad/Intersemestral 2025-i/ARSW/ARSW_Networking/Networking/www";


    public static void main(String[] args) {
        System.out.println("Servidor iniciado en http://localhost:" + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleRequest(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error procesando solicitud: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();

        String requestLine = in.readLine();
        if (requestLine == null || !requestLine.startsWith("GET")) {
            return;
        }

        String[] tokens = requestLine.split(" ");
        String path = tokens[1];
        if (path.equals("/")) {
            path = "/index.html";
        }

        File file = new File(ROOT + path);
        System.out.println("Archivo solicitado: " + file.getAbsolutePath());
        if (file.exists() && !file.isDirectory()) {
            String contentType = getContentType(file.getName());
            byte[] content = Files.readAllBytes(file.toPath());

            PrintWriter headerOut = new PrintWriter(out);
            headerOut.println("HTTP/1.1 200 OK");
            headerOut.println("Content-Type: " + contentType);
            headerOut.println("Content-Length: " + content.length);
            headerOut.println();
            headerOut.flush();

            out.write(content);
            out.flush();
        } else {
            PrintWriter notFoundOut = new PrintWriter(out);
            notFoundOut.println("HTTP/1.1 404 Not Found");
            notFoundOut.println("Content-Type: text/html");
            notFoundOut.println();
            notFoundOut.println("<h1>404 Not Found</h1>");
            notFoundOut.flush();
        }

        in.close();
        out.close();
    }

    private static String getContentType(String fileName) {
        Map<String, String> mimeTypes = new HashMap<>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("htm", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("js", "application/javascript");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("gif", "image/gif");
        mimeTypes.put("jpeg", "image/jpeg");
        mimeTypes.put("svg", "image/svg+xml");
        mimeTypes.put("ico", "image/x-icon");
        mimeTypes.put("jpe", "image/jpeg");

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return mimeTypes.getOrDefault(ext, "application/octet-stream");
    }
}
