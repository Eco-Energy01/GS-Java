package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.dao.ClienteDAO;
import br.com.fiap.ecoenergy.dao.DuvidaDAO;
import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.model.Cliente;
import br.com.fiap.ecoenergy.model.Duvida;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/duvida")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DuvidaResource {

    private DuvidaDAO duvidaDAO = new DuvidaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    // POST - Salvar duvida
    @POST
    public Response salvarDuvida(Duvida duvida, @Context UriInfo uriInfo) {
        try {
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

            duvida.setCliente(cliente);
            duvidaDAO.salvar(duvida);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(duvida.getId()).build()).entity(duvida).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao salvar dúvida: " + e.getMessage()).build();
        }
    }

    // GET - Duvida específico
    @GET
    @Path("/{id}")
    public Response getDuvida(@PathParam("id") String id) {
        try {
            Duvida duvida = duvidaDAO.pesquisarPorId(id);
            return Response.ok(duvida).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // GET - Listar duvidas
    @GET
    public Response getTodasDuvidas() {
        List<Duvida> duvidas = duvidaDAO.listar();
        System.out.println("Lista de Duvidas:\n" + duvidas.toString());
        return Response.ok(duvidas).build();

    }

    // PUT - Atualizar duvida
    @PUT
    @Path("/{id}")
    public Response atualizarDuvida(@PathParam("id") String id, Duvida duvidaAtualizada, @Context UriInfo uriInfo) {
        try {
            Duvida duvida = duvidaDAO.pesquisarPorId(id);

            duvida.setAssunto(duvidaAtualizada.getAssunto());
            duvida.setMensagem(duvidaAtualizada.getMensagem());

            duvidaDAO.atualizar(duvida);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(duvida.getId()).build()).entity(duvida).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // DELETE - Remover duvida
    @DELETE
    @Path("/{id}")
    public Response removerDuvida(@PathParam("id") String id) {
        try {
            Duvida duvida = duvidaDAO.pesquisarPorId(id);
            duvidaDAO.remover(id);
            return Response.noContent().build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
