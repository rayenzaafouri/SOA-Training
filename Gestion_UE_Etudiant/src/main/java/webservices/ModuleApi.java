package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleApi {

    private ModuleBusiness helper = new ModuleBusiness();
    private UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();


    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }


    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMatricule(@PathParam("matricule") String matricule) {
        Module m = helper.getModuleByMatricule(matricule);
        if (m != null) {
            return Response.status(200).entity(m).build();
        }
        return Response.status(404).entity("Module not found").build();
    }


    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByType(@PathParam("type") String type) {
        try {
            Module.TypeModule t = Module.TypeModule.valueOf(type.toUpperCase());
            List<Module> list = helper.getModulesByType(t);
            return Response.status(200).entity(list).build();
        } catch (Exception e) {
            return Response.status(400).entity("Invalid type").build();
        }
    }


    @GET
    @Path("/ue/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUE(@PathParam("code") int code) {
        UniteEnseignement ue = ueHelper.getUEByCode(code);
        if (ue == null) {
            return Response.status(404).entity("UE not found").build();
        }
        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Module module) {
        boolean ok = helper.addModule(module);
        if (ok) {
            return Response.status(201).entity(module).build();
        }
        return Response.status(400).entity("Cannot add module (UE not found)").build();
    }

    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("matricule") String matricule, Module module) {
        boolean ok = helper.updateModule(matricule, module);
        if (ok) {
            return Response.status(200).entity(module).build();
        }
        return Response.status(404).entity("Module not found").build();
    }


    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("matricule") String matricule) {
        boolean ok = helper.deleteModule(matricule);
        if (ok) {
            return Response.status(200).entity("Module deleted").build();
        }
        return Response.status(404).entity("Module not found").build();
    }
}
