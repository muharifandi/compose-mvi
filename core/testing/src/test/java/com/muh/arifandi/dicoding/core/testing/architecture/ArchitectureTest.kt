package com.muh.arifandi.dicoding.core.testing.architecture

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.Test

class ArchitectureTest {

    private val allProjectClasses = ClassFileImporter()
        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
        .importPackages("com.muh.arifandi.dicoding")

    @Test
    fun `domain layer should not depend on data or ui layer`() {
        classes().that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat()
            .resideInAnyPackage("..domain..", "..core.model..", "java..", "kotlin..", "kotlinx.coroutines..")
            .check(allProjectClasses)
    }

    @Test
    fun `data layer should not depend on ui layer`() {
        noClasses().that().resideInAPackage("..data..")
            .should().dependOnClassesThat().resideInAPackage("..ui..")
            .check(allProjectClasses)
    }

    @Test
    fun `feature modules should not depend on other feature implementations`() {
        noClasses().that().resideInAPackage("..features.(*).impl..")
            .should().dependOnClassesThat().resideInAPackage("..features.(*).impl..")
            .because("Feature implementation details should be isolated. Communication must happen via :api modules.")
            .check(allProjectClasses)
    }

    @Test
    fun `core modules should not depend on feature modules`() {
        noClasses().that().resideInAPackage("..core..")
            .should().dependOnClassesThat().resideInAPackage("..features..")
            .check(allProjectClasses)
    }

    @Test
    fun `viewmodels should not depend on data layer directly`() {
        noClasses().that().haveSimpleNameEndingWith("ViewModel")
            .should().dependOnClassesThat().resideInAPackage("..data..")
            .because("ViewModels should only communicate with the Domain layer (UseCases or Repositories).")
            .check(allProjectClasses)
    }
}
