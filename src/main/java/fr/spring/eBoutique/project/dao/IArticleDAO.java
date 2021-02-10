package fr.spring.eBoutique.project.dao;


import fr.spring.eBoutique.project.model.Article;

import java.util.List;

public interface IArticleDAO {

	public List<Article> getArticles(Integer idUtilisateur);
	public Article addArticle(Article article);
	public void removeArticle(Integer id);
	public void clear (Integer idUtilisateur);
	

}