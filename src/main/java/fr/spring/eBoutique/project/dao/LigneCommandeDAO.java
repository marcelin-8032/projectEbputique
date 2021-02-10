package fr.spring.eBoutique.project.dao;


import fr.spring.eBoutique.project.BDD.DataSourceConnexion;
import fr.spring.eBoutique.project.model.LigneCommande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDAO implements ILigneCommandeDAO {
	
		private Connection connexion= null;
			
			public LigneCommandeDAO() {
				if (connexion == null) {
					try {
						connexion= DataSourceConnexion.getInstance().getConnection();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
//--------------------------------------------------------------------------
	@Override
	public List<LigneCommande> getLignesCommande(Integer idCommande) {

		List<LigneCommande> listeLigneCom = null;
		ResultSet rs=null;
		
		try {
			String query = "SELECT * FROM ligne_commande WHERE commande_id=?";
			Statement stm = connexion.createStatement();
			rs=stm.executeQuery(query);
			
			if (rs !=null) {
				listeLigneCom = new ArrayList<LigneCommande>();
				while (rs.next()) {
					LigneCommande lignecommande= new LigneCommande();
					lignecommande.setId(rs.getInt("id"));
					lignecommande.setQuantite(rs.getInt("quantite"));
					
					listeLigneCom.add(lignecommande);
					}	
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connexion !=null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		return listeLigneCom;
}



	//--------------------------------------------------------------------------
	@Override
	public LigneCommande addLigneCommande(LigneCommande ligneCommande) {
		
		LigneCommande ligneCommandAdded=null;
		
		try {
			String requete="INSERT INTO ligne_commande (quantite) VALUES (?)";
		    PreparedStatement ps=connexion.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		
		    ps.setInt(1, ligneCommande.getQuantite());
			ps.executeUpdate();
			
			ligneCommandAdded = ligneCommande;
		
		    ResultSet rs=ps.getGeneratedKeys();
			if (rs.next()) {
				ligneCommandAdded.setId(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (connexion !=null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		return ligneCommandAdded;	
	}	

}