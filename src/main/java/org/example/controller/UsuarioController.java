package org.example.controller;

import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.dtos.UsuarioDto;
import org.example.entities.usuario.Usuario;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.usuario.UsuarioNotSavedException;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService = UsuarioServiceFactory.create();


    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(Map.of("mensagem", "Hello World!")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Ocorreu um erro inesperado: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(UsuarioDto input) {
        if (input.getId() == null) {
            try {
                // Criando usuário com construtor sem id
                Usuario usuario = this.usuarioService.create(new Usuario(null, input.getNome(), input.getEndereco(), input.getSenha(), input.getEmail(), input.getDataNascimento(), input.getCpf(), input.getTelefone()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(usuario)
                        .build();
            } catch (UsuarioNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Falha ao salvar o usuário no banco. Verifique os dados e tente novamente."))
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir usuário. Detalhes técnicos: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of(
                                    "mensagem",
                                    "Este método permite apenas a criação de novos usuários, sem ID especificado."))
                    .build();
        }
    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        UsuarioDao usuarioDao = UsuarioDaoFactory.create();
        List<Usuario> usuarios = usuarioDao.readAll();

        if (usuarios.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); // Retorna 204 se não houver usuários
        }

        return Response.status(Response.Status.OK)
                .entity(this.usuarioService.readAll()).build();
    }

    @PUT
    @Path("/update/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("cpf") String cpf, UsuarioDto input) {
        try {
            Usuario usuario = this.usuarioService.update(new Usuario(null, input.getNome(), input.getEndereco(), input.getSenha(), input.getEmail(), input.getDataNascimento(), cpf, input.getTelefone()));
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar usuário. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{cpf}")
    public Response delete(@PathParam("cpf") String cpf) {
        try {
            this.usuarioService.delete(cpf);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar usuário. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }
}
