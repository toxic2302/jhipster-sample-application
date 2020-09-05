package de.toxic2302.jhipster;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.toxic2302.jhipster");

        noClasses()
            .that()
            .resideInAnyPackage("de.toxic2302.jhipster.service..")
            .or()
            .resideInAnyPackage("de.toxic2302.jhipster.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..de.toxic2302.jhipster.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
