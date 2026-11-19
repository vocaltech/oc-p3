package com.equipit;

import com.equipit.equipment.model.Equipment;
import com.equipit.equipment.repository.EquipmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EquipitApplication {

    static void main(String[] args) {
        SpringApplication.run(EquipitApplication.class, args);
    }

    @Bean
    CommandLineRunner loadEquipment(EquipmentRepository repository) {
        final String AUDIOVISUEL = "Audiovisuel";

        return args -> {
            if (repository.count() == 0) {
                repository.save(new Equipment("Ordinateur portable", "Informatique"));
                repository.save(new Equipment("Vidéo-projecteur", AUDIOVISUEL));
                repository.save(new Equipment("Casque audio", AUDIOVISUEL));
                repository.save(new Equipment("Appareil photo", AUDIOVISUEL));
                repository.save(new Equipment("Tablette graphique", "Informatique"));
            }
        };
    }
}