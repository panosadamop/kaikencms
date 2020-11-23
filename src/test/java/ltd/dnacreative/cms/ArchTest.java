package ltd.dnacreative.cms;

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
            .importPackages("ltd.dnacreative.cms");

        noClasses()
            .that()
            .resideInAnyPackage("ltd.dnacreative.cms.service..")
            .or()
            .resideInAnyPackage("ltd.dnacreative.cms.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ltd.dnacreative.cms.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
