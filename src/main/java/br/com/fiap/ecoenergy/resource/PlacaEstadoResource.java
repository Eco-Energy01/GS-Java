package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.dao.PlacaEstadoDAO;
import br.com.fiap.ecoenergy.model.PlacaEstado;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/estado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlacaEstadoResource {

    PlacaEstadoDAO placaEstadoDAO = new PlacaEstadoDAO();

    // GET - Calculo Estado
    @GET
    @Path("/calculo")
    public Response CalculoPlaca(@QueryParam("nome_estado") String nomeEstado) {
        try {
            PlacaEstado placaEstado = placaEstadoDAO.buscarPorEstado(nomeEstado);
            return Response.ok(nomeEstado).build();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            return Response.status(Response.Status.UNAUTHORIZED).entity("Estado inválidas.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();
        }
    }
}
