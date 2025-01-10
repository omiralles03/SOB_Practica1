/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import authn.JwtUtil;
import authn.Secured;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
import model.auth.CustomPrincipal;
import model.entities.ArticleDTO;
import model.entities.User;

/**
 *
 * @author oupman, gerard
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
    public Response findByFilters(@QueryParam("topic") List<String> topics, @QueryParam("author") String author) {
        List<Long> topicIds = null;
        Long authorId = null;

        // Build the query
        StringBuilder queryString = new StringBuilder("SELECT a FROM Article a WHERE 1=1");

        // Add topics to query if provided
        if (topics != null && !topics.isEmpty() && topics.stream().anyMatch(topic -> topic != null && !topic.isEmpty())) {
            topicIds = em.createQuery("SELECT t.id FROM Topic t WHERE t.name IN :topicNames", Long.class)
                    .setParameter("topicNames", topics)
                    .getResultList();
            if (topicIds.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No articles found with those topics.")
                        .build();
            }
            queryString.append(" AND EXISTS (SELECT t FROM a.topicIds t WHERE t IN :topicIds)");
        }

        // Add author to query if provided
        if (author != null && !author.isEmpty()) {
            try {
                authorId = em.createQuery("SELECT u.id FROM User u WHERE u.username = :authorName", Long.class)
                        .setParameter("authorName", author)
                        .getSingleResult();
            } catch (NoResultException e) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No articles found with this author, or the author does not exist.")
                        .build();
            }
            queryString.append(" AND a.authorId = :authorId");
        }

        // Sort articles by views
        queryString.append(" ORDER BY a.views DESC");

        // Create the query with the parameters provided
        TypedQuery<Article> query = em.createQuery(queryString.toString(), Article.class);

        if (topics != null && !topics.isEmpty() && topicIds != null) {
            query.setParameter("topicIds", topicIds);
        }
        if (authorId != null) {
            query.setParameter("authorId", authorId);
        }

        // Create a list of the articles or return it as empty if no articles match the filters
        List<Article> articles = query.getResultList();
        if (articles.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No articles found with the specified filters.")
                    .build();
        }
  
        // Use Article DTO to return the desired info
        List<ArticleDTO> articleDTOs = articles.stream()
                .map(article -> {
                    System.out.println("author id: " +article.getAuthorId());
                    User authorEntity = em.find(User.class, article.getAuthorId());
                    return new ArticleDTO(
                            article.getId(),
                            article.getTitle(),
                            null,
                            article.getSummary(),
                            null,
                            article.getPublishedAt(),
                            authorEntity.getUsername(),
                            authorEntity.getImageURL(),
                            article.getViews(),
                            article.getIsPrivate(),
                            article.getImageURL()
                    );
                })
                .toList();

        return Response.ok(articleDTOs).build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id, @HeaderParam("Authorization") String authorizationHeader) {
        // Find article by ID
        Article article = super.find(id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article not found")
                    .build();
        }

        // Check if article is private and user is authenticated
        if (article.getIsPrivate()) {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("You must provide a valid token to access private articles.")
                        .build();
            }

            // Validate JWT Token as done in JwtFilter (necessary since we do not use @Secured)
            String token = authorizationHeader.substring("Bearer".length()).trim();
            String username;
            try {
                username = JwtUtil.validateToken(token);
            } catch (JWTVerificationException e) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid or expired token.")
                        .build();
            }

            // Check if the user exists in the DB
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            if (user == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("User not found.")
                        .build();
            }
        }

        // Increment article views
        article.setViews(article.getViews() + 1);
        super.edit(article);

        // Get the author and build the ArticleDTO
        User author = em.find(User.class, article.getAuthorId());
        List<String> topicNames = em.createQuery(
                "SELECT t.name FROM Topic t WHERE t.id IN :topicIds", String.class)
                .setParameter("topicIds", article.getTopicIds())
                .getResultList();

        ArticleDTO articleDTO = new ArticleDTO(
                article.getId(),
                article.getTitle(),
                topicNames,
                null,
                article.getBody(),
                article.getPublishedAt(),
                author.getUsername(),
                author.getImageURL(),
                article.getViews(),
                article.getIsPrivate(),
                article.getImageURL()
        );
        
        return Response.ok().entity(articleDTO).build();
    }
    
    @DELETE
    @Path("{id}")
    @Secured
    public Response deleteById(@PathParam("id") Long id, @Context SecurityContext securityContext) {
        
        // Find article by id
        Article article = super.find(id);
        if(article == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Article not found")
                    .build();
        }        

        // Check if the authenticated user is the author of the article
        CustomPrincipal principal = (CustomPrincipal) securityContext.getUserPrincipal();
        if (!article.getAuthorId().equals(principal.getUserId())) {
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
    public Response createArticle(ArticleDTO articleDTO, @Context SecurityContext securityContext) {

        // Validate the topics by searching them in the DB
        List<Long> topicIds = articleDTO.getTopics().stream()
                .map(topicName -> em.createQuery("SELECT t.id FROM Topic t WHERE LOWER(t.name) = LOWER(:name)", Long.class)
                        .setParameter("name", topicName)
                        .getResultStream()
                        .findFirst()
                        .orElse(null))
                .filter(id -> id != null)
                .toList();
        
        if (topicIds.size() != articleDTO.getTopics().size()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid topics provided.")
                    .build();
        }

        // Check if an article already exists with the same title to prevent duplicates
        boolean exists = em.createQuery("SELECT COUNT(a) FROM Article a WHERE a.title = :title", Long.class)
                .setParameter("title", articleDTO.getTitle())
                .getSingleResult() > 0;

        if (exists) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("An article with the same title already exists.")
                    .build();
        }
        
        // Validate body
        if (articleDTO.getBody() == null || articleDTO.getBody().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The body of the article cannot be null or blank.")
                    .build();
        }

        if (articleDTO.getBody().length() > 500) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The body of the article cant be longer than 500 characters.")
                    .build();
        }
        
        // Validate title
        if (articleDTO.getTitle() == null || articleDTO.getTitle().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The title of the article cannot be null or blank.")
                    .build();
        }

        if (articleDTO.getTitle().length() > 100) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The title of the article can't be longer than 100 characters.")
                    .build();
        }
        
        // Validate summary
        if (articleDTO.getSummary() == null || articleDTO.getSummary().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The summary of the article cannot be null or blank.")
                    .build();
        }

        if (articleDTO.getSummary().length() > 100) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The summary of the article can't be longer than 100 characters.")
                    .build();
        }
        
        // Validate imageURL length
        if (articleDTO.getImageURL() != null && articleDTO.getImageURL().length() > 255) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The image URL of the article can't be longer than 255 characters.")
                    .build();
        }  
        
        // Create the arcticle with the JSON/XML provided
        CustomPrincipal principal = (CustomPrincipal) securityContext.getUserPrincipal();
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setSummary(articleDTO.getSummary());
        article.setBody(articleDTO.getBody());
        article.setPublishedAt(new Date());
        article.setIsPrivate(articleDTO.getIsPrivate());
        article.setViews(0);
        article.setAuthorId(principal.getUserId());
        article.setTopicIds(topicIds);
        article.setImageURL(articleDTO.getImageURL());
        
        super.create(article);
        
        // Only return response status with the new article id
        return Response.status(Response.Status.CREATED)
                .entity("Article created with ID: " + article.getId())
                .build();
    }
}