package br.com.fiap.ecoenergy.resource;

import br.com.fiap.ecoenergy.bo.ClienteBO;
import br.com.fiap.ecoenergy.dao.ClienteDAO;
import br.com.fiap.ecoenergy.exception.IdNaoEncontradoException;
import br.com.fiap.ecoenergy.model.Cliente;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.List;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ClienteBO clienteBO = new ClienteBO();

    // POST - Criar cliente
    @POST
    public Response criarCliente(Cliente cliente, @Context UriInfo uriInfo) {
        try {

            clienteBO.validarCliente(cliente);

            boolean usuarioCadastrado = clienteDAO.clienteExiste(cliente.getTelefone(), cliente.getCpf(), cliente.getEmail());

            if (usuarioCadastrado) {
                return Response.status(Response.Status.CONFLICT).entity("Informações já existentes.").build();
            }

            clienteDAO.cadastrar(cliente);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(cliente.getId()).build()).entity(cliente).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao criar cliente: " + e.getMessage()).build();
        }
    }

    // GET - Login Cliente
    @GET
    @Path("/login")
    public Response loginCliente(@QueryParam("email") String email, @QueryParam("senha") String senha) {
        try {
            Cliente cliente = clienteDAO.buscarPorLogin(email, senha);
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();
        }
    }

    // GET - Cliente específico
    @GET
    @Path("/{cpf}")
    public Response getCliente(@PathParam("cpf") String cpf) {
        try {
            Cliente cliente = clienteDAO.pesquisarPorCpf(cpf);
            return Response.ok(cliente).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    // GET - Listar clientes
    @GET
    public Response getTodosClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        System.out.println("Lista de Clientes:\n" + clientes.toString());
        return Response.ok(clientes).build();

    }

    // PUT - Atualizar cliente
    @PUT
    @Path("/{cpf}")
    public Response atualizarCliente(@PathParam("cpf") String cpf, Cliente clienteAtualizado, @Context UriInfo uriInfo) {
        try {
            Cliente cliente = clienteDAO.pesquisarPorCpf(cpf);

            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setSenha(clienteAtualizado.getSenha());

            clienteDAO.atualizar(cliente);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(cliente.getId()).build()).entity(cliente).build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // DELETE - Remover cliente
    @DELETE
    @Path("/{id}")
    public Response removerCliente(@PathParam("id") String id) {
        try {
            Cliente cliente = clienteDAO.pesquisarPorId(id);
            clienteDAO.remover(id);
            return Response.noContent().build();
        } catch (IdNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}

