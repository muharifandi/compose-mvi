package com.muh.arifandi.dicoding.baselineprofile

import androidx.benchmark.macro.Direction
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a basic baseline profile for the target package.
 *
 * We recommend you to perform the following steps:
 * 1. Register the module as a dependency in the target project.
 * 2. Run the generator.
 * 3. Use the generated profile in the target project.
 */
@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() = baselineProfileRule.collect(
        packageName = "com.muh.arifandi.dicoding",
        includeInStartupProfile = true
    ) {
        pressHome()
        startActivityAndWait()

        // Optimize Scrolling
        device.findObject(By.res("article_item"))?.fling(Direction.DOWN)
        
        // Optimize Detail Navigation
        device.findObject(By.res("article_item"))?.click()
        device.waitForIdle()
    }
}
