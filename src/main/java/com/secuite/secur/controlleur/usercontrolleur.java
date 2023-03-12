package com.secuite.secur.controlleur;


import com.secuite.secur.EmailSenderService;
import com.secuite.secur.modeles.*;
import com.secuite.secur.repository.*;
import com.secuite.secur.services.servuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.time.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class usercontrolleur {


    @Autowired
    private final servuser service;
    @Autowired
    private final reporole rol;

    @Autowired
    private final reponote notte;

    @Autowired
    private final reponotification notif;

    @Autowired
    private final repoprojet proj;

    @Autowired
    private final repoadmin ads;

    @Autowired
    private final reposoutenance sout;


    @Autowired
    private final repojury jur;

    @Autowired
	private EmailSenderService senderService;
    @Autowired
    private final repoetudiant etudiant;
    @Autowired
    private final reposoutenance soutenan;


    @Autowired
    private final repopromo promo;

    @Autowired
    private final repoformation  format;

    @Autowired
    private final repouser user;

    public usercontrolleur(servuser service, reporole rol, reponote notte, reponotification notif, repoprojet proj, repoadmin ads, reposoutenance sout, repojury jur, EmailSenderService senderService, repoetudiant etudiant, reposoutenance soutenan, repopromo promo, repoformation format, repouser user, repojury jurys, repocriteres critere) {
        this.service = service;
        this.rol = rol;
        this.notte = notte;
        this.notif = notif;
        this.proj = proj;
        this.ads = ads;
        this.sout = sout;
        this.jur = jur;
        this.senderService = senderService;
        this.etudiant = etudiant;
        this.soutenan = soutenan;
        this.promo = promo;
        this.format = format;
        this.user = user;
        this.jurys = jurys;
        this.critere = critere;
    }
//    public usercontrolleur(servuser service, reporole rol, reponote notte, reponotification notif, repoprojet proj, repoadmin ads, reposoutenance sout, repojury jur, repoetudiant etudiant, reposoutenance soutenan, repopromo promo, repoformation format, repouser user, repojury jurys, repocriteres critere) {
//
//        this.service = service;
//        this.rol = rol;
//        this.notte = notte;
//        this.notif = notif;
//        this.proj = proj;
//        this.ads = ads;
//        this.sout = sout;
//        this.jur = jur;
//        this.etudiant = etudiant;
//        this.soutenan = soutenan;
//        this.promo = promo;
//        this.format = format;
//        this.user = user;
//        this.jurys = jurys;
//        this.critere = critere;
//    }

    @Autowired
    private final repojury jurys;

    @Autowired
    private final repocriteres critere;


    @PostMapping("/ajoutetudiant/{nom}/{prenom}/{username}/{mot2passe}/{email}/{prom}/{nomsout}")
    // @PreAuthorize("hasRole('ADMIN')")
    public Object create(@RequestParam("file") MultipartFile file, @PathVariable String nom, @PathVariable String prenom, @PathVariable String username, @PathVariable String mot2passe,@PathVariable String email, @PathVariable("prom") int prom,@PathVariable String nomsout) {
        String a = file.getOriginalFilename();
        role r = rol.findByRolename("etudiant");
        System.out.println("la promotionnnnnn");
        System.out.println("alors la promotion marccheeee");
        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        System.out.println("le path n'as pas de probleme");
        try {
            System.out.println("on est rentrer dans try");
            //  Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
            System.out.println("la c'est essay etudiant");
            Etudiant o = new Etudiant();
            //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
            System.out.println("on va tester la promotion");
            System.out.println(o.getPromo());
            o.setNom(nom);
            o.setLu(false);
            o.setPrenom(prenom);
            o.setEmail(email);
            o.setUsername(service.generateUsername(prenom,nom));
            o.setMot2passe("Orange2023");
            o.setSoutenances(soutenan.findByNom(nomsout));
            System.out.println("on va tester la promotion encore");
            // o.getPromo().add(p);
            System.out.println("on va tester la promotion");
            // o.setImg(a);
            o.setRoles(r);
            System.out.println(o.getPromo());
            System.out.println(o.getNom());
            if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                return "veillez remplir tout les champs ";
            } else{
                System.out.println("le moment de blocker ");
                System.out.println(o.getNom());

                utilisateur m= user.findByNom(o.getNom());
                //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                promotion   p=promo.findById(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                if (o.getPromo() == null) {
                    //  o.setPromo(new ArrayList<Promotion>(p));
                    o.setPromo(new ArrayList<promotion>());
                    // o.setPromo((Collection<promotion>) p);
                    o.getPromo().add(p);
                    service.enregistreretudiant(o);
//                    senderService.sendSimpleEmail("fantisca747@gmail.com","kmahamadou858@gmail.com",
//                            "This is email body",
//                            "This is email subject");
                }
                else {
                    System.out.println("erreurrrrrrrrr");
                    // gérer le cas où la propriété promo n'est pas définie
                }



                // o.setPromo((Collection<promotion>)p);
                //  o.getPromo().add(p);


                // service.ajoutele(nom,prom);
                //o.setUsername(service.generateUsername(nom,prenom));
                //  o.getPromo().add(promo.findById(1));

                System.out.println("la relationnnnnn");

                // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                ;
                System.out.println(o.getPromo());
                return "bien enregistrer";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/ajoutjury/{nom}/{prenom}/{username}/{mot2passe}/{email}/{prom}/{voir}")
    // @PreAuthorize("hasRole('ADMIN')")
    public Object createjury( @PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("username") String username, @PathVariable("mot2passe") String mot2passe,@PathVariable  String email, @PathVariable("prom") String prom,@PathVariable String voir) {
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getContentType());
//        System.out.println(file.getSize());
//        String a = file.getOriginalFilename();
//        role r = rol.findByRolename("jury");
//        promotion p = promo.findByNom(prom);
//
//        String Path = "C:\\Users\\Fatoumata DEMBELE\\Downloads\\projetsoutenance\\src\\main\\resources\\img";
//        try {
//            Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
////            jury o = new jury((int) 0, nom, prenom, username, a, mot2passe, r, (Collection<promotion>) promo, null);
////            if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) || p == null) {
////                return "veillez remplir tout les champs ";
////            } else{
////                o.setUsername(service.generateUsername(nom,prenom));
////                return service.ajoutjury(o);
////            }
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getContentType());
//        System.out.println(file.getSize());
//        String a = file.getOriginalFilename();
        role r = rol.findByRolename(voir);
        System.out.println("la promotionnnnnn");
        //  promotion p = promo.findById(prom);
//        System.out.println(p.getNom());
//        System.out.println(p.getDatefin());
        System.out.println("alors la promotion marccheeee");

        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        System.out.println("le path n'as pas de probleme");
        try {
            System.out.println("on est rentrer dans try");
            //   Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
            System.out.println("la c'est essay etudiant");
            if (voir.equals("jury")){
                jury o = new jury();
                //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
                System.out.println("on va tester la promotion");
                System.out.println(o.getPromo());
                o.setNom(nom);
                o.setPrenom(prenom);
                o.setUsername(service.generateUsername(prenom,nom));
                o.setMot2passe("Orange2023");
                o.setEmail(email);
                System.out.println("on va tester la promotion encore");
                // o.getPromo().add(p);
                System.out.println("on va tester la promotion");
                //  o.setImg(a);
                o.setRoles(r);
                System.out.println(o.getPromo());
                System.out.println(o.getNom());
                if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                    return "veillez remplir tout les champs ";
                } else{
                    System.out.println("le moment de blocker ");
                    System.out.println(o.getNom());

                    utilisateur m= user.findByNom(o.getNom());
                    //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                    promotion   p=promo.findByNom(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                    if (o.getPromo() == null) {
                        //  o.setPromo(new ArrayList<Promotion>(p));
                        o.setPromo(new ArrayList<promotion>());
                        // o.setPromo((Collection<promotion>) p);
                        o.getPromo().add(p);
                        service.ajoutjury(o);
//                        senderService.sendSimpleEmail("fantisca747@gmail.com","kmahamadou858@gmail.com",
//                                "This is email body",
//                                "This is email subject");
                    }
                    else {
                        System.out.println("erreurrrrrrrrr");
                        // gérer le cas où la propriété promo n'est pas définie
                    }



                    // o.setPromo((Collection<promotion>)p);
                    //  o.getPromo().add(p);


                    // service.ajoutele(nom,prom);
                    //o.setUsername(service.generateUsername(nom,prenom));
                    //  o.getPromo().add(promo.findById(1));

                    System.out.println("la relationnnnnn");

                    // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                    ;
                    System.out.println(o.getPromo());
                    return "bien enregistrer";
                }

            }
            else if (voir.equals("admin")){
                admin o = new admin();
                //Etudiant o = new Etudiant( nom, prenom, username,  mot2passe, r, (Collection<promotion>) p);
                System.out.println("on va tester la promotion");
                System.out.println(o.getPromo());
                o.setNom(nom);
                o.setPrenom(prenom);
                o.setUsername(service.generateUsername(prenom,nom));
                o.setMot2passe(mot2passe);
                o.setEmail(email);
                System.out.println("on va tester la promotion encore");
                // o.getPromo().add(p);
                System.out.println("on va tester la promotion");
                o.setRoles(r);
                System.out.println(o.getPromo());
                System.out.println(o.getNom());
                if (nom.equals(null) || prenom.equals(null) || username.equals(null) || mot2passe.equals(null) ) {
                    return "veillez remplir tout les champs ";
                } else{
                    System.out.println("le moment de blocker ");
                    System.out.println(o.getNom());

                    utilisateur m= user.findByNom(o.getNom());
                    //   p= new promotion(2,"dfgh",new Date(),new Date(),new formation());
                    promotion   p=promo.findByNom(prom);
//                if (o.getPromo() != null) {
//                    o.getPromo().add(p);
//                }
                    if (o.getPromo() == null) {
                        //  o.setPromo(new ArrayList<Promotion>(p));
                        o.setPromo(new ArrayList<promotion>());
                        // o.setPromo((Collection<promotion>) p);
                        o.getPromo().add(p);
                        service.enreisteradmin(o);
                    }
                    else {
                        System.out.println("erreurrrrrrrrr");
                        // gérer le cas où la propriété promo n'est pas définie
                    }



                    // o.setPromo((Collection<promotion>)p);
                    //  o.getPromo().add(p);


                    // service.ajoutele(nom,prom);
                    //o.setUsername(service.generateUsername(nom,prenom));
                    //  o.getPromo().add(promo.findById(1));

                    System.out.println("la relationnnnnn");

                    // o.getPromo().add(p);
//                promotion pi=promo.findByNom(prom);
//                user.find(o.getIde(),pi.getId()
                    ;
                    System.out.println(o.getPromo());
                    return "bien enregistrer";
                }
            }
            else {
                return "erreur de gestion de role";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }

