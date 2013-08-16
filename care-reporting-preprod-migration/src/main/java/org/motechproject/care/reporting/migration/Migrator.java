package org.motechproject.care.reporting.migration;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.motechproject.care.reporting.migration.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Migrator {

    private MigrationService migrationService;

    @Autowired
    public Migrator(MigrationService migrationService) {
        this.migrationService = migrationService;
    }

    public static void main(String[] args) {
        boolean success = true;
        DateTime startTime = DateTime.now();
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationCareMigration.xml");

        try {
            Migrator migrator = applicationContext.getBean(Migrator.class);
            MigratorArguments migratorArguments = new MigratorArguments(args);

            migrator.migrate(migratorArguments);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Usage: " + MigratorArguments.usage());
        } catch (RuntimeException e) {
            System.out.println("Migration Failed");
            success = false;
        } finally {
            System.out.printf("Total time taken for migration: %d mins %n", new Duration(startTime, DateTime.now()).getStandardMinutes());
            applicationContext.destroy();
        }
        if (!success)
            System.exit(1);
    }

    public void migrate(MigratorArguments migratorArguments) {
        migrationService.migrate(migratorArguments);
    }
}