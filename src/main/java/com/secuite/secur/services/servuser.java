package com.secuite.secur.services;

import com.secuite.secur.modeles.*;
import com.secuite.secur.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional

public class servuser {
    @Autowired
    private reponotification notificationRepository;
    @Autowired
    private final repouser user;
    @Autowired
    private  final reporole rol;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private  final repojury juri;
    @Autowired
    private  final reponote not;

    @Autowired
    private final reposoutenance sout;

    @Autowired
    private final repoadmin ad;




    @Autowired
    private  final repoformation formu;
    @Autowired
    private  final reponotification notif;

    @Autowired
    private  final repocriteres criteres;

    @Autowired
    private  final repopromo promotionn;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  final  repoetudiant etudiant;
    @Autowired
    private  final repoprojet projet;

//    public servuser(repouser repouser, reporole rol) {
//        this.user = repouser;
//        this.rol = rol;
//    }
    public utilisateur ajout(utilisateur u){
        // collaborateur u=new collaborateur(nom,mot,null);
        // Utilisateur co=new Utilisateur(null,nom,mot,new ArrayList<>());
        String a=u.getMot2passe();
        u.setMot2passe(passwordEncoder.encode(a));
        return user.save(u);
    }
    public promotion enregistrer (promotion p){
        return  promotionn.save(p);
    }
    public role ajouter(String nom){
        role r= new role(null,nom);
        return rol.save(r);
    }
//    public List<utilisateur> lire( String type) { // on retourne repository.la méthode(findAll) pour afficher tous les commentaires;
//        return user.findByDtype(type);
//    }
    public List<role> liree() { // on retourne repository.la méthode(findAll) pour afficher tous les commentaires;
        return rol.findAll();
    }
    public void addroleToUser(String username,String rolenom){
        utilisateur col=user.findByNom(username);
        System.out.println("le userrrrrrrrr");

        System.out.println(col.getIde());
        System.out.println(col.getNom());
        System.out.println("le rolbbbbbbe");

        role r=rol.findByRolename(rolenom);
        System.out.println(r.getId());
        System.out.println(r.getRolename());
        col.setRoles(r);
        System.out.println("le rollllllllllllllllllllleeeeeeee");
        System.out.println(col.getRoles());
        //col.setR((List<role>) r);
    }
    public utilisateur loadUserByName(String nom){
        return user.findByNom(nom);
    }

    public void ajoutele(String username,String promo){

        promotion  p= promotionn.findByNom(promo);
        System.out.println("ici la promo");
          System.out.println(p.getId());
          System.out.println(p.getNom());
        utilisateur col=user.findByNom(username);
       // col.getPromo().add( p);
        System.out.println("ici la promotionnnnnnnnnnnn");
        System.out.println(col.getPromo());

    }
    public  notification ajoutnoti(notification noti){
        return  notif.save(noti);
    }
    public void ajoutenotiification(String username,String promo){
        notification p=new notification();
        p= notif.findByTitre(promo);


        utilisateur col=user.findByNom(username);
//        col.getNotifications().add( p);

        System.out.println(col.getPromo());

    }
    public formation ajou(formation form){
        return formu.save(form);
    }

    public projet enregistrerprojet(projet m,String n){
        m.setEtudiant(etudiant.findByUsername(n));

        return projet.save(m);
    }

    public Etudiant enregistreretudiant(Etudiant e){

        String a=e.getMot2passe();
        e.setMot2passe(passwordEncoder.encode(a));
        return etudiant.save(e);
    }
    public jury ajoutjury(jury j){
        String a=j.getMot2passe();
        j.setMot2passe(passwordEncoder.encode(a));

        return juri.save(j);
    }
    public lesnotes ajoutnotes(lesnotes n){
        return not.save(n);
    }
    public criteresevaluation ajoutcriteres( criteresevaluation c){
        return criteres.save(c);

    }
    public utilisateur donneeutilisateur(int m){
        return  user.trouveinfoetudiant(m);
    }

//    public List<Etudiant> lire() { // on retourne repository.la méthode(findAll) pour afficher tous les commentaires;
//        return etudiant.findAll();
//    }
    public  utilisateur afficherdetailuser(int o){
        return user.trouveinfoetudiant(o);
    }

