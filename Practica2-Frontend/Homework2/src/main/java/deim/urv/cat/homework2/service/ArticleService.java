/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Article;
import java.util.List;
/**
 *
 * @author oupma
 */
public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Long getLastArticleId(Long userId);
    List<Article> searchArticles(String query, List<String> topics, String author);
}
