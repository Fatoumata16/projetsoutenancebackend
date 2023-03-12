package com.secuite.secur;


import com.secuite.secur.modeles.formation;
import com.secuite.secur.modeles.promotion;
import com.secuite.secur.modeles.role;
import com.secuite.secur.modeles.utilisateur;
import com.secuite.secur.services.servuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@SpringBootApplication
public class SecurApplication {
		@Autowired
	private EmailSenderService senderService;
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		senderService.sendSimpleEmail("fantisca747@gmail.com","kmahamadou858@gmail.com",
//				"This is email body",
//				"This is email subject");
//
//	}
	public static void main(String[] args) {
		SpringApplication.run(SecurApplication.class, args);
	}
    @Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start( servuser e  ) throws Exception {
		return  args -> {
            //	e.ajouter(("admin"));
            //e.ajouter("user");
            	//e.ajouter("etudiant");
            //	e.ajouter("jury");
            	//	e.ajout(new utilisateur(0, "fatoumata", "", "fatou", "", "1234", new role(1L, "admin"), null, null));
//		//	e.ajout(new utilisateur(0, "Hadi", "", "fatou", "", "5678", new role(2L, "user"), null, null));
		//	e.addroleToUser("fatoumata","admin");
			int i = 4;
			int i1 = 45;
			Date u=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = "2023-08-10";
          //  e.ajou(new formation(null,"developement mobile","formation1","faire que les etudiants deviennent des pro "));
			Date date = new Date(sdf.parse(strDate).getTime());
			//u=("2023-01-12 13:20:13");
        // e.enregistrer(new promotion(0,"odk2",new Date(),new Date(sdf.parse(strDate).getTime()), new formation(1L,"formationa","formdes","dfghjk")));
			//

//			e.ajouter(("admin"));
//			e.ajouter("user");
//
//			e.ajout(new utilisateur(null,"fatoumata","1234",new ArrayList<role>()));
//			e.ajout(new utilisateur(null,"Hadi","5678",new ArrayList<role>()));
//			e.addroleToUser("fatoumata","admin");
//			e.addroleToUser("fatoumata","user");
//			e.addroleToUser("Hadi","user");
		};
	}

}
