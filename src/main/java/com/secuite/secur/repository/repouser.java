package com.secuite.secur.repository;

import com.secuite.secur.modeles.promotion;
import com.secuite.secur.modeles.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface repouser extends JpaRepository<utilisateur, Integer> {
    utilisateur findByNom(String nom);
    utilisateur findByUsername(String userna);
//    List<utilisateur> findByDtype(String type);

    //affichage des details de l'etudiant pour que le jurie puisse voter mais ne marche pas
    @Transactional
    @Modifying
    @Query(value="SELECT projet.nomn ,utilisateur.id,utilisateur.nom,promotion.datedebut,promotion.nom,formation.nom,criteresevaluation.nom FROM projet,utilisateur,formation,criteresevaluation,promotion,utilisateur_promo WHERE utilisateur.id=projet.etudiant_id and utilisateur.id=:ide  AND promotion.idformation=formation.id AND formation.id=criteresevaluation.id",nativeQuery = true)
    utilisateur trouvejenesaitquelinfo(int ide);

// cette mehode marche elle affiche les details consernant l'etudiant
    @Query(value="SELECT projet.nomn ,utilisateur.id,utilisateur.nom,promotion.datedebut,promotion.nom,formation.nom,criteresevaluation.nom FROM projet,utilisateur,formation,criteresevaluation,promotion,utilisateur_promo WHERE utilisateur.id=projet.etudiant_id and utilisateur.id=:ida  ",nativeQuery = true)
    utilisateur trouveinfoetudiant(int ida);
   //les notes qu'un jury a donnee a un etudiant specifique methode a regler apres
   @Transactional
   @Modifying
    @Query(value="SELECT lesnotes.id,lesnotes.commentaires,utilisateur.id,criteresevaluation.coefficient,projet.id,projet.description FROM lesnotes,utilisateur,projet,criteresevaluation WHERE lesnotes.idjury=utilisateur.id AND projet.id=lesnotes.id AND projet.etudiant_id=:idjury and  criteresevaluation.id=lesnotes.idcriteres AND utilisateur.id=:ide",nativeQuery = true)
    List<utilisateur> notejuryparrapportaetudiant(int ide , int idjury);
   // la liste des notes d'un etudiant attribuees par tous les jurys
    @Transactional
    @Modifying
    @Query(value="SELECT utilisateur.id , lesnotes.id ,lesnotes.commentaires ,criteresevaluation.id,criteresevaluation.coefficient FROM utilisateur,lesnotes,criteresevaluation WHERE lesnotes.idjury=utilisateur.id AND lesnotes.idcriteres=criteresevaluation.id AND lesnotes.idetudiant=:ide",nativeQuery = true)
    List<utilisateur> lesnotesdeetudiantparrapportajury(int ide );
    // la liste des notes d'un etudiant attribuees par un jury specifique
    @Transactional
    @Modifying
    @Query(value="SELECT utilisateur.id , lesnotes.id ,lesnotes.note ,criteresevaluation.id,criteresevaluation.coefficient FROM utilisateur,lesnotes,criteresevaluation WHERE lesnotes.idjury=utilisateur.id AND lesnotes.idcriteres=criteresevaluation.id AND lesnotes.idetudiant=:etudiants and utilisateur.id=:ide",nativeQuery = true)
    List<Object[]> lesnotesdeetudiantparrapportaunjuwsxryspecifique(int etudiants,int ide );
    // la liste des notes d'un etudiant attribuees par rapport a tout les jurys
    @Transactional
    @Modifying
    @Query(value="SELECT  utilisateur.id , lesnotes.note ,lesnotes.commentaires ,criteresevaluation.ido,criteresevaluation.coefficient FROM utilisateur,lesnotes,criteresevaluation WHERE lesnotes.idjury=utilisateur.id AND lesnotes.idcriteres=criteresevaluation.ido AND lesnotes.idetudiant=:ide",nativeQuery = true)
    List<Object[]> lesnotesdeetudiantparrapportatoutjury(int ide );

  // la liste des etudiants par rapport a une promotion
//    @Transactional
//    @Modifying
    @Query(value="SELECT utilisateur.id,utilisateur.nom,utilisateur_promo.promo_id from utilisateur_promo,promotion,utilisateur WHERE utilisateur_promo.promo_id=promotion.id AND utilisateur_promo.utilisateur_id=utilisateur.id AND promotion.id =:ide",nativeQuery = true)
    List<Object[]> lesetudiiantsparrapportaunepromotion( @Param("ide") int ide );
    List<utilisateur> findByPromo(promotion promotion);
    @Transactional
    @Modifying
    @Query(value="SELECT *,1 as clazz_ from utilisateur ",nativeQuery = true)
//    int essay( @Param("ide") int ide );
    List<utilisateur>toute();

  // le nombre de jury par rapport a un etudiant
//    @Transactional
    //@Modifying
    @Query(value="SELECT COUNT(DISTINCT idjury) as nombre from lesnotes,utilisateur,criteresevaluation WHERE utilisateur.id=lesnotes.idetudiant and utilisateur.id=:ide and criteresevaluation.ido=lesnotes.idcriteres ",nativeQuery = true)
    int  lesjurysparrapportaletudiant(int ide );
    @Modifying
    @Transactional
    @Query(value="INSERT INTO `utilisateur_promo` (`utilisateur_id`, `promo_id`) VALUES(?,?)",nativeQuery = true)
    int find( int u, int date);
    @Query(value="SELECT etudiant.id, utilisateur.img,utilisateur.nom,utilisateur.prenom,projet.nomn from etudiant,utilisateur,projet,promotion,formation ,utilisateur_promo,role where utilisateur.idrole=role.id and projet.etudiant_id=utilisateur.id and utilisateur.id=utilisateur_promo.utilisateur_id and utilisateur_promo.promo_id=promotion.id and promotion.idformation=formation.id and promotion.id=:ide and role.id=2  AND etudiant.lu=0",nativeQuery = true)
    List<Object>etudiantlisteparpromotiondeformation(int ide );


    @Query(value="SELECT etudiant.id ,utilisateur.nom,utilisateur.prenom,utilisateur.img ,projet.nomn FROM etudiant,utilisateur,promotion,formation,utilisateur_promo,projet WHERE etudiant.id=utilisateur.id and utilisateur_promo.utilisateur_id=utilisateur.id and utilisateur_promo.promo_id=promotion.id and promotion.idformation=formation.id and promotion.id=:ide AND projet.etudiant_id=etudiant.id",nativeQuery = true)
    List<Object[]>etudiantlisteparpromotion(int ide );

    @Query(value="SELECT * FROM promotion p WHERE p.idformation =:ide AND p.datedebut <=:m AND p.datefin >=:m LIMIT 1 ",nativeQuery = true)
    Object  ladatedeformationrecent(Long ide, Date m);

    @Query(value="SELECT DISTINCT projet.nomn ,utilisateur.id,utilisateur.nom,utilisateur.prenom,projet.description FROM projet,utilisateur,formation,promotion WHERE utilisateur.id=projet.etudiant_id and utilisateur.id=:ide ",nativeQuery = true)
    Object  infoetudiant(int ide);

    @Query(value="SELECT DISTINCT criteresevaluation.ido,criteresevaluation.nom,utilisateur.id,promotion.datedebut FROM utilisateur,formation,criteresevaluation,promotion,utilisateur_promo WHERE utilisateur.id=:ide and utilisateur.id=utilisateur_promo.utilisateur_id and promotion.id=utilisateur_promo.promo_id and promotion.idformation=formation.id and criteresevaluation.idformation=formation.id ",nativeQuery = true)
     List<Object> criteresparuser  (int ide);

    @Query(value="SELECT AVG(note) as moyenne_generale, criteresevaluation.nom as nom_critere FROM lesnotes JOIN criteresevaluation ON lesnotes.idcriteres = criteresevaluation.ido JOIN etudiant ON lesnotes.idetudiant = etudiant.id WHERE etudiant.id =:ide GROUP BY criteresevaluation.ido",nativeQuery = true)
    List<Object> criteresparusernote  (int ide);

    @Query(value=" SELECT AVG(lesnotes.note) AS moyenne, utilisateur.nom, utilisateur.prenom,promotion.id FROM lesnotes,utilisateur,promotion where  lesnotes.idetudiant = utilisateur.id and promotion.id=:ide GROUP BY utilisateur.nom, utilisateur.prenom ORDER BY AVG(lesnotes.note) DESC ",nativeQuery = true)
    List<Object[]> lesnotesetudiantparordre( @Param("ide") int ide );
      // calcule notee 1 111111111111111111111111111111111111111111111111111
      @Query(value=" SELECT AVG(lesnotes.note) AS moyenne, utilisateur.nom, utilisateur.prenom, soutenance.nom AS nom_soutenance,utilisateur.email \n" +
              "FROM lesnotes ,soutenance,etudiant,utilisateur WHERE soutenance.id=etudiant.idsoutenance and lesnotes.idetudiant=etudiant.id and etudiant.id=utilisateur.id\n" +
              "\n" +
              "and soutenance.id =:ide\n" +
              "GROUP BY utilisateur.nom, utilisateur.prenom, nom_soutenance \n" +
              "ORDER BY AVG(lesnotes.note) DESC ",nativeQuery = true)
      List<Object[]> lesnotesetudiantparrapportsoutenance( @Param("ide") int ide );

    @Query(value=" SELECT AVG(lesnotes.note) AS moyenne, utilisateur.nom, utilisateur.prenom FROM lesnotes,utilisateur,promotion where  lesnotes.idetudiant = utilisateur.id and utilisateur.id=:ide GROUP BY utilisateur.nom, utilisateur.prenom  ",nativeQuery = true)
    Object laanotesetudiant( @Param("ide") int ide );

    @Query(value=" SELECT etudiant.*,utilisateur.nom,utilisateur.prenom,projet.nomn, utilisateur.img,utilisateur.mot2passe\n" +
            "            FROM soutenance s,soutenance_jurys,jury,utilisateur,etudiant ,projet WHERE soutenance_jurys.soutenance_id=s.id AND projet.etudiant_id=etudiant.id   and soutenance_jurys.jurys_id=jury.id  AND etudiant.id=utilisateur.id AND etudiant.lu=0 and\n" +
            "            jury.id=:ide AND etudiant.idsoutenance=s.id\n" +
            "            AND s.datedebut = CURRENT_DATE\n" +
            "            AND s.heuredeb < CURRENT_TIME\n" +
            "            AND s.heurefin > CURRENT_TIME  ",nativeQuery = true)
   List<Object>lasoutenancequiacommenceretpasencorefini(@Param("ide") int ide);
    @Query(value=" SELECT etudiant.*,utilisateur.nom,utilisateur.prenom,projet.nomn, utilisateur.img,utilisateur.mot2passe\n" +
            "            FROM soutenance s,soutenance_jurys,jury,utilisateur,etudiant ,projet WHERE soutenance_jurys.soutenance_id=s.id AND projet.etudiant_id=etudiant.id   and soutenance_jurys.jurys_id=jury.id  AND etudiant.id=utilisateur.id AND etudiant.lu=0 and\n" +
            "            jury.id=:ide AND etudiant.idsoutenance=s.id\n" +
            "            AND s.datedebut = CURRENT_DATE\n" +
            "            AND s.heuredeb < CURRENT_TIME\n" +
            "            AND s.heurefin > CURRENT_TIME  ",nativeQuery = true)
    List<Object>rlasoutenancequiacommenceretpasencorefini(@Param("ide") int ide);



    @Query(value="SELECT etudiant.*,utilisateur.nom,utilisateur.prenom,projet.nomn, utilisateur.img,utilisateur.mot2passe\n" +
            "            FROM soutenance s,soutenance_jurys,jury,utilisateur,etudiant ,projet WHERE soutenance_jurys.soutenance_id=s.id AND projet.etudiant_id=etudiant.id   and soutenance_jurys.jurys_id=jury.id  AND etudiant.id=utilisateur.id AND etudiant.lu=1 and\n" +
            "            jury.id=:ide AND etudiant.idsoutenance=s.id\n" +
            "            AND s.datedebut = CURRENT_DATE\n" +
            "            AND s.heuredeb < CURRENT_TIME\n" +
            "            AND s.heurefin > CURRENT_TIME  ",nativeQuery = true)
    List<Object>lasoutenancequiacommenceretpasencorefiniouetudiantnote(@Param("ide") int ide);


    @Query(value=" SELECT * FROM soutenance WHERE  heuredeb <=:a AND heurefin >=:a",nativeQuery = true)
    Object laanotesetu( LocalTime a);


    @Query(value=" SELECT soutenance.img,soutenance.id,soutenance.datedebut,soutenance.heuredeb,soutenance.heurefin,soutenance.nom FROM soutenance,soutenance_jurys,jury WHERE\n" +
            "            soutenance_jurys.soutenance_id=soutenance.id and soutenance_jurys.jurys_id=jury.id\n" +
            "            and jury.id =:ide\n" +
            "            ORDER BY datedebut DESC, heuredeb DESC",nativeQuery = true)
    List<Object>  historiquesoutenance( @Param("ide") int ide );


    @Query(value=" SELECT MIN(TIMESTAMPDIFF(SECOND, NOW(), CONCAT(datedebut, ' ', heuredeb))) AS time_until_next_soutenance\n" +
            "FROM soutenance ,soutenance_jurys,jury WHERE soutenance_jurys.soutenance_id=soutenance.id AND soutenance_jurys.jurys_id=jury.id \n" +
            "and jury.id =:ide ",nativeQuery = true)
    Long soutenancerestantpourjury( @Param("ide") int ide );

    @Query(value=" SELECT s.id,s.nom, s.img,s.datedebut,s.heuredeb,s.heurefin,soutenance_jurys.jurys_id\n" +
            "FROM soutenance s,jury,soutenance_jurys\n" +
            "WHERE datedebut = (SELECT MIN(datedebut)\n" +
            "                   FROM soutenance\n" +
            "                   WHERE datedebut >= CURRENT_DATE)\n" +
            "  AND heuredeb = (SELECT MIN(heuredeb)\n" +
            "                  FROM soutenance\n" +
            "                  WHERE datedebut = (SELECT MIN(datedebut)\n" +
            "                                     FROM soutenance\n" +
            "                                     WHERE datedebut >= CURRENT_DATE)\n" +
            "                    AND heuredeb >= CURRENT_TIME)\n" +
            "  AND  soutenance_jurys.soutenance_id=s.id AND soutenance_jurys.jurys_id=jury.id and jury.id=:ide",nativeQuery = true)
    Object soutenancereslaplusprochepourjury(@Param("ide") int ide );
    @Query(value=" SELECT soutenance.type FROM soutenance,etudiant ,utilisateur WHERE utilisateur.id=etudiant.id AND etudiant.idsoutenance=soutenance.id and etudiant.id=:ide ",nativeQuery = true)
    Object soutenanceetudiant( @Param("ide") int ide );

    @Query(value=" SELECT COUNT(DISTINCT ido) as nombre from utilisateur,promotion,formation,criteresevaluation ,utilisateur_promo,etudiant WHERE\n" +
            "promotion.idformation=formation.id AND utilisateur_promo.promo_id=promotion.id and utilisateur_promo.utilisateur_id=utilisateur.id and utilisateur.id=:ide ",nativeQuery = true)
    int countetudiantnombrecritere( @Param("ide") int ide );
    @Query(value=" SELECT DISTINCT idcriteres\n" +
            "FROM lesnotes,criteresevaluation,etudiant,jury\n" +
            "WHERE jury.id=lesnotes.idjury and lesnotes.idetudiant=etudiant.id and lesnotes.idcriteres=criteresevaluation.ido AND etudiant.id =:ide",nativeQuery = true)
    Object[] idcritereparetudiant( @Param("ide") int ide );

    @Query(value=" SELECT s.type   FROM soutenance s,soutenance_jurys,jury WHERE soutenance_jurys.soutenance_id=s.id   and  soutenance_jurys.jurys_id=jury.id   and  jury.id=:ide   AND s.datedebut = CURRENT_DATE  AND  s.heuredeb < CURRENT_TIME  AND s.heurefin > CURRENT_TIME ",nativeQuery = true)
    boolean soutenanceencoursetat ( @Param("ide") int ide );
    @Query(value=" SELECT s.id,s.nom,s.datedebut,s.heuredeb  FROM soutenance s,soutenance_jurys,jury WHERE soutenance_jurys.soutenance_id=s.id   and  soutenance_jurys.jurys_id=jury.id   and  jury.id=:ide  AND s.datedebut = CURRENT_DATE  AND  s.heuredeb < CURRENT_TIME  AND s.heurefin > CURRENT_TIME ",nativeQuery = true)
    Object soutenanceencours ( @Param("ide") int ide );

    @Query(value=" SELECT soutenance.type FROM etudiant,soutenance where etudiant.idsoutenance=soutenance.id and etudiant.id=:ide ",nativeQuery = true)
    boolean soutenancestatutpourunetudiant ( @Param("ide") int ide );
    @Query(value=" SELECT soutenance.id FROM etudiant,soutenance where etudiant.idsoutenance=soutenance.id and etudiant.id=:ide ",nativeQuery = true)
    Object soutenancesidpourunetudiant ( @Param("ide") int ide );
    @Query(value=" select etudiant.id,utilisateur.email, utilisateur.nom,utilisateur.prenom,utilisateur.username from soutenance,etudiant,utilisateur where soutenance.id=etudiant.idsoutenance and soutenance.id=:ide and utilisateur.id=etudiant.id",nativeQuery = true)
    Object[] listeetudiantparsoutenance( @Param("ide") int ide );
    @Query(value=" select etudiant.id,utilisateur.email, utilisateur.nom,utilisateur.prenom,utilisateur.username from soutenance,etudiant,utilisateur where soutenance.id=etudiant.idsoutenance and soutenance.nom=:ide and utilisateur.id=etudiant.id",nativeQuery = true)
    Object[] listeetudiantparsout( @Param("ide") String ide );

    @Query(value=" select soutenance.datedebut,soutenance.heuredeb,soutenance.heurefin, etudiant.id,utilisateur.email, utilisateur.nom,utilisateur.prenom,utilisateur.username from soutenance,etudiant,utilisateur where soutenance.id=etudiant.idsoutenance and  soutenance.nom=:ide and utilisateur.id=etudiant.id",nativeQuery = true)
    List<Object> listeetudiantparsoutenancenom( @Param("ide") String ide );

    @Query(value=" SELECT DISTINCT c.nom  FROM criteresevaluation c,utilisateur,etudiant,formation,soutenance  where c.idformation=formation.id and utilisateur.id=etudiant.id and etudiant.idsoutenance=soutenance.id AND soutenance.nom=:ide",nativeQuery = true)
    List<Object> listecriteresparsoutenancenom( @Param("ide") String ide );

    @Query(value=" SELECT soutenance.nom FROM soutenance WHERE datedebut > NOW();",nativeQuery = true)
    List<Object> listedesnomdesoutenanceavenir();
    @Query(value=" SELECT criteresevaluation.ido,criteresevaluation.nom,criteresevaluation.coefficient FROM criteresevaluation,formation WHERE criteresevaluation.idformation=formation.id AND formation.nom=:ide",nativeQuery = true)
    List<Object> listedescritereparformation(@Param("ide") String ide );
    @Query(value=" SELECT jury.id,utilisateur.nom,utilisateur.prenom,utilisateur.email FROM jury,utilisateur,soutenance,soutenance_jurys WHERE soutenance.id=soutenance_jurys.soutenance_id AND jury.id=soutenance_jurys.jurys_id AND \n" +
            "jury.id=utilisateur.id AND soutenance.id=:ide",nativeQuery = true)
     List<Object> juryparsoutenance( @Param("ide") int ide );
    @Query(value=" SELECT etudiant.id,utilisateur.nom,utilisateur.prenom,utilisateur.email FROM etudiant,utilisateur,soutenance WHERE soutenance.id=etudiant.idsoutenance AND \n" +
            "etudiant.id=utilisateur.id AND soutenance.id=:ide",nativeQuery = true)
    List<Object> listeetudiantparrapportasoutenance( @Param("ide") int ide );
    @Query(value=" SELECT promotion.id FROM utilisateur,promotion,utilisateur_promo WHERE  utilisateur_promo.utilisateur_id=utilisateur.id and utilisateur_promo.promo_id=promotion.id and utilisateur.id=:ide",nativeQuery = true)
    Object promoparetudiant( @Param("ide") int ide );


    @Query(value=" SELECT etudiant.id,utilisateur.nom,utilisateur.prenom,utilisateur.email FROM soutenance ,utilisateur,etudiant WHERE utilisateur.id=etudiant.id AND etudiant.idsoutenance=soutenance.id AND soutenance.nom=:ide",nativeQuery = true)
    List<Object> listeetudiantparrapportasoutenanceee( @Param("ide") String ide );


    // requete de calcule de pourcentage d'une soutenance autre
    @Query(value="SELECT \n" +
            "                 \n" +
            "                  SUM(n.valider)/COUNT(n.id)*100 AS taux_progression \n" +
            "            FROM criteresevaluation c ,soutenance ,lesnotes n ,etudiant WHERE n.idcriteres=c.ido and n.idetudiant=etudiant.id AND soutenance.id=etudiant.idsoutenance AND soutenance.nom=:ide\n" +
            "            \n" +
            "            GROUP BY c.ido;",nativeQuery = true)
   List<Object>  pourcentagesoutenance( @Param("ide") String ide );

    // requete de calcule de pourcentage d'une soutenance facon1
    @Query(value="SELECT \n" +
            "     SUM(n.note)/COUNT(n.id)*10 AS taux_progression\n" +
            "FROM \n" +
            "    soutenance s,lesnotes n,etudiant,criteresevaluation c ,jury,soutenance_jurys WHERE jury.id=soutenance_jurys.jurys_id AND soutenance_jurys.soutenance_id=s.id and etudiant.idsoutenance=s.id and n.idetudiant=etudiant.id AND c.ido=n.idcriteres and s.nom=:ide" +
            "    \n" +
            "GROUP BY \n" +
            "    c.ido, c.nom",nativeQuery = true)
    List<Object>  pourcentagesoutenancefacon1( @Param("ide") String ide );

    @Query(value=" SELECT jury.id,utilisateur.nom,utilisateur.prenom,utilisateur.img,utilisateur.email FROM jury,utilisateur,soutenance,soutenance_jurys WHERE soutenance.id=soutenance_jurys.soutenance_id AND jury.id=soutenance_jurys.jurys_id AND \n" +
            "            jury.id=utilisateur.id AND soutenance.nom=:ide",nativeQuery = true)
    List<Object> juryparsoutenancenom( @Param("ide") String ide );
    @Query(value="SELECT COUNT(DISTINCT jury.id)  FROM jury,soutenance,soutenance_jurys WHERE soutenance.id=soutenance_jurys.soutenance_id AND jury.id=soutenance_jurys.jurys_id AND \n" +
            "                        soutenance.nom=:ide ",nativeQuery = true)
    int  juryssommeparrapportasoutenance(String ide );

    @Query(value=" SELECT COUNT(DISTINCT c.ido)  FROM criteresevaluation c,utilisateur,etudiant,formation,soutenance  where c.idformation=formation.id and utilisateur.id=etudiant.id and etudiant.idsoutenance=soutenance.id AND soutenance.nom=:ide ",nativeQuery = true)
    int  criteresommeparrapportasoutenance(String ide );
    @Query(value=" SELECT COUNT(DISTINCT formation.id) from formation ",nativeQuery = true)
    int  formationcount();
    @Query(value=" SELECT COUNT(DISTINCT promotion.id) from promotion ",nativeQuery = true)
    int  promotiontioncount( );
    @Query(value=" SELECT COUNT(DISTINCT soutenance.id) from soutenance ",nativeQuery = true)
    int  soutenancecount( );
    @Query(value=" SELECT COUNT(DISTINCT etudiant.id) from etudiant ",nativeQuery = true)
    int  etudiantcount( );

    @Query(value="SELECT COUNT(DISTINCT soutenance.id) as nombre from jury,soutenance ,soutenance_jurys WHERE jury.id=soutenance_jurys.jurys_id and soutenance_jurys.soutenance_id=soutenance.id and jury.id=:ide",nativeQuery = true)
    int  lesjurysparrappor(int ide );

    @Query(value="SELECT COUNT(DISTINCT etudiant.id) as nombre\n" +
            "FROM soutenance s,soutenance_jurys,jury,utilisateur,etudiant ,projet WHERE soutenance_jurys.soutenance_id=s.id AND projet.etudiant_id=etudiant.id   and soutenance_jurys.jurys_id=jury.id  AND etudiant.id=utilisateur.id AND etudiant.lu=0 and\n" +
            "                        jury.id=:ide AND etudiant.idsoutenance=s.id\n" +
            "                       AND s.datedebut = CURRENT_DATE\n" +
            "                        AND s.heuredeb < CURRENT_TIME\n" +
            "                     AND s.heurefin > CURRENT_TIME",nativeQuery = true)
    int  lesjurysparrapportaetudiantlu(int ide );


    @Query(value="SELECT COUNT(DISTINCT etudiant.id) as nombre\n" +
            "FROM soutenance s,soutenance_jurys,jury,utilisateur,etudiant ,projet WHERE soutenance_jurys.soutenance_id=s.id AND projet.etudiant_id=etudiant.id   and soutenance_jurys.jurys_id=jury.id  AND etudiant.id=utilisateur.id AND etudiant.lu=1 and\n" +
            "                        jury.id=:ide AND etudiant.idsoutenance=s.id\n" +
            "                       AND s.datedebut = CURRENT_DATE\n" +
            "                        AND s.heuredeb < CURRENT_TIME\n" +
            "                     AND s.heurefin > CURRENT_TIME",nativeQuery = true)
    int  lesjuryspar(int ide );
    @Query(value=" SELECT s.id,s.nom, s.img,s.datedebut,s.heuredeb,s.heurefin,soutenance_jurys.jurys_id\n" +
            "            FROM soutenance s,jury,soutenance_jurys\n" +
            "            \n" +
            "                               WHERE datedebut >= CURRENT_DATE\n" +
            "             \n" +
            "                                AND heuredeb >= CURRENT_TIME\n" +
            "             AND  soutenance_jurys.soutenance_id=s.id AND soutenance_jurys.jurys_id=jury.id and jury.id=:ide ORDER BY s.datedebut ASC, s.heuredeb ASC;",nativeQuery = true)
    List<Object> listehisto(@Param("ide") int ide );

    @Query(value=" SELECT DISTINCT etudiant.id,utilisateur.nom,utilisateur.prenom,projet.nomn FROM utilisateur,etudiant,soutenance,projet WHERE utilisateur.id=etudiant.id and etudiant.idsoutenance=soutenance.id AND projet.etudiant_id=etudiant.id AND soutenance.id=:ide",nativeQuery = true)
    List<Object> listeetudiantparsoutenanceautre(@Param("ide") int ide );

    @Query(value=" SELECT e.id,utilisateur.nom,utilisateur.prenom, AVG(lesnotes.note) as moyenne\n" +
            "FROM etudiant e, lesnotes, criteresevaluation ,utilisateur\n" +
            "WHERE lesnotes.idetudiant = e.id AND criteresevaluation.ido = lesnotes.idcriteres AND utilisateur.id=e.id\n" +
            "GROUP BY e.id\n" +
            "ORDER BY moyenne DESC\n" +
            "LIMIT 5;",nativeQuery = true)
    List<Object> listemeilleuretudiant( );

    @Query(value=" select soutenance.resultat FROM soutenance WHERE soutenance.id=:ide",nativeQuery = true)
    boolean etudiantresultatsoutenance(@Param("ide") int ide );
}