package webservices;

import metiers.UniteEnseignementBusiness;
import entities.UniteEnseignement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UERestApi {

    private UniteEnseignementBusiness helper = new UniteEnseignementBusiness();


    @GET
    @Path("/list")
    public Response getAll() {
        return Response.ok(helper.getListeUE()).build();
    }

    @GET
    @Path("/{code}")
    public Response getByCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue != null) {
            return Response.ok(ue).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("UE not found")
                .build();
    }

    @POST
    @Path("/add")
    public Response addUE(UniteEnseignement ue) {
        boolean added = helper.addUniteEnseignement(ue);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity(ue)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Unable to add UE")
                .build();
    }

    @GET
    @Path("/domaine/{domaine}")
    public Response getByDomaine(@PathParam("domaine") String domaine) {
        List<UniteEnseignement> list = helper.getUEByDomaine(domaine);
        return Response.ok(list).build();
    }

    @GET
    @Path("/semestre/{semestre}")
    public Response getBySemestre(@PathParam("semestre") int semestre) {
        List<UniteEnseignement> list = helper.getUEBySemestre(semestre);
        return Response.ok(list).build();
    }


    @PUT
    @Path("/update/{code}")
    public Response updateUE(
            @PathParam("code") int code,
            UniteEnseignement ue) {

        boolean updated = helper.updateUniteEnseignement(code, ue);
        if (updated) {
            return Response.ok(ue).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("UE not found")
                .build();
    }


    @DELETE
    @Path("/delete/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        boolean deleted = helper.deleteUniteEnseignement(code);
        if (deleted) {
            return Response.ok("UE deleted successfully").build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("UE not found")
                .build();
    }
}
