package br.com.fiap.ecopower.resource;

import br.com.fiap.ecopower.bo.InvestimentoBO;
import br.com.fiap.ecopower.dao.InvestimentoDAO;
import br.com.fiap.ecopower.model.Investimento;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

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
}
