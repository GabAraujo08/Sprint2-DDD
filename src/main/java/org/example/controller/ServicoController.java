package org.example.controller;

import org.example.dao.servicodao.ServicoDao;
import org.example.dtos.ServicoDto;
import org.example.entities.servico.Servico;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.exceptions.servico.ServicoNotSavedException;
import org.example.service.servico.ServicoService;
import org.example.service.servico.ServicoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/servico")
public class ServicoController {

    private final ServicoService servicoService = ServicoServiceFactory.create();

    @OPTIONS
    @Path("")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }

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
    public Response add(ServicoDto input) {
        if (input.getId() == null) {
            try {
                // Criando serviço com construtor sem id
                Servico servico = this.servicoService.create(new Servico(
                        input.getNome(),
                        input.getDescricao(),
                        input.getCategoria(),
                        input.getValor(),
                        null
                ));

                return Response
                        .status(Response.Status.CREATED)
                        .entity(servico)
                        .build();
            } catch (ServicoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Falha ao salvar o serviço no banco. Verifique os dados e tente novamente."))
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir serviço. Detalhes técnicos: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of(
                                    "mensagem",
                                    "Este método permite apenas a criação de novos serviços, sem ID especificado."))
                    .build();
        }
    }



    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Servico> servicos = servicoService.readAll();
        if (servicos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(servicos).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ServicoDto input) {
        try {

            Servico servico = new Servico(
                    input.getNome(),
                    input.getDescricao(),
                    input.getCategoria(),
                    input.getValor(),
                    id);


            Servico servicoAtualizado = this.servicoService.update(servico);


            return Response.status(Response.Status.OK).entity(servicoAtualizado).build();
        } catch (ServicoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Serviço não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar o serviço. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }


    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.servicoService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ServicoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Serviço não encontrado"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar o serviço. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }
}
