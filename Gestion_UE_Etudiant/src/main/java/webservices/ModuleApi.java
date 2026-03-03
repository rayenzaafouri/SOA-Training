package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/module")
@Tag(name = "Module", description = "Endpoints pour la gestion des modules d'enseignement")
public class ModuleApi {

    private ModuleBusiness helper = new ModuleBusiness();
    private UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lister tous les modules", description = "Récupère la liste complète des modules enregistrés")
    @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    public Response getAllModules() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Trouver un module par matricule", description = "Recherche un module spécifique via son matricule unique")
    @ApiResponse(responseCode = "200", description = "Module trouvé")
    @ApiResponse(responseCode = "404", description = "Module non trouvé")
    public Response getModuleByMatricule(
            @Parameter(description = "Le matricule du module", example = "MOD-2024-01")
            @PathParam("matricule") String matricule) {
        return Response.status(200)
                .entity(helper.getModuleByMatricule(matricule))
                .build();
    }

    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Filtrer par type", description = "Récupère les modules selon leur catégorie (COURS, TP, TD...)")
    public Response getModulesByType(
            @Parameter(description = "Le type de module", example = "COURS")
            @PathParam("type") String type) {
        Module.TypeModule typeModule = Module.TypeModule.valueOf(type.toUpperCase());
        return Response.status(200)
                .entity(helper.getModulesByType(typeModule))
                .build();
    }

    @GET
    @Path("/ue/{codeUE}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lister par UE", description = "Récupère tous les modules rattachés à une Unité d'Enseignement")
    @ApiResponse(responseCode = "200", description = "Modules trouvés")
    @ApiResponse(responseCode = "404", description = "Unité d'Enseignement inexistante")
    public Response getModulesByUE(
            @Parameter(description = "Code numérique de l'UE", example = "101")
            @PathParam("codeUE") int codeUE) {
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        if (ue == null) {
            return Response.status(404)
                    .entity("UniteEnseignement not found")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Ajouter un nouveau module", description = "Crée un module dans la base de données")
    @ApiResponse(responseCode = "200", description = "Module ajouté avec succès")
    public Response addModule(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objet Module au format JSON")
            Module module) {
        return Response.status(200)
                .entity(helper.addModule(module))
                .build();
    }

    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Mettre à jour un module", description = "Modifie les informations d'un module existant")
    public Response updateModule(
            @Parameter(description = "Matricule du module à modifier") @PathParam("matricule") String matricule,
            Module updatedModule) {
        return Response.status(200)
                .entity(helper.updateModule(matricule, updatedModule))
                .build();
    }

    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Supprimer un module", description = "Supprime définitivement un module de la base")
    public Response deleteModule(
            @Parameter(description = "Matricule du module à supprimer") @PathParam("matricule") String matricule) {
        return Response.status(200)
                .entity(helper.deleteModule(matricule))
                .build();
    }
}