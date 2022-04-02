package service;

import cst8218.desj0270.sprite.AbstractFacade;
import cst8218.desj0270.sprite.Sprite;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful HTTP API
 * 
 * @author Frederic Desjardins
 * @version 1.0
 */
@Stateless
@Path("cst8218.desj0270.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "SpriteFrederic-ejbPU")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    
    /**
    *3.b - This function supplies an endpoint to get the number of Sprites
    */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /**
    * 3.c - This Function updates a Sprite without replacing exisiting values
    * @param id passes a the id
    * @param entity passes a Sprite
    */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postUpdate(@PathParam("id") Long id, Sprite entity) {
        
        Long entId = entity.getId();
        
        if (entId == null) {         
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (super.find(id) != null) {
            
            if (entId.equals(id)) {
                Sprite tempSprite = super.find(id);
                tempSprite.updateSprite(entity);
                return Response.status(Response.Status.CREATED).build();

            }
            
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();          
         
    }
    
    /**
    * 3.f - This Function updates a Sprite without replacing exisiting values
    * @param entity passes a Sprite
    */
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postUpdate(Sprite entity) {
        
        Long id = entity.getId();
        
        
        if (id == null) {
            super.create(entity);
            return Response.status(Response.Status.CREATED).build();
        }
        
        Sprite tempSprite = super.find(id);
        
        if (tempSprite != null) {
            
            tempSprite.updateSprite(entity);
            return Response.status(Response.Status.CREATED).build();
            
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }       
     
    }
    

    /**
    * 3.d - This function edits a Sprite and replaces all existing values by default if not specified
    * @param id gets
    * @param entity passes a Sprite
    */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
        
        
        
        Long entId = entity.getId();
        
        if (entId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (super.find(id) != null) {
            
            if (entId.equals(id)) {
                super.edit(entity);
                return Response.status(Response.Status.CREATED).build();

            }
            
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
     /**
     * 
     * 3.e of features
     * Returns not implemented status as this is not supposed to work 
     * @param entity does nothing
     * @return Returns a status
     */
    @PUT
    public Response editRoot(Sprite entity) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
      
   
    /**
     * Deletes a sprite
     * @param id sprite id
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
    * Returns a specific sprite
    * @param id sprite's id
    */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    /**
    * Returns all sprite in the database
    */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    /**
    * Returns all sprites within the specified range
    * @param id sprite's id from
    * @param id sprite's id to
    */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
