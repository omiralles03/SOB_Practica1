/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Article;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import java.util.Date;
import java.util.List;
import model.entities.Topic;
import model.entities.User;

/**
 *
 * @author oupman
 */
@Stateless
@Path("article")
public class ArticleFacadeREST extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public ArticleFacadeREST() {
        super(Article.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByFillters(@QueryParam("topic") List<String> topics, @QueryParam("author") String author) {
        
        // Initialize topics as an empty List if null
        if(topics == null)
            topics = List.of();
        
        // Return error if more than 2 topics are specified
        if(topics.size() > 2) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("You can specify a maximum of two topics.")
                    .build();
        }
        
        // String for the query and add conditions if specified
        StringBuilder queryString = new StringBuilder("SELECT a FROM Article a WHERE 1=1");
        
        if (author != null) {
            queryString.append(" AND a.author.username = :author");
        }
        
        if (!topics.isEmpty()) {
            queryString.append(" AND a.topic IN :topics");
        }
        
        // Order articles by popularity (descendant)
        queryString.append(" ORDER BY a.views DESC");
        
        // Build the query and only set parameters if specified
        TypedQuery<Article> query = em.createQuery(queryString.toString(), Article.class);
        
        if(author != null) {
            query.setParameter("author", author);
        }

        if(!topics.isEmpty()) {
            query.setParameter("topics", topics);
        }
        
        List<Article> articles = query.getResultList();
        return Response.ok().entity(articles).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response findById(@PathParam("id") Long id) {
        
        // Find article by id
        Article article = super.find(id);
        if(article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article not found")
                    .build();
        }
        
        // Increment views
        article.setViews(article.getViews() + 1);
        super.edit(article);
        
        return Response.ok().entity(article).build();        
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response deleteById(@PathParam("id") Long id, @Context SecurityContext securityContext) {
        
        // Find article by id
        Article article = super.find(id);
        if(article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article not found")
                    .build();
        }
        
        String username = securityContext.getUserPrincipal().getName();
        if(!article.getAuthor().getUsername().equals(username)) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("You can not delete an article that is not yours.")
                    .build();
        }
        
        super.remove(article);
        
        return Response.noContent().build();        
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response createArticle(Article article, @Context SecurityContext securityContext){
        
        if(article.getTopics() == null || article.getTopics().isEmpty()) {
            Response.status(Response.Status.BAD_REQUEST)
                    .entity("At least one topic is required.")
                    .build();
        }
        
        List<Topic> validTopics = em.createQuery("SELECT t FROM Topic t WHERE t.name IN: topicNames", Topic.class)
                .setParameter("topicNames", article.getTopics())
                .getResultList();
        
        if(validTopics.isEmpty() || validTopics.size() != article.getTopics().size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid topics")
                    .build();
        }
        
        String username = securityContext.getUserPrincipal().getName();
        User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        article.setAuthor(user);

        if(user == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("User does not exist or is not authenticated.")
                    .build();
        }
        
        article.setPublishedAt(new Date());
        
        article.setAuthor(user);
        
        article.setTopics(validTopics);
        
        super.create(article);
        
        return Response.status(Response.Status.CREATED)
                .entity("Article created with id " +article.getId())
                .build();        
    }
}