    public List<utilisateur> affichernoteetudiant(int idjury ,int ide){
        return user.notejuryparrapportaetudiant(idjury,ide);
    }




    public String generateUsername(String firstName, String lastName) {
        String username = firstName.toLowerCase() + lastName.toLowerCase();
        // vérifier si le nom d'utilisateur existe déjà dans la base de données
        if (user.findByUsername(username) != null) {
            int i = 1;
            while (user.findByUsername(username + i) != null) {
                i++;
            }
            username = username + i;
        }
        return username;
    }
    public void enreistererprojets(projet j){
        projet.save(j);
    }
    public void enreisterersout(soutenance j){
        sout.save(j);
    }
    public void enreisteradmin(admin j){
        ad.save(j);
    }

    public soutenance getProchaineSoutenance(int idJury) {
        String sql = " SELECT * FROM soutenance,soutenance_jurys,jury WHERE\n" +
                " soutenance_jurys.soutenance_id=soutenance.id and soutenance_jurys.jurys_id=jury.id\n" +
                "ORDER BY datedebut DESC, heuredeb DESC " +
                "LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new Object[] { idJury }, (rs, rowNum) -> {
            soutenance s = new soutenance();
            s.setId(rs.getInt("id"));
            s.setNom(rs.getString("nom"));
            s.setImg(rs.getString("img"));
            s.setDatedebut(rs.getDate("datedebut"));
            s.setHeuredeb(rs.getTime("heuredeb").toLocalTime());
            s.setHeurefin(rs.getTime("heurefin").toLocalTime());
            return s;
        });
    }
    public List<notification> getNotificationsByExpediteurAndDestinateur(String expediteur, String destinateur) {
        return notificationRepository.findByExpeditaireAndDestinateurOrderByDateenvoieDesc(expediteur, destinateur);
    };}
//    public List<participant> nn(){
//        //int id = 0;
//
//        String nom="";
//        String prenom="";
//        String mail="";
//        InputStream input= null;
//        try {
//            input = new FileInputStream("exo.xls");
//            POIFSFileSystem fs=new POIFSFileSystem(input);
//            HSSFWorkbook wb=new HSSFWorkbook(fs);
//            HSSFSheet sheet= wb.getSheetAt(0);
//            Iterator rows= sheet.rowIterator();
//            //int j = 0;
//            while (rows.hasNext()){
//                HSSFRow row= (HSSFRow) rows.next();
//                Iterator cells=row.cellIterator();
//                int i=0;
//                while (cells.hasNext()){
//
//                    HSSFCell cell= (HSSFCell) cells.next();
//                    /*if (i==0){
//                        id= (int) cell.getNumericCellValue();
//                    }*/
//                    if (i==0){
//                        nom=  cell.getStringCellValue();
//                    }
//                    else if (i==1){
//                        prenom=  cell.getStringCellValue();
//                    }
//                    else if (i==2){
//                        mail=  cell.getStringCellValue();
//                    }
//                    i++;
//
//                }
//                participant p = new participant(nom,prenom,mail,null);
//                //u1.save(p);
//                et.add(p);
//
//                //j++;
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return et;
//
//    }
//}
//


/*

Utiliser la méthode parse() de LocalTime :
 Cette méthode prend en entrée une chaîne de caractères qui doit être formatée selon le format
 standard ISO-8601 pour les heures, et renvoie une instance de LocalTime.
Copy code
LocalTime defenseTime = LocalTime.parse("09:00:00");

Il est important de noter que lorsque vous utilisez la méthode parse(), vous devez vous assurer que la chaîne de
 caractères est formatée correctement. Sinon, une exception de type DateTimeParseException sera levée.

Il est également important de vérifier que les valeurs utilisées pour initialiser une
instance de LocalTime sont valides, sinon une exception de type DateTimeException sera levée.


2---
Utiliser la méthode of() de LocalTime :
 Cette méthode prend en entrée les heures, les minutes et les secondes, et renvoie une instance de LocalTime.
Copy code
LocalTime defenseTime = LocalTime.of(9, 0, 0);


1---
Utiliser la méthode now() de LocalTime :
Cette méthode renvoie l'heure courante.
Copy code
LocalTime currentTime = LocalTime.now();
 */
