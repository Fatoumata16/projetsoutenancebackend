package com.secuite.secur.controlleur;

import com.secuite.secur.EmailSenderService;
import com.secuite.secur.modeles.Etudiant;
import com.secuite.secur.modeles.projet;
import com.secuite.secur.modeles.promotion;
import com.secuite.secur.modeles.soutenance;
import com.secuite.secur.repository.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;

@RestController
//@RequestMapping("/import")
public class excel {

    @Autowired
    private final repopromo promo;

    @Autowired
    private final reporole rol;
    @Autowired
    private final reposoutenance soutenan;
    @Autowired
    private final repoetudiant etud;
    @Autowired
    private final repoprojet proj;
    @Autowired
    private EmailSenderService senderService;




    public excel(repopromo promo, reporole rol, reposoutenance soutenan, repoetudiant etud, repoprojet proj,EmailSenderService senderService) {
        this.promo = promo;
        this.rol = rol;
        this.soutenan = soutenan;
        this.etud = etud;
        this.proj = proj;
        this.senderService=senderService;
    }

    @PostMapping("/excel/{nom}")
        public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file, @PathVariable String nom) {
            try {
                InputStream inputStream = file.getInputStream();

                Workbook workbook;
                if (file.getOriginalFilename().endsWith(".xls")) {
                    workbook = new HSSFWorkbook(inputStream); // For Excel 97-2003 file format (.xls)
                } else if (file.getOriginalFilename().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(inputStream); // For Excel 2007+ file format (.xlsx)
                } else {
                    throw new IllegalArgumentException("Unsupported file type");
                }

                Sheet sheet = workbook.getSheetAt(0); // Assume that the data is in the first sheet
                 soutenance a=soutenan.findByNom(nom);
                // Loop through the rows and create students and projects
                for (Row row : sheet) {
                    Etudiant etudiant = new Etudiant();
                    etudiant.setNom(row.getCell(0).getStringCellValue());
                    etudiant.setPrenom(row.getCell(1).getStringCellValue());
                    etudiant.setUsername(row.getCell(2).getStringCellValue());
                    etudiant.setMot2passe("fatouscha");
                    etudiant.setRoles(rol.findByRolename("etudiant"));
                    etudiant.setPromo(Collections.singleton(promo.findById(1)));                    etudiant.setEmail(row.getCell(3).getStringCellValue());
                    etudiant.setSoutenances(a);
                    etudiant.setLu(false);
                    // Set other fields of Etudiant

                    projet proje = new projet();

                    proje.setNomn(row.getCell(4).getStringCellValue());
                    proje.setDescription(row.getCell(5).getStringCellValue());
                    // Set other fields of Projet
                    proje.setEtudiant(etudiant);
//                    etudiant.setProjet(proje);

                    // Save the student and the project to the database
                    // ...
                    etud.save(etudiant);
                    proj.save(proje);
                    senderService.sendSimpleEmail("fantisca747@gmail.com","kmahamadou858@gmail.com",
                            "This is email body",
                            "This is email subject");
                }

                return ResponseEntity.ok("Import successful");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Import failed");
            }
        }




}
