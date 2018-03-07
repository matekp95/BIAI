/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.web_app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Kamil
 */
@Repository
public class KotDAO {
    List<Kot> koty = new ArrayList<>();
    @Autowired 
    private DataSource dataSource;
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional
    public void dodajKota(Kot kot) {
        
            entityManager.merge(kot);
            koty.add(kot);
    }
    
    @Transactional
    public List<Kot> getKoty() {
        koty.clear();

        getKotyJPLA();
        //getKotyJDBC();
        return koty;
    }
    
    @Transactional
    public Kot getKotById(Integer id) {
            if (id<koty.size()) {
                return entityManager.find(Kot.class, id+1);
                //return koty.get(id);
            } else {
                return null;
            }
    }
    @Transactional
    private void getKotyJPLA() {
            Query query = entityManager.createQuery("SELECT k FROM Kot k");
            List<Kot> kots = query.getResultList();
            this.koty.addAll(kots);
    }

    private void getKotyJDBC() {
         String sql = "SELECT * FROM KOTY"; 
            Connection conn = null; 
            try
                { 
                    conn = dataSource.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    
                     Kot kot = null; 
                     ResultSet rs = ps.executeQuery(); 
                     while (rs.next()) 
                        { 
                            kot = new Kot();
                            kot.setImie(rs.getString("imie"));
                            kot.setDataUrodzenia(rs.getDate("dataUrodzenia"));
                            kot.setWaga(rs.getFloat("waga"));
                            kot.setImieOpiekuna(rs.getString("imieOpiekuna"));
                            koty.add(kot);
                           
                        }
                     rs.close();
                     ps.close();
                     
                }
            catch (SQLException e) 
            { 
                throw new RuntimeException(e); 
            } finally 
            { if (conn != null) 
            { try 
            { 
                conn.close(); } catch (SQLException e) {} }
            }
    }
}