//        @GetMapping("/readea")
    // @PreAuthorize("hasRole('USER')")
//    public List<utilisateur> readd( String type){
//        return service.lire(type);
//    }


    @GetMapping("/etudiantparid/{n}")
    public  Etudiant  etudiantparid(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        return etudiant.findByIde(n);
    }
    @GetMapping("/ajoutnote/{idprojet}/{idjury}/{nom}/{criterenom}/{note}/{commentaire}")
//     @PreAuthorize("hasRole('ADMIN')")
    public Object createjury(@PathVariable("idprojet") int idproget,@PathVariable("idjury") int idjury, @PathVariable("nom") String nom, @PathVariable("criterenom") int criterenom, @PathVariable("note") int note, @PathVariable("commentaire") String commentaire) {
        System.out.println("le etudiantttttttttttt");

        Etudiant m= etudiant.findByIde(idproget);
        System.out.println(m);


        jury r=    jur.findByIde(idjury);
        System.out.println("le juryyyyyyyyyyyyyyyyy");
        System.out.println(r);
        criteresevaluation c=  critere.findByIdo(criterenom);
        lesnotes mo=new lesnotes(null,note,true,commentaire,new Date(),m,r,c);
        return service.ajoutnotes(mo);
    }
    @GetMapping("/readea")
    public Object infouser(int o) {
        return service.donneeutilisateur(o);
    }

    @GetMapping("/readea/{o}/{m}")
    public Object affichernoteetudiant(@PathVariable int o, @PathVariable int m) {
        return service.affichernoteetudiant(o, m);
    }

    @GetMapping("/readea/{o}")
    public Object afficherdetailetudiant(@PathVariable int o) {
        return service.afficherdetailuser(o);
    }

//    @PostMapping("/ajoutjury/{nom}/{prenom}/{username}/{mot2passe}/{prom}")
    // @PreAuthorize("hasRole('ADMIN')")
//  public Object createjury(@PathVariable String nomprojet,@PathVariable String nomjury, @PathVariable String nomcritere, @RequestBody int note, @RequestBody String commentaire,  @RequestBody String prom) {
//       projet j=proj.findByNom(nomprojet);
//       jury u=jurys.findByNom(nomjury);
//       role i=u.getRoles();
//       criteresevaluation c=critere.findByNom(nomcritere);

//       if (i.getRolename()=="jury"){
//           lesnotes um=new lesnotes(null,note,commentaire,new Date(),j,u,c);
//           service.ajoutnotes(um);
//        return "enregistrement effectuee avec succes";
//}
//    else {
//    return "vous n'etes pas un jury";
//    }
//    }
//    @PostMapping("/readea/{o}")
//    public Double calculenoteetudiantparlastarfatoumata(@PathVariable Long o,@PathVariable Long g) {
////        return service.afficherdetailuser(o);
//        Double t=0.0; Double coef=0.0; Double m;
//        List<Object[]> notes = user.lesnotesdeetudiantparrapportaunjuwsxryspecifique(o, g);
//        for (Object[] note : notes) {
//            Long userId = (Long) note[0];
//            int noteId = (int) note[1];
//            String comment = (String) note[2];
//            Long criteriaId = (Long) note[3];
//            int coefficient = (int) note[4];
//            t=t+noteId;
//            coef= coef+coefficient;

    // do something with the data
