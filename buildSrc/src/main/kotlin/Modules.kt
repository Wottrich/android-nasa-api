import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

fun DependencyHandlerScope.moduleResource() {
    "implementation"(project(":resource"))
}

fun DependencyHandlerScope.openModuleScreenState() {
    "api"(project(":screen-state"))
}

fun DependencyHandlerScope.moduleDatasource() {
    "implementation"(project(":datasource"))
}

fun DependencyHandlerScope.moduleData() {
    "implementation"(project(":data"))
}

fun DependencyHandlerScope.moduleCommonUiCompose() {
    "implementation"(project(":common-ui-compose"))
}

fun DependencyHandlerScope.moduleUiHome() {
    "implementation"(project(":ui-home"))
}

fun DependencyHandlerScope.moduleUiAPOD() {
    "implementation"(project(":ui-apod"))
}

fun DependencyHandlerScope.moduleCommonResources() {
    "implementation"(project(":common-resources"))
}

fun DependencyHandlerScope.moduleCommonAndroid() {
    "implementation"(project(":common-android"))
}

fun DependencyHandlerScope.moduleDomain() {
    "implementation"(project(":domain"))
}