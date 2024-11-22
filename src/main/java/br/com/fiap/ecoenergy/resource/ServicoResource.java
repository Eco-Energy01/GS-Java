package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.bo.ServicoBO;
import br.com.fiap.ecoenergy.dao.ServicoDAO;
import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.model.Servico;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/servico")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServicoResource {

    ServicoBO servicoBO = new ServicoBO();
    ServicoDAO servicoDAO = new ServicoDAO();

    // POST - Salvar servico
    @POST
    public Response salvarServico(Servico servico, @Context UriInfo uriInfo) {
        try {

            servicoBO.validarServico(servico);
            servicoDAO.salvar(servico);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(servico.getId()).build()).entity(servico).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao salvar serviço: " + e.getMessage()).build();
        }
    }

    // GET - Serviço específico
    @GET
    @Path("/{id}")
    public Response getServico(@PathParam("id") String id) {
        try {
            Servico servico = servicoDAO.pesquisarPorId(id);
            return Response.ok(servico).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    // GET - Listar serviços
    @GET
    public Response getTodosServicos() {
        List<Servico> servicos = servicoDAO.listar();
        System.out.println("Lista de Serviços:\n" + servicos.toString());
        return Response.ok(servicos).build();

    }

    // PUT - Atualizar serviço
    @PUT
    @Path("/{id}")
    public Response atualizarServico(@PathParam("id") String id, Servico servicoAtualizado, @Context UriInfo uriInfo) {
        try {
            Servico servico = servicoDAO.pesquisarPorId(id);

            servico.setEndereco(servicoAtualizado.getEndereco());
            servico.setLocal(servicoAtualizado.getLocal());
            servico.setQuantidadePlaca(servicoAtualizado.getQuantidadePlaca());
            servico.setTipoServico(servicoAtualizado.getTipoServico());
            servico.setTelefone(servicoAtualizado.getTelefone());

            servicoDAO.atualizar(servico);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(servico.getId()).build()).entity(servico).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // DELETE - Remover serviço
    @DELETE
    @Path("/{id}")
    public Response removerServiço(@PathParam("id") String id) {
        try {
            Servico servico = servicoDAO.pesquisarPorId(id);
            servicoDAO.remover(id);
            return Response.noContent().build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