//            System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
//        }
//
//        return t/coef;
//    }
    @PostMapping("/readea/{o}")
    public Double calculenotetotaletudiantparrapportajoliejury(@PathVariable("o") int o) {
//        return service.afficherdetailuser(o);
        Double t=0.0; Double coef=0.0; Double m; int
                a;
        System.out.println("bienvenue");
        List<Object[]> notes = user.lesnotesdeetudiantparrapportatoutjury(o);
        System.out.println("maiintenant on rentre dans la boucle");
        for (Object[] note : notes) {
            int userId = (int) note[0];
            int noteId = (int) note[1];
            System.out.println("la liste3");
            String comment = (String) note[2];
            int criteriaId = (int) note[3];
            int coefficient = (int) note[4];
            t=t+noteId;
            System.out.println("la valeur de t");
            System.out.println(t);
//            coef= coef+coefficient;
            // do something with the data
            System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
        }
        System.out.println("la valeur total de t");
        System.out.println(t);
        a=  user.lesjurysparrapportaletudiant(o);
        return t/a;
    }
    @GetMapping("/lalistedesresultat/{o}")
    public List<resultatvote> jesperfonctionner(@PathVariable("o") int o) {
        System.out.println("la listeeeeeeeeeeeeeeee");
        // System.out.println(o);
        List <Object[]> liste=user.lesetudiiantsparrapportaunepromotion(o);
        System.out.println(o);
        System.out.println("aussi");
        System.out.println(liste);
        List<resultatvote> l = new ArrayList<>();
        System.out.println("aussi2");
        int i=0;
        for (Object[] etud : liste) {
            System.out.println("la liste3");
            int userid = (int) etud[0];
            System.out.println("la liste4");
            System.out.println(userid);
            String nom = (String) etud[1];
            System.out.println("la liste5");
            System.out.println(nom);
            //String nomprojet = (String) etud[2];
            System.out.println("la liste6");
            //  System.out.println(nomprojet);
            Double t = 0.0;
            Double coef = 0.0;
            System.out.println("la liste2");
            Double m;
            List<Object[]> notes = user.lesnotesdeetudiantparrapportatoutjury(userid);
            for (Object[] note : notes) {
                int userId = (int) note[0];
                int noteId = (int) note[1];
                System.out.println("la liste3");
                String comment = (String) note[2];
                int criteriaId = (int) note[3];
                int coefficient = (int) note[4];
                t=t+noteId;
                System.out.println("la valeur de t");
                coef= coef+coefficient;
                // do something with the data
                System.out.println("userId :" + userId + " noteId :" + noteId + " comment :" + comment + " criteriaId :" + criteriaId + " coefficient :" + coefficient);
            }
            int   a=  user.lesjurysparrapportaletudiant(userid);
            Double r= t/a;
            System.out.println("voici le t");
            System.out.println(t);
            System.out.println("voici le t");
            System.out.println(t);
            System.out.println("voici le r");
            System.out.println(r);
            resultatvote result=new resultatvote();
            result.setNom(nom);
            result.setPrenom("nomduprojet");
            result.setAutrenom("nompromotionici");
            result.setResultat(r);
            System.out.println(result.getResultat());
            l.add(result);
        }
        return  l;
    }
    @GetMapping("/lalistealors/{p}")
    public Object listi( @PathVariable int p){
        System.out.println("la listeeeeeeeeeeeeeeee");
//        user.essay(o);
//        List<Object> liste=new ArrayList<>();
//        liste=user.lesetudiiantsparrapportaunepromotion(o);
//        notte.findByEtudiantAndJurysAndCriteres(etudiant.findByIde(o),jur.findByIde(m),critere.findByIdo(c));
        System.out.println("la listeeeeeeeeeeeeeeee");
        // System.out.println(user.essay(o));
        return  user.lesjurysparrapportaletudiant(p);            //  notte.findByEtudiantAndJurysAndCriteres(etudiant.findByIde(o),jur.findByIde(m),critere.findByIdo(c));
    }
    @PostMapping("/creationpromo/{nom}/{datedebut}/{datefin}/{forma}")
    public void creerpromo(@PathVariable String nom,@PathVariable String datedebut,@PathVariable String datefin,@PathVariable String forma) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        formation form=format.findByNom(forma);
        promotion p=new promotion(0,nom,new Date(sdf.parse(datedebut).getTime()),new Date(sdf.parse(datefin).getTime()),form);
        if (promo.findByNom(nom)!=null){
        }
        else {
            service.enregistrer(p);

        }
    }
    // ajout critere evaluationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
    @PostMapping("/creationpromo/{nom}/{datedebut}/{forma}")
    public String enregistrercritereevaluations(@PathVariable String nom,@PathVariable int datedebut,@PathVariable String forma) throws ParseException {
        formation form=format.findByNom(forma);
        criteresevaluation e=new criteresevaluation(0,nom,datedebut,form);
        if(critere.findByNomAndFormations(nom,form)!=null){
            return  "ce criteres existe deja dans cette formation";
        }
        else {
            service.ajoutcriteres(e);
            return "critere enregistrer  dans cette formation avec succes";
        }
    }
    @PostMapping("/creationprojet/{nom}/{datedebut}/{forma}")
    public String enregistrerprojet( @PathVariable String nom, @PathVariable String datedebut, @PathVariable String forma)  {
        Etudiant e=etudiant.findByUsername(forma);
        if (nom.equals(null) || datedebut.equals(null) || forma.equals(null)) {
            return "veillez renplir tout les champs";
        }
        else
        {
            projet d=new projet(0,nom,datedebut,e);
            service.enreistererprojets(d);
            return "bien enregistrer";
        }
    }
    @PostMapping("/creationprojet/{nomsout}/{datee}/{heuredeb}/{heurefin}/{nomj}")
    public String ajoutsoutenance( @RequestParam("file") MultipartFile file,@PathVariable String nomsout,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable String nomj) throws ParseException, IOException {
        jury h=jur.findByNom(nomj);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        String a = file.getOriginalFilename();
        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
        // Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
        LocalTime defenseTime = LocalTime.parse("09:00:00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        soutenance s= new soutenance();
        //  (0, new Date(sdf.parse(datee).getTime()), new Date(sdf.parse(datedebut).getTime()),LocalTime.parse(heuredeb),LocalTime.parse(heurefin),h);
        // s.setImg(a);
        s.setDatedebut(new Date(sdf.parse(datee).getTime()));
        s.setNom(nomsout);
        s.setHeuredeb(LocalTime.parse(heuredeb));
        s.setHeurefin(LocalTime.parse(heurefin));
        if (s.getJurys() == null) {
            //  o.setPromo(new ArrayList<Promotion>(p));
            s.setJurys(new ArrayList<jury>());
            // o.setPromo((Collection<promotion>) p);
            s.getJurys().add(h);
//         soutenance l=sout.findFirstByJurysAndDatedebutLessThanEqualAndDatefinGreaterThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),new Date(),LocalTime.now(),LocalTime.now());
            //  List<soutenance> g=sout.findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),LocalTime.now(),LocalTime.now());

            if (isJuryAvailable(h, new Date(sdf.parse(datee).getTime()), LocalTime.parse(heuredeb), LocalTime.parse(heurefin))) {
                service.enreisterersout(s);
                return "enregistrer";
            } else {
                return "cet jury a deja une soutenance dans cette plage de temps";
            }
        }


        else
            return " mauvaise enregistrement de la soutenance";
    }
    @PostMapping("/creationformation/{nom}/{desc}/{obj}")
    public void enregistrerformation( @PathVariable String nom, @PathVariable String desc, @PathVariable String obj) {
        if (nom.equals(null) || desc.equals(null) || obj.equals(null) ) {
        }
        else {
            formation f=new formation(0L,nom,desc,obj);
            format.save(f);
        }}
    // la suppression
    @DeleteMapping("/delete/{idPostulant}")
    public String supprimerformation( @PathVariable String idPostulant) {
        formation f=format.findByNom(idPostulant);
        format.deleteById(f.getId()); // Supprimer l'ID;
        return "formation  Supprimer !";
    }
    @DeleteMapping("/sup/{idPostulant}")
    public String supprimerjury( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        jur.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimeretudiant/{idPostulant}")
    public String supprimeretudiant( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        etudiant.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimerpromotion/{idPostulant}")
    public String supprimepromotion( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        promo.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimerprojet/{idPostulant}")
    public String supprimerprojet( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        proj.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/supprimeradmin/{idPostulant}")
    public String supprimeradmin( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        ads.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @DeleteMapping("/criteresup/{idPostulant}")
    public String criteresup( @PathVariable int idPostulant) {
        //  formation f=format.findByNom(idPostulant);
        critere.deleteById(idPostulant); // Supprimer l'ID;
        return "jury  Supprimer !";
    }
    @GetMapping("/lalistedesetudiants")
    public List<Etudiant> lire() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return etudiant.findAll();
    }

    @GetMapping("/lalistedesadmin")
    public List<admin> lireadmin() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return ads.findAll();
    }
    @GetMapping("/lalistedesjurys")
    public List<jury> lirejury() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return jurys.findAll();
    }
    @GetMapping("/lalistedesformation")
    public List<formation> lireformation() { // la méthode (findAll) pour la lecture ou liste des postulants;
        return format.findAll();
    }

    @GetMapping("/lalistedesformation/{nom}")
    public List<promotion> lirepromotionparformation(@PathVariable String nom) { // la méthode (findAll) pour la lecture ou liste des postulants;
        formation f=format.findByNom(nom);
        return promo.findByFormations(f);
    }

    @GetMapping("/lalisteeee/{nom}")
    public List<Object> lireetudiantprojetpromotionparformation(@PathVariable int nom) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(nom);
        return user.etudiantlisteparpromotiondeformation(nom);
    }
    // a faire la liste des projets et leur etudiants dans une promotion d'une formation
//@GetMapping("/lalistedesformation")
//public List<criteresevaluation> lirecriteresparformation() { // la méthode (findAll) pour la lecture ou liste des postulants;
//    return critere.findAllByFormations();
//}
    @GetMapping("/ladate/{n}")
    public Object lireetudia(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        formation f=format.findByNom(n);
        return user.ladatedeformationrecent(f.getId(),new Date());
    }

    @GetMapping("/infoetud/{n}")
    public Object infoetud(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.infoetudiant(n);
    }
    @GetMapping("/infocritere/{n}")
    public List<Object> infocritere(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.criteresparuser(n);}
    @PostMapping("/creationvote/{note}/{commentaires}/{etudian}/{jury}/{critereid}")
    public String enregistrervote( @PathVariable int note, @PathVariable("commentaires") String commentaire, @PathVariable int etudian,@PathVariable int jury,@PathVariable int critereid) {
        Etudiant l=etudiant.findByIde(etudian);
        Object k= user.soutenanceetudiant(l.getIde());
        Boolean s = Boolean.parseBoolean(k.toString());
        com.secuite.secur.modeles.jury j=jur.findByIde(jury);
        criteresevaluation c=critere.findByIdo(critereid);
        lesnotes m=  notte.findByEtudiantAndJurysAndCriteres(l,j,c);

        if (m == null) {
            lesnotes e=new lesnotes((Long) null, (Integer) note,false,commentaire,new Date(),l,j,c);
            notte.save(e);
            l.setLu(true);
            return "note enregistrer avec succes";
        } else {
            notte.findById(m.getId())
                    .map(p->{
                        p.setNote((Integer) note);
                        p.setCommentaires(commentaire);
                        return  notte.save(p);
                    });
            return "bien";
        }


    }
    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/creationa/{notee}/{commentaires}/{etudian}/{jury}/{critereid}")
    public void enregistrervotepar( @PathVariable boolean notee, @PathVariable("commentaires") String commentaire, @PathVariable int etudian,@PathVariable int jury,@PathVariable int critereid) {
        Etudiant l=etudiant.findByIde(etudian);
        Object k= user.soutenanceetudiant(l.getIde());
        boolean s = Boolean.parseBoolean(k.toString());
        com.secuite.secur.modeles.jury j=jur.findByIde(jury);
        criteresevaluation c=critere.findByIdo(critereid);
        lesnotes m=  notte.findByEtudiantAndJurysAndCriteres(l,j,c);
        if (m == null) {
            lesnotes e=new lesnotes((Long) null,0,  notee,commentaire,new Date(),l,j,c);
            notte.save(e);
            l.setLu(true);

        } else {
            notte.findById(m.getId())
                    .map(p->{
                        p.setValider((Boolean) notee);
                        p.setCommentaires(commentaire);
                        return  notte.save(p);
                    });

        }
    }
    @GetMapping("/etatetudiant/{n}")
    public int etatetudiant(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        // user.infoetudiant(n);
        etudiant.findById(n)
                .map(p->{
                    p.setLu(true);

                    return  etudiant.save(p);
                });
        return 0;
    }

    @GetMapping("/infomoyennecretere/{n}")
    public List<Object> infocriterenoteparetudiantlamoyenne(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.criteresparusernote(n);}
    @GetMapping("/infomoyenneordonneedesetudiants/{n}")
    public List<Object[]> infocalculenoteordonnee(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lesnotesetudiantparordre(n);}
    // calcule note 1 11111111111111111111111111111111111111111
    @GetMapping("/infomoyenneordonneedese/{n}")
    public List<Object[]> lesnotesetudiantparrapportsoutenan(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lesnotesetudiantparrapportsoutenance(n);}

    @GetMapping("/infomoyenneetudiant/{n}")
    public Object infocalculenoteetudiantparid(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.laanotesetudiant(n);}

    @GetMapping("/lasoutenancequiacommencere/{n}")
    public List<Object>  lasoutenancequiacommenceretpasfini(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lasoutenancequiacommenceretpasencorefini(n);}


    @GetMapping("/recherchelasoutenancequiacommencere/{n}")
    public boolean  recherchelasoutenancequiacommenceretpasfini(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
    List  <Object> j= user.lasoutenancequiacommenceretpasencorefini(n);
        if (j.isEmpty()){
            System.out.println(j);
            return false;
        }
        else{
        System.out.println("le jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        System.out.println(j);
    return true;}
    }

    @GetMapping("/lasoutenancequiacommencereouetudiantlu/{n}")
    public List<Object>  lasoutenancequiacommenceretpasfiniouetudiantlu(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.lasoutenancequiacommenceretpasencorefiniouetudiantnote(n);}
    @GetMapping("/lasout")
    public Object  lasoutenancequiacommenceretpasfini() { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.laanotesetu(LocalTime.now());}

    @GetMapping("/historiquesoutenance/{n}")
    public List<Object>historiquesoutenance(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.historiquesoutenance(n);}

    @GetMapping("/prochaine/{idJury}")
    public ResponseEntity<soutenance> getProchaineSoutenance(@PathVariable int idJury) {
        soutenance soutenance = service.getProchaineSoutenance(idJury);
        if (soutenance != null) {
            return ResponseEntity.ok(soutenance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/prochainedatesout/{idJury}")
    public String getProchai(@PathVariable int idJury) {
        long timeUntilNextSoutenance = user.soutenancerestantpourjury(idJury);
        long days = timeUntilNextSoutenance / 86400;
        long hours = (timeUntilNextSoutenance % 86400) / 3600;
        long minutes = (timeUntilNextSoutenance % 3600) / 60;
        long seconds = timeUntilNextSoutenance % 60;
        return String.format("%d jour(s) %d heure(s) %d minute(s) %d seconde(s)", days, hours, minutes, seconds);
    }
    @GetMapping("/juride/{n}")
    public  List<soutenance>  juride(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        jury j=jur.findByIde(n);
        return sout.findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(j,new Date(),LocalTime.now(),LocalTime.now());
    }
    @GetMapping("/time-to-next-defense/{juryId}")
    public String getTimeToNextDefense(@PathVariable int juryId) {
        // Récupérez la soutenance correspondant à l'ID du jury
        soutenance soutenance = sout.findById(juryId).orElseThrow(() -> new RuntimeException("Soutenance introuvable"));
        // soutenance soutenance =user.soutenancereslaplusprochepourjury(juryId);
        LocalTime currentTime = LocalTime.now();
        Object l= user.soutenancereslaplusprochepourjury(juryId);
        LocalTime defenseStartTime = soutenance.getHeuredeb();
        LocalTime defenseEndTime = soutenance.getHeurefin();

        // Calculez la durée restante jusqu'au début de la soutenance
        Duration durationToStart = Duration.between(currentTime, defenseStartTime);

        // Calculez la durée restante jusqu'à la fin de la soutenance
        Duration durationToEnd = Duration.between(currentTime, defenseEndTime);

        // Retournez le temps restant sous forme de chaîne de caractères
        return "Temps restant avant le début de la soutenance : " + durationToStart.toString() + "\n" +
                "Temps restant avant la fin de la soutenance : " + durationToEnd.toString();
    }

    @GetMapping("/allerrr/{n}")
    public  String  jurd(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
//        LocalDateTime now = LocalDateTime.now();
//
//
//            LocalDateTime start = LocalDateTime.of(jury.getDatedebut(), jury.getHeuredeb());
//            Duration duration = Duration.between(now, start);
//            long days = duration.toDays();
//            duration = duration.minusDays(days);
//            long hours = duration.toHours();
//            duration = duration.minusHours(hours);
//            long minutes = duration.toMinutes();
//
//            return ("Il vous reste " + days + "j " + hours + "h " + minutes + "mn avant votre soutenance");
//         }

        return "coucou";}

    @GetMapping("/jurideeee/{n}")
    public  Object  soutenanceprochepourjury(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        jury j=jur.findByIde(n);
        return user.soutenancereslaplusprochepourjury(n);
    }
    @GetMapping("/jural/{juryId}")
    public Object fait( @PathVariable int juryId){
        String m="pas de soutenance";
        Object[] defense = (Object[]) user.soutenancereslaplusprochepourjury(juryId);
        if (defense==null){
            return "{\"message\":\"pas de soutenance prevu pour maintenant\"}";
        }
        else {
            Date defenseDate = (Date) defense[3];
            Time defenseStartTime = (Time) defense[4];

            LocalDateTime defenseStartDateTime = LocalDateTime.of(
                    defenseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    defenseStartTime.toLocalTime()
            );
            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(now, defenseStartDateTime);
            long minutes = duration.toMinutes();
            long hours = minutes / 60;
            long days = hours / 24;
            minutes = minutes % 60;
            hours = hours % 24;
            return "{\"days\":" + days + ", \"hours\":" + hours + ", \"minutes\":" + minutes + "}";


        }
    }


    public String formatDuration(Duration duration) {
        long minutes = duration.toMinutes();
        long hours = minutes / 60;
        long days = hours / 24;
        minutes = minutes % 60;
        hours = hours % 24;

        return String.format("%dj %dh %dmn", days, hours, minutes);
    }
    public boolean isJuryAvailable(jury  jur, Date dateDebut, LocalTime heureDeb, LocalTime heureFin) {
        for (soutenance s : sout.findAll()) {
            for (jury j : s.getJurys()) {
                if (j.getIde() == jur.getIde()) {
                    LocalDateTime soutenanceStart = LocalDateTime.of(s.getDatedebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), s.getHeuredeb());
                    LocalDateTime soutenanceEnd = LocalDateTime.of(s.getDatedebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), s.getHeurefin());
                    LocalDateTime newSoutenanceStart = LocalDateTime.of(dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), heureDeb);
                    LocalDateTime newSoutenanceEnd = LocalDateTime.of(dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), heureFin);
                    if ((newSoutenanceStart.isBefore(soutenanceEnd) && newSoutenanceStart.isAfter(soutenanceStart)) || (newSoutenanceEnd.isBefore(soutenanceEnd) && newSoutenanceEnd.isAfter(soutenanceStart))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @GetMapping("/resultatautrpkiju/{juryId}/{cri}")
    public int resultatautrer( @PathVariable int juryId,@PathVariable int cri){
        Etudiant n=etudiant.findByIde(juryId);
        criteresevaluation b=critere.findByIdo(cri);
        int t=0;
        //  int m=user.lesjurysparrapportaletudiant(juryId);
        int a=0;
        List<lesnotes> l= notte.findByEtudiantAndCriteres(n,b);
        for (lesnotes note : l){
            if (note.getValider()){
                a++;
            }
            else {
                t++;
            }
        }
        if (t>a){
            return 0;
        }
        else {
            return 1;
        }
    }
    @GetMapping("/jurp/{n}")
    public  Object  toutnotes(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        Etudiant o = etudiant.findByIde(n);
        int q =user.countetudiantnombrecritere(n);
        int p=0;
        List<lesnotes> a = notte.findByEtudiant(o);
        for (lesnotes note : a) {
            System.out.println("le ppppppppppppp1");
            System.out.println(p);

            criteresevaluation w=note.getCriteres();

            System.out.println("le ppppppppppppp2");
            System.out.println(p);
            if (resultatautrer(o.getIde(),w.getIdo()) == 1) {
                p++;
            }

        }
        System.out.println("le ppppppppppppp3");
        System.out.println(p);
        if(p>=q){
            return"valider";
        }
        else {
            return"pas valider";
        }}


    @GetMapping("/student/{id}")
    public Map<String, String> hasStudentPassedAllCriteria(@PathVariable("id") int studentId) {
        Etudiant student = etudiant.findByIde(studentId);
        List<lesnotes> studentNotes = notte.findByEtudiant(student);
        Object[] h=user.idcritereparetudiant(studentId);
        int totalCriteriaCount = user.countetudiantnombrecritere(studentId);
        int passedCriteriaCount = 0;
        for (Object note : h) {
            if (resultatautre(studentId, (Integer) note) == 1) {
                passedCriteriaCount= passedCriteriaCount+resultatautre(studentId, (Integer) note);
                System.out.println("le resultat");
                System.out.println(passedCriteriaCount);
            }
        }
        if (passedCriteriaCount >= totalCriteriaCount) {
            Map<String, String> result = new HashMap<>();
            result.put("status", "Validé");
            return result;
        } else {
            Map<String, String> result = new HashMap<>();
            result.put("status", "Non validé");
            return result;
        }
    }

    public List<Map<String, String>> lesnotesetudiantparrapportsoutenance(int ide) {
        List<Object[]> result = user.lesnotesetudiantparrapportsoutenance(ide);
        List<Map<String, String>> results = new ArrayList<>();

        for (Object[] row : result) {
            String nom = (String) row[1];
            String prenom = (String) row[2];
            String moyenne = row[0].toString();
            String nomSoutenance = (String) row[3];
            String mail = (String) row[4];

            Map<String, String> map = new HashMap<>();
            map.put("nom", nom);
            map.put("prenom", prenom);
            map.put("moyenne", moyenne);
            map.put("nom_soutenance", nomSoutenance);
            map.put("email", mail);


            results.add(map);
        }

        return results;
    }
    // ici le calcule de resultat 2   2222222222222222222222222222222222222222222222222222222222222
    @GetMapping("/soutenance/{soutenanceId}/results")
    public List<Map<String, String>> getSoutenanceResults(@PathVariable("soutenanceId") String soutenanceId) {
      //  Optional<soutenance> a=soutenan.findById(soutenanceId);
        soutenance a=soutenan.findByNom(soutenanceId);
        if (a.isType()){
        List<Etudiant> etudiants = etudiant.findBySoutenancesId(a.getId());
        List<Map<String, String>> results = new ArrayList<>();
        for (Etudiant etudiant : etudiants) {
            String nom = etudiant.getNom();
            String prenom = etudiant.getPrenom();
            int id=etudiant.getIde();
            String mail=etudiant.getEmail();
            String resultat = String.valueOf(hasStudentPassedAllCriteria(etudiant.getIde()));
            Map<String, String> result = new HashMap<>();
            result.put("id", String.valueOf(id));
            result.put("nom", nom);
            result.put("prenom", prenom);
            result.put("resultat", resultat);
            result.put("email",mail);
            results.add(result);
        }
        return results;}
        else {
            return lesnotesetudiantparrapportsoutenance(a.getId());}
        };










@GetMapping("/resultatau/{juryId}/{cri}")
    public int resultatautre(@PathVariable("juryId") int juryId, @PathVariable("cri") int criteriaId) {
        Etudiant student = etudiant.findByIde(juryId);
        criteresevaluation criteria = critere.findByIdo(criteriaId);
        List<lesnotes> notes = notte.findByEtudiantAndCriteres(student, criteria);
        int passedCount = 0;
        int failedCount = 0;
        for (lesnotes note : notes) {
            if (note.getValider()) {
                passedCount++;
            } else {
                failedCount++;
            }
        }
        if (failedCount > passedCount) {
            return 0;
        } else {
            return 1;
        }
    }
    @GetMapping("/trouverparnom/{n}")
    public  Object  utilisateurparnom(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        utilisateur u=   user.findByNom(n);
        return u.getIde();
    }
    @GetMapping("/trouverparnomlerole/{n}")
    public  Object  utilisateurparrole(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        utilisateur u=   user.findByNom(n);
        return u.getRoles();
    }
    @GetMapping("/etudiantlisteparpromotion/{n}")
    public  List<Object[]>  etudiantlisteparpromotion(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        int o= (int) user.promoparetudiant(n);
        return  user.etudiantlisteparpromotion(o);

    }

    @GetMapping("/soutenanceactuelstatut/{n}")
    public  boolean  soutenanceactuelstatut(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        return user.soutenanceencoursetat(n);

    }
    @GetMapping("/soutenanceactuelstatutts/{n}")
    public  boolean  soutenanceactuelstatutt(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        if(user.soutenanceencours(n)==null)    {
            return false;
        }else {
            return true;
        }
    }

    @GetMapping("/resultatspp/{juryId}")
    public Map<Integer, Integer> resultat(@PathVariable("juryId") int juryId) {
        Etudiant student = etudiant.findByIde(juryId);
        List<criteresevaluation> criteres = critere.findAll();
        // récupérer tous les critères

        Map<Integer, Integer> resultats = new HashMap<>();

        for (criteresevaluation critere : criteres) {
            List<lesnotes> notes = notte.findByEtudiantAndCriteres(student, critere);
            int passedCount = 0;
            int failedCount = 0;
            for (lesnotes note : notes) {
                if (note.getValider()) {
                    passedCount++;
                } else {
                    failedCount++;
                }
            }
            if (failedCount > passedCount) {
                resultats.put(critere.getIdo(), 0);
            } else {
                resultats.put(critere.getIdo(), 1);
            }
        }

        return resultats;
    }

    @GetMapping("/resultatssst/{juryId}")
    public Map<String, String> resultattt(@PathVariable("juryId") int juryId) {
        Etudiant student = etudiant.findByIde(juryId);
        List<lesnotes> studentNotes = notte.findByEtudiant(student);
        Object[] h=user.idcritereparetudiant(juryId);
        Map<String, String> resultats = new HashMap<>();
        // int totalCriteriaCount = user.countetudiantnombrecritere(juryId);
        int passedCriteriaCount = 0;
        for (Object notef : h) {
            criteresevaluation l=critere.findByIdo((Integer) notef);
            List<lesnotes> notes = notte.findByEtudiantAndCriteres(student,l);
            int passedCount = 0;
            int failedCount = 0;
            for (lesnotes note : notes) {
                if (note.getValider()) {
                    passedCount++;
                } else {
                    failedCount++;
                }
            }
            if (failedCount > passedCount) {
                resultats.put(l.getNom(),"pas valider");
            } else {
                resultats.put(l.getNom()," valider");
            }

        }
        return resultats;

    }

    @GetMapping("/soutenancestatutpourunetudiant/{n}")
    public  boolean  soutenancestatutpourunetudiant(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        return user.soutenancestatutpourunetudiant(n);
    }

    @GetMapping("/soutenancesidpourunetudiant/{n}")
    public  Object  soutenancesidpourunetudiant(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        return user.soutenancesidpourunetudiant(n);
    }

    @GetMapping("/listeetudiantparsoutenance/{n}")
    public Object[] listeetudiantparsoutenancee(@PathVariable int n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.listeetudiantparsoutenance(n);}
    @GetMapping("/listeetudiantparsout/{n}")
    public Object[] listeetudiantparsout(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
        return user.listeetudiantparsout(n);}

    @GetMapping("/envoiemail/{envoie}/{destinateur}/{body}/{subject}")
    public Object envoiemail(@PathVariable String envoie,@PathVariable String destinateur,@PathVariable String body,@PathVariable String subject) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //formation f=format.findByNom(n);
          senderService.sendSimpleEmail(envoie,destinateur,
				body,
				subject);
          notification n=new notification(null,subject,body,new Date(),envoie,destinateur,false);
        notif.save(n);
        return "bien envoyer";
        }
    @GetMapping("/{expediteur}/{destinateur}")
    public List<notification> getNotificationsByExpediteurAndDestinateur(@PathVariable String expediteur, @PathVariable String destinateur) {
        return service.getNotificationsByExpediteurAndDestinateur(expediteur, destinateur);
    }
    @PostMapping("/soutenance/{id}/jurys/{juryIds}")
    public soutenance addJurysToSoutenance(@PathVariable int id, @PathVariable List<Integer> juryIds) {
        soutenance sout = soutenan.findById(id).orElseThrow(() -> new EntityNotFoundException("Soutenance not found"));

        List<jury> jurys = new ArrayList<>();
        for (int juryId : juryIds) {
            jury j = jur.findById(juryId).orElseThrow(() -> new EntityNotFoundException("Jury not found"));
            jurys.add(j);
        }

        sout.getJurys().addAll(jurys);
        return soutenan.save(sout);
    }
    @GetMapping("/listedesnomdesoutenanceavenir")
    public List<Object> listedesnomdesoutenanceavenir() { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listedesnomdesoutenanceavenir();
    }
    @GetMapping("/listesoutenance")
    public List<soutenance> listesoutenance() { // la méthode (findAll) pour la lecture ou liste des postulants;

        return soutenan.findAll();
    }
    @GetMapping("/juryparsoutenance/{id}")
    public List<Object> juryparsoutenance(  @PathVariable int id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.juryparsoutenance(id);
    }
    @GetMapping("/listeetudiantparrapportasoutenance/{id}")
    public List<Object> listeetudiantparrapportasoutenance(  @PathVariable int id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listeetudiantparrapportasoutenance(id);
    }
    // liste des soutenances a venir rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    @GetMapping("/listeetudiantparsoutenancenom/{id}")
    public List<Object> listeetudiantparsoutenancenom(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listeetudiantparsoutenancenom(id);
    }
    @GetMapping("/listecriteresparsoutenancenom/{id}")
    public List<Object> listecriteresparsoutenancenom(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listecriteresparsoutenancenom(id);
    }
    // pourcentage autre
    @GetMapping("/pourcentagesoutenance/{id}")
    public List<Object> pourcentagesoutenance(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.pourcentagesoutenance(id);
    }
    // pourcentage calcule 11111111
    @GetMapping("/pourcentagesoutenancefacon1/{id}")
    public List<Object> pourcentagesoutenancefacon1(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.pourcentagesoutenancefacon1(id);
    }
    @GetMapping("/juryparsoutenancenom/{id}")
    public List<Object> juryparsoutenancenom(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.juryparsoutenancenom(id);
    }
    @GetMapping("/juryssommeparrapportasoutenance/{id}")
    public int juryssommeparrapportasoutenance(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.juryssommeparrapportasoutenance(id);
    }
    @GetMapping("/criteresommeparrapportasoutenance/{id}")
    public int criteresommeparrapportasoutenance(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.criteresommeparrapportasoutenance(id);
    }
    @GetMapping("/etudiantcount")
    public int etudiantcount( ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.etudiantcount();
    }
    @GetMapping("/soutenancecount")
    public int soutenancecount( ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.soutenancecount();
    }
    @GetMapping("/promotiontioncount")
    public int promotiontioncount( ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.promotiontioncount();
    }
    @GetMapping("/formationcount")
    public int formationcount( ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.formationcount();
    }
    @GetMapping("/listpromo")
    public List<promotion> listpromo( ) { // la méthode (findAll) pour la lecture ou liste des postulants;

      return promo.findAll();
    }
    @GetMapping("/listcritereeva/{id}")
    public List<Object> listcri( @PathVariable String id) {
    // la méthode (findAll) pour la lecture ou liste des postulants;
        System.out.println(id);
        return user.listedescritereparformation(id);
    }
    @GetMapping("/soutparid/{id}")
    public Optional<soutenance> soutparid(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return soutenan.findById(id);
    }
    @GetMapping("/lastatistiquev/{id}")
    public List<Object> lastatistiquev(  @PathVariable String id) { // la méthode (findAll) pour la lecture ou liste des postulants;
            soutenance s=soutenan.findByNom(id);
            if(s.isType()){
                return user.pourcentagesoutenance(id);

            }
            else {
                return user.pourcentagesoutenancefacon1(id);

            }
    }
    @PostMapping("/soutenance/{nom}/{datee}/{heuredeb}/{heurefin}/{juryNoms}/{ad}/{boo}")
    public Object createSoutenanceWithJurys(@PathVariable String nom,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable List<String> juryNoms, @PathVariable String ad,@PathVariable boolean boo) throws ParseException {
        // Vérifier si une soutenance avec ce nom existe déjà
        soutenance existingSoutenance = soutenan.findByNom(nom);
        LocalTime defenseTime = LocalTime.parse("09:00:00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (existingSoutenance != null) {
            throw new IllegalArgumentException("Une soutenance avec le nom " + nom + " existe déjà.");
        }
        // Créer une nouvelle soutenance avec le nom donné et les jurys fournis
        soutenance sout = new soutenance();
        sout.setNom(nom);
        sout.setDatedebut(new Date(sdf.parse(datee).getTime()));
        sout.setHeuredeb(LocalTime.parse(heuredeb));
        sout.setHeurefin(LocalTime.parse(heurefin));
        sout.setType(boo);
        List<jury> jurys = new ArrayList<>();
        for (String juryNom : juryNoms) {
            jury j = jur.findByUsername(juryNom);
            if (j == null) {
//                j = new jury();
//                j.setNom(juryNom);
//                j = jur.save(j);
                System.out.println(j);
                return ("jury introuvable");
            }
//            senderService.sendSimpleEmail("fantisca747@gmail.com","kmahamadou858@gmail.com",
//                    "This is email body",
//                    "This is email subject");
            jurys.add(j);
        }
        sout.getJurys().addAll(jurys);

        // Enregistrer la nouvelle soutenance dans la base de données
        return soutenan.save(sout);
    }

    @GetMapping("/resultatsoutenanceautrefacon/{soutenanceId}")
    public Map<String, Map<String, String>> resultatsSoutenance(@PathVariable("soutenanceId") String soutenanceId) {
        soutenance soutenanc = soutenan.findByNom(soutenanceId);
        Collection<Etudiant> etudiants = etudiant.findBySoutenances(soutenanc);
        Map<String, Map<String, String>> resultatsSoutenance = new HashMap<>();
        for (Etudiant etudiant : etudiants) {
            Map<String, String> resultatsEtudiant = resultattt(etudiant.getIde());
            resultatsSoutenance.put(etudiant.getNom() + " " + etudiant.getPrenom(), resultatsEtudiant);
        }
        return resultatsSoutenance;
    }
    @PutMapping("/modificationcritere/{idProblemes}/{nom}/{datedebut}/{forma}")
    public Object updatecritere(@PathVariable int idProblemes,@PathVariable String nom,@PathVariable int datedebut,@PathVariable String forma){
        return critere.findById(idProblemes) // Cherchons le commentaire id si on trouve;
                .map(c->{ // Si on trouve on fait le mappage;
                    c.setNom(nom);
                    c.setCoefficient(datedebut);
                    c.setFormations(format.findByNom(forma));

                    // on modifie le commentaire;
                    return critere.save(c); // On l'Enregistre dans la base de donnée;
                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
    }
    @PutMapping("/modificationformation/{idProblemes}/{nom}/{desc}/{obj}")
    public Object update(@PathVariable Long idProblemes, @PathVariable String nom, @PathVariable String desc, @PathVariable String obj ){
          return format.findById(idProblemes) // Cherchons le commentaire id si on trouve;
                .map(c->{ // Si on trouve on fait le mappage;
                    c.setNom(nom);
                    c.setDescription(desc);
                    c.setObjectif(obj);

                    // on modifie le commentaire;
                    return format.save(c); // On l'Enregistre dans la base de donnée;
                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
    }
    @PutMapping("/modificationprojet/{idProblemes}/{nom}/{datedebut}/{forma}")
    public Object updateprojet(@PathVariable int idProblemes, @PathVariable String nom, @PathVariable String datedebut, @PathVariable String forma ){
        return proj.findById(idProblemes) // Cherchons le commentaire id si on trouve;
                .map(c->{ // Si on trouve on fait le mappage;
                    c.setNomn(nom);
                    c.setDescription(datedebut);
                    c.setEtudiant(etudiant.findByNom(forma));

                    // on modifie le commentaire;
                    return proj.save(c); // On l'Enregistre dans la base de donnée;
                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
    }
    // modif etudiant eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    @PutMapping("/modificationetudiant/{idProblemes}/{nom}/{prenom}/{username}/{email}")
    public Object updateetudiant(@PathVariable int idProblemes,  @PathVariable String nom, @PathVariable String prenom, @PathVariable String username,@PathVariable String email ){
        return etudiant.findById(idProblemes) // Cherchons le commentaire id si on trouve;
                .map(c->{ // Si on trouve on fait le mappage;
                    c.setNom(nom);
                    c.setPrenom(prenom);
                    c.setUsername(username);
                    c.setEmail(email);
//                    Collection<promotion> promoList = new ArrayList<promotion>(); // On crée une nouvelle collection de promotion
//                    promoList.add(promo.findByNom(prom)); // On ajoute l'objet promotion retourné par la méthode findByNom à la collection
//                    c.setPromo(promoList); // On assigne la collection d'objets promotion à l'étudiant                    c.setSoutenances(soutenan.findByNom(nomsout));
                    // on modifie le commentaire;
                    return etudiant.save(c); // On l'Enregistre dans la base de donnée;
                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
    }
    @PutMapping("/modificationpromo/{Problemes}/{nom}/{datedebut}/{fin}/{N}")
    public Object updatepromo(@PathVariable Integer Problemes,@PathVariable String nom,@PathVariable String datedebut,@PathVariable String fin,@PathVariable String N ){
       formation f=format.findByNom(N);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return promo.findById(Problemes) // Cherchons le commentaire id si on trouve;
                .map(c->{ // Si on trouve on fait le mappage;
                    c.setNom(nom);
                    try {
                        c.setDatedebut( new Date(sdf.parse(datedebut).getTime()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        c.setDatefin( new Date(sdf.parse(fin).getTime()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    c.setFormations(f);
                    return promo.save(c); // On l'Enregistre dans la base de donnée;
                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
    }

//ajout soutenance avec un admin precis
    @PostMapping("/soutenancemotif/{id}/{nom}/{datee}/{heuredeb}/{heurefin}/{juryNoms}/{ad}/{boo}")
    public soutenance updateSoutenance(@PathVariable int id,@PathVariable String nom,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable List<String> juryNoms, @PathVariable String ad,@PathVariable boolean boo) throws ParseException {
        // Trouver la soutenance à mettre à jour
        List<jury> jurys = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        soutenance sout = soutenan.findById(id).orElseThrow(() -> new IllegalArgumentException("Soutenance introuvable avec l'ID : " + id));
        // Mettre à jour les champs de la soutenance avec les valeurs fournies dans updatedSoutenance
        sout.setNom(nom);
       // sout.setDatedebut(updatedSoutenance.getDatedebut());
        sout.setHeuredeb(LocalTime.parse(heuredeb));
        sout.setHeurefin(LocalTime.parse(heurefin));
        sout.setDatedebut(new Date(sdf.parse(datee).getTime()));
        sout.setType(boo);
        for (String juryNom : juryNoms) {
            jury j = jur.findByUsername(juryNom);
            if (j == null) {
                j = new jury();
                j.setNom(juryNom);
                j = jur.save(j);
            }
            jurys.add(j);
        }
        sout.setJurys(jurys);
        // Enregistrer les modifications dans la base de données
        return soutenan.save(sout);
    }
    @GetMapping("/lesjurysparrapportaetudiantlu/{id}")
    public int lesjurysparrapportaetudiantlu(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.lesjurysparrapportaetudiantlu(id);
    }
    @GetMapping("/lesjuryspar/{id}")
    public int lesjuryspar(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.lesjuryspar(id);
    }
    @GetMapping("/lesjurysparrappor/{id}")
    public int lesjurysparrappor(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.lesjurysparrappor(id);
    }
    @GetMapping("/listehisto/{id}")
    public List<Object> listehisto(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listehisto(id);
    }
    @GetMapping("/listeetudiantparsoutenanceautre/{id}")
    public List<Object> listeetudiantparsoutenanceautre(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listeetudiantparsoutenanceautre(id);
    }
    @GetMapping("/listemeilleuretudiant")
    public List<Object> listemeilleuretudiant() { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listemeilleuretudiant();
    }
    @GetMapping("/listeetudiantparrapportasoutenanceee/{id}")
    public List<Object> listeetudiantparrapportasoutenanceee(@PathVariable String id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.listeetudiantparrapportasoutenanceee(id);
    }


    @GetMapping("/etudiantresultatsoutenance/{id}")
    public boolean etudiantresultatsoutenance(@PathVariable int id ) { // la méthode (findAll) pour la lecture ou liste des postulants;

        return user.etudiantresultatsoutenance(id);
    }
    @GetMapping("/soutenanceactuelstatutchanger/{n}")
    public  void   soutenanceactuelstatutchanger(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        soutenance soutenanc = soutenan.findByNom(n);

        soutenanc.setResultat(true);
        soutenance updatedSoutenance = soutenan.save(soutenanc);
     soutenance s=   soutenan.findByNom(n);

    }
    @GetMapping("/juryparnom/{n}")
    public  jury   juryparnom(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
       return  jur.findByNom(n);
    }

    @GetMapping("/etudianttatutchanger/{n}")
    public  void   etudianttatutchanger(@PathVariable String n) { // la méthode (findAll) pour la lecture ou liste des postulants;
        //  jury j=jur.findByIde(n);
        // utilisateur u=   user.findByNom(n);
        Etudiant soutenanc = etudiant.findByNom(n);

        soutenanc.setLu(true);
        Etudiant updatedSoutenance = etudiant.save(soutenanc);
        Etudiant s=   etudiant.findByNom(n);

    }

//    @PutMapping("/creationprojet/{nomsout}/{datee}/{heuredeb}/{heurefin}/{nomj}")
//    public Object updatesoutenance(@PathVariable String nomsout,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable String nomj){
//       // formation f=format.findByNom(N);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        return promo.findById(Problemes) // Cherchons le commentaire id si on trouve;
//                .map(c->{ // Si on trouve on fait le mappage;
//                    c.setNom(nom);
//                    try {
//                        c.setDatedebut( new Date(sdf.parse(datedebut).getTime()));
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                    try {
//                        c.setDatefin( new Date(sdf.parse(fin).getTime()));
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                    c.setFormations(f);
//                    return promo.save(c); // On l'Enregistre dans la base de donnée;
//                }).orElseThrow(()-> new RuntimeException("Commentaire non trouvé !"));
//    }







    }



//    @PostMapping("/creationprojet/{nomsout}/{datee}/{heuredeb}/{heurefin}/{nomj}")
//    public String ajoutsoutenance( @RequestParam("file") MultipartFile file,@PathVariable String nomsout,@PathVariable String datee, @PathVariable String heuredeb, @PathVariable String heurefin, @PathVariable String nomj) throws ParseException, IOException {
//        jury h=jur.findByNom(nomj);
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getName());
//        System.out.println(file.getContentType());
//        System.out.println(file.getSize());
//        String a = file.getOriginalFilename();
//        String Path = "C:\\Users\\Fatoumata DEMBELE\\Desktop\\securityvraisoutenance\\securite\\secur\\src\\main\\resources\\img";
//        // Files.copy(file.getInputStream(), Paths.get(Path + File.separator + file.getOriginalFilename()));
//        LocalTime defenseTime = LocalTime.parse("09:00:00");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        soutenance s= new soutenance();
//        //  (0, new Date(sdf.parse(datee).getTime()), new Date(sdf.parse(datedebut).getTime()),LocalTime.parse(heuredeb),LocalTime.parse(heurefin),h);
//        // s.setImg(a);
//        s.setDatedebut(new Date(sdf.parse(datee).getTime()));
//        s.setNom(nomsout);
//        s.setHeuredeb(LocalTime.parse(heuredeb));
//        s.setHeurefin(LocalTime.parse(heurefin));
//        if (s.getJurys() == null) {
//            //  o.setPromo(new ArrayList<Promotion>(p));
//            s.setJurys(new ArrayList<jury>());
//            // o.setPromo((Collection<promotion>) p);
//            s.getJurys().add(h);
////         soutenance l=sout.findFirstByJurysAndDatedebutLessThanEqualAndDatefinGreaterThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),new Date(),LocalTime.now(),LocalTime.now());
//            //  List<soutenance> g=sout.findByJurysAndDatedebutLessThanEqualAndHeuredebLessThanEqualAndHeurefinGreaterThanEqual(h,new Date(),LocalTime.now(),LocalTime.now());
//
//            if (isJuryAvailable(h, new Date(sdf.parse(datee).getTime()), LocalTime.parse(heuredeb), LocalTime.parse(heurefin))) {
//                service.enreisterersout(s);
//                return "enregistrer";
//            } else {
//                return "cet jury a deja une soutenance dans cette plage de temps";
//            }
//        }
//
//
//        else
//            return " mauvaise enregistrement de la soutenance";
//    }
