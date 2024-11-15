package br.com.fiap.ecopower.resource;

import br.com.fiap.ecopower.bo.ServicoBO;
import br.com.fiap.ecopower.dao.ServicoDAO;
import br.com.fiap.ecopower.model.Servico;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

@Path("/servico")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServicoResource {

    ServicoBO servicoBO = new ServicoBO();
    ServicoDAO servicoDAO = new ServicoDAO();

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
}
