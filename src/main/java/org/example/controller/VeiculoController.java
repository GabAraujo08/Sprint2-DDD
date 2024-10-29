package org.example.controller;

import org.example.dao.usuariodao.UsuarioDao;
import org.example.dao.usuariodao.UsuarioDaoFactory;
import org.example.dtos.UsuarioDto;
import org.example.dtos.VeiculoDto;
import org.example.entities.usuario.Usuario;
import org.example.entities.veiculo.Veiculo;
import org.example.exceptions.usuario.UsuarioNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotFoundException;
import org.example.exceptions.veiculo.VeiculoNotSavedException;
import org.example.service.usuario.UsuarioService;
import org.example.service.usuario.UsuarioServiceFactory;
import org.example.service.veiculo.VeiculoService;
import org.example.service.veiculo.VeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
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

        List<Veiculo> veiculos = veiculoService.readAll();

        if (veiculos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK)
                .entity(this.veiculoService.readAll()).build();
    }

    @PUT
    @Path("/update/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("placa") String placa, VeiculoDto input) {
        try{
            Veiculo veiculo = this.veiculoService.update(new Veiculo(
                    input.getMarca(),
                    input.getModelo(),
                    input.getAno(),
                    placa,
                    input.getCor(),
                    input.getKilometragem(),
                    usuarioService.findById(input.getProprietarioId()),
                    input.getTipo()

            ));

            return Response.status(Response.Status.OK)
                    .entity(veiculo).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar veículo."))
                    .build();
        } catch (VeiculoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Veículo não encontrado."))
                    .build();
        } catch (UsuarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Proprietário não encontrado."))
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{placa}")
    public Response delete(@PathParam("placa") String placa) {
        try{
            this.veiculoService.delete(placa);
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar veículo. Detalhes técnicos"))
                    .build();
        } catch (VeiculoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Veículo não encontrado."))
                    .build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

