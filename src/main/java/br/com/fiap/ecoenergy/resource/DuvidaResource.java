package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.dao.ClienteDAO;
import br.com.fiap.ecoenergy.dao.DuvidaDAO;
import br.com.fiap.ecoenergy.model.Cliente;
import br.com.fiap.ecoenergy.model.Duvida;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/duvida")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DuvidaResource {

    private DuvidaDAO duvidaDAO = new DuvidaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    @POST
    public Response salvarDuvida(Duvida duvida, @Context UriInfo uriInfo) {
        try {
            // Certifique-se de que o cliente e o ID foram fornecidos
            if (duvida.getCliente() == null || duvida.getCliente().getId() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Cliente ou ID do cliente não fornecido")
                        .build();
            }

            Cliente cliente = clienteDAO.pesquisarPorId(duvida.getCliente().getId());

            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Cliente não encontrado com ID: " + duvida.getCliente().getId())
                        .build();
            }

            duvida.setCliente(cliente); // Certifica que o cliente é corretamente associado
            duvidaDAO.salvar(duvida);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(duvida.getId()).build()).entity(duvida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao salvar dúvida: " + e.getMessage()).build();
        }
    }

}
