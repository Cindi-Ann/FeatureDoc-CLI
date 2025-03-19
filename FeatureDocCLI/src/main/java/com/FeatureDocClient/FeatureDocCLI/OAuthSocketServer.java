package com.FeatureDocClient.FeatureDocCLI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class OAuthSocketServer {

    public static String startAndWaitForCode(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
           // System.out.println("Socket server listening on port " + port);

            // Wait for a single connection
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                // Read the HTTP request
                StringBuilder request = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    request.append(line).append("\n");
                }

                // Extract the authorization code from the request
                String code = extractAuthorizationCode(request.toString());
                if (code != null) {
                    System.out.println("Authorization code received: " + code);

                    // Send a simple HTTP response
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html");
                    out.println("\r\n");
                    out.println("<html><body><h1>LOGIN SUCCESSFUL</h1></body></html>");
                    serverSocket.close();
                    clientSocket.close();
                    return code;
                } else {
                    throw new IOException("No authorization code found in the request.");
                }
            }

        }
    }

    private static String extractAuthorizationCode(String request) {
        // Example request line: GET /callback?code=AUTHORIZATION_CODE&scope=... HTTP/1.1

            int codeStart = request.indexOf("code=") + 5;
            int codeEnd = request.indexOf("&", codeStart);
            if (codeEnd == -1) {
                codeEnd = request.indexOf(" ", codeStart);
            }
            return request.substring(codeStart, codeEnd);


    }
}