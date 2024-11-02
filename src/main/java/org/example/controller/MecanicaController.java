package org.example.controller;



import org.example.dtos.MecanicaDto;
import org.example.entities.mecanica.Mecanica;
import org.example.entities.servico.Servico;
import org.example.exceptions.mecanica.MecanicaNotFoundException;
import org.example.exceptions.mecanica.MecanicaNotSavedException;
import org.example.exceptions.servico.ServicoNotFoundException;
import org.example.service.mecanica.MecanicaService;
import org.example.service.mecanica.MecanicaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/mecanica")
public class MecanicaController {

    private final MecanicaService mecanicaService = MecanicaServiceFactory.create();

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
    public Response add(MecanicaDto input) {
        if (input.getId() == null) { // Verifica se o ID é nulo para garantir que é uma nova mecânica
            try {
                // Criando mecânica com construtor sem id
                Mecanica mecanica = this.mecanicaService.create(new Mecanica(
                        null,
                        input.getNome(),
                        input.getEndereco(),
                        input.getTelefone(),
                        input.getCnpjMecanica()
                ));

                return Response
                        .status(Response.Status.CREATED)
                        .entity(mecanica)
                        .build();
            } catch (MecanicaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Falha ao salvar a mecânica no banco. Verifique os dados e tente novamente."))
                        .build();
            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir mecânica. Detalhes técnicos: " + e.getMessage()))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of(
                                    "mensagem",
                                    "Este método permite apenas a criação de novas mecânicas, sem ID especificado."))
                    .build();
        }
    }




    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Mecanica> mecanicas = mecanicaService.readAll();
        if (mecanicas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(mecanicas).build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, MecanicaDto input) {
        try {
            Mecanica mecanica = new Mecanica(
                    id,
                    input.getNome(),
                    input.getEndereco(),
                    input.getTelefone(),
                    input.getCnpjMecanica()
            );

            Mecanica mecanicaAtualizada = this.mecanicaService.update(mecanica);


            return Response.status(Response.Status.OK).entity(mecanicaAtualizada).build();

        } catch (MecanicaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Mecânica não encontrada"))
                    .build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar a mecânica. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }




    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.mecanicaService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (MecanicaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Mecânica não encontrada"))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar a mecânica. Detalhes técnicos: " + e.getMessage()))
                    .build();
        }
    }
}
