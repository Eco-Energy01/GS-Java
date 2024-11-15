package br.com.fiap.ecopower.view;

import br.com.fiap.ecopower.cors.CorsFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/ecopower/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap.ecopower.resource");

        rc.register(new CorsFilter());

        URI uri = URI.create(BASE_URI);
        return GrizzlyHttpServerFactory.createHttpServer(uri, rc);
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println("Servidor iniciado em " + BASE_URI);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
