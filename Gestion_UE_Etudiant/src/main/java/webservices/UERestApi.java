package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/ue")
@Tag(name = "Unité d'Enseignement", description = "Gestion des UE (Unités d'Enseignement)")
public class UERestApi {

    public static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lister toutes les UE", description = "Récupère la liste complète des Unités d'Enseignement")
    public Response getAll() {
        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Ajouter une UE", description = "Crée une nouvelle Unité d'Enseignement")
    @ApiResponse(responseCode = "201", description = "UE créée avec succès")
    public Response addUE(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Détails de l'UE à ajouter")
            UniteEnseignement ue) {
        return Response.status(201)
                .entity(helper.addUniteEnseignement(ue))
                .build();
    }

    @DELETE
    @Path("/delete/{code}")
    @Operation(summary = "Supprimer une UE", description = "Supprime une UE via son code numérique")
    public Response deleteUE(
            @Parameter(description = "Code de l'UE", example = "101")
            @PathParam("code") int code) {
        return Response.status(200)
                .entity(helper.deleteUniteEnseignement(code))
                .build();
    }

    @PUT
    @Path("/update/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Modifier une UE", description = "Met à jour les informations d'une UE existante")
    public Response updateUE(
            @Parameter(description = "Code de l'UE à modifier") @PathParam("code") int code,
            UniteEnseignement updatedUE) {
        return Response.status(200)
                .entity(helper.updateUniteEnseignement(code, updatedUE))
                .build();
    }

    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Chercher par code", description = "Récupère une UE spécifique via son code")
    @ApiResponse(responseCode = "200", description = "UE trouvée")
    @ApiResponse(responseCode = "404", description = "UE introuvable")
    public Response getUEByCode(
            @Parameter(description = "Code de l'UE", example = "105")
            @PathParam("code") int code) {
        return Response.status(200)
                .entity(this.helper.getUEByCode(code))
                .build();
    }

    @GET
    @Path("domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Filtrer par domaine", description = "Récupère les UE appartenant à un domaine (ex: Informatique)")
    public Response getUEByDomaine(
            @Parameter(description = "Nom du domaine", example = "Informatique")
            @PathParam("domaine") String domaine) {
        return Response.status(200)
                .entity(this.helper.getUEByDomaine(domaine))
                .build();
    }

    @GET
    @Path("Semestre/{Semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Filtrer par semestre (Path)", description = "Récupère les UE d'un semestre spécifique via le chemin")
    public Response getUEBySemestre(
            @Parameter(description = "Numéro du semestre", example = "1")
            @PathParam("Semestre") int Semestre) {
        return Response.status(200)
                .entity(helper.getUEBySemestre(Semestre))
                .build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Rechercher par semestre (Query)", description = "Utilise un paramètre de requête (?semestre=X) pour filtrer")
    public Response getUEBySemestre2(
            @Parameter(description = "Numéro du semestre", example = "2")
            @QueryParam("semestre") int s) {
        return Response.status(200)
                .entity(helper.getUEBySemestre(s))
                .build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Test Hello", description = "Endpoint de test pour vérifier les QueryParams")
    public Response sayHelloQuerry(
            @Parameter(description = "Nom de la personne", example = "Rayen")
            @QueryParam("name") String name) {
        return Response.status(200)
                .entity("Hello Querry " + name + "!")
                .build();
    }
}