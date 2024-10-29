package org.example.controller;

import org.example.dtos.VeiculoDto;
import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;
import org.example.service.veiculo.VeiculoService;
import org.example.service.veiculo.VeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService = VeiculoServiceFactory.create();
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
    public Response add(VeiculoDto input) {
        try {
            // Busca o proprietário pelo ID fornecido
            Usuario proprietario = usuarioService.findById(input.getProprietarioId());
            System.out.println("O DONO DO VEICULO É: " + proprietario.toString());

            Veiculo veiculo = new Veiculo(
                    input.getMarca(),
                    input.getModelo(),
                    input.getAno(),
                    input.getPlaca(),
                    input.getCor(),
                    input.getKilometragem(),
                    proprietario, // Passe o objeto Usuario aqui
                    input.getChassi(),
                    input.getTipo()
            );

            veiculoService.create(veiculo);

            return Response.status(Response.Status.CREATED).entity(veiculo).build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Proprietário não encontrado"))
                    .build();
        } catch (VeiculoNotSavedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Falha ao salvar o veiculo no banco. Verifique os dados e tente novamente."))
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Um erro inesperado aconteceu."))
                    .build();
        }
    }




    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return null;
    }

    @PUT
    @Path("/update/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("placa") String placa, VeiculoDto input) {
        return null;
    }

    @DELETE
    @Path("/delete/{placa}")
    public Response delete(@PathParam("placa") String placa) {
        return null;
    }
}

