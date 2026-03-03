package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleApi {

    private ModuleBusiness helper = new ModuleBusiness();
    private UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    // 🔹 Get all modules
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }

    // 🔹 Get module by matricule
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        return Response.status(200)
                .entity(helper.getModuleByMatricule(matricule))
                .build();
    }

    // 🔹 Get modules by type
    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@PathParam("type") String type) {
        Module.TypeModule typeModule = Module.TypeModule.valueOf(type.toUpperCase());
        return Response.status(200)
                .entity(helper.getModulesByType(typeModule))
                .build();
    }

    // 🔹 Get modules by UE code
    @GET
    @Path("/ue/{codeUE}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("codeUE") int codeUE) {
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

    // 🔹 Add module
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        return Response.status(200)
                .entity(helper.addModule(module))
                .build();
    }

    // 🔹 Update module
    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        return Response.status(200)
                .entity(helper.updateModule(matricule, updatedModule))
                .build();
    }

    // 🔹 Delete module
    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        return Response.status(200)
                .entity(helper.deleteModule(matricule))
                .build();
    }
}