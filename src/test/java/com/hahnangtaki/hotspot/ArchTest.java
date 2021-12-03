package com.hahnangtaki.hotspot;

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
            .importPackages("com.hahnangtaki.hotspot");

        noClasses()
            .that()
            .resideInAnyPackage("com.hahnangtaki.hotspot.service..")
            .or()
            .resideInAnyPackage("com.hahnangtaki.hotspot.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.hahnangtaki.hotspot.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
