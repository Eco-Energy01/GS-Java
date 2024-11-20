package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.bo.InvestimentoBO;
import br.com.fiap.ecoenergy.dao.InvestimentoDAO;
import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.model.Investimento;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/investimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvestimentoResource {

    private InvestimentoBO investimentoBO = new InvestimentoBO();
    private InvestimentoDAO investimentoDAO = new InvestimentoDAO();

    @POST
    public Response salvarInvestimento(Investimento investimento, @Context UriInfo uriInfo) {
        try {

            investimentoBO.validarInvestimento(investimento);
            investimentoDAO.salvar(investimento);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(investimento.getId()).build()).entity(investimento).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao salvar investimento: " + e.getMessage()).build();
        }
    }

    // GET - Investimento específico
    @GET
    @Path("/{id}")
    public Response getInvestimento(@PathParam("id") String id) {
        try {
            Investimento investimento = investimentoDAO.pesquisarPorId(id);
            return Response.ok(investimento).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // GET - Listar investimentos
    @GET
    public Response getTodosInvestimentos() {
        List<Investimento> investimentos = investimentoDAO.listar();
        System.out.println("Lista de Investimentos:\n" + investimentos.toString());
        return Response.ok(investimentos).build();

    }

    // PUT - Atualizar investimento
    @PUT
    @Path("/{id}")
    public Response atualizarInvestimento(@PathParam("id") String id, Investimento investimentoAtualizado, @Context UriInfo uriInfo) {
        try {
            Investimento investimento = investimentoDAO.pesquisarPorId(id);

            investimento.setAreaInteresse(investimentoAtualizado.getAreaInteresse());
            investimento.setEmpresa(investimentoAtualizado.getEmpresa());
            investimento.setSetor(investimentoAtualizado.getSetor());
            investimento.setTelefone(investimentoAtualizado.getTelefone());
            investimento.setValorInvestimento(investimentoAtualizado.getValorInvestimento());

            investimentoDAO.atualizar(investimento);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(investimento.getId()).build()).entity(investimento).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // DELETE - Remover investimento
    @DELETE
    @Path("/{id}")
    public Response removerInvestimento(@PathParam("id") String id) {
        try {
            Investimento investimento = investimentoDAO.pesquisarPorId(id);
            investimentoDAO.remover(id);
            return Response.noContent().build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
