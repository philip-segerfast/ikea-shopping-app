package dev.psegerfast.ikeashoppingapp

import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5_000L

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestProductList {

    private lateinit var device: UiDevice
    private lateinit var context: Context

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertNotNull(launcherPackage)
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        context = ApplicationProvider.getApplicationContext()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BuildConfig.APPLICATION_ID
        )!!.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BuildConfig.APPLICATION_ID).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun ensureProductListIsNotEmpty() {
        val productList = device.findObject(By.res("ProductList"))
        assertNotNull(productList)
        assertTrue(productList.childCount > 0)
    }

    @Test
    fun testAddItemToCart() {
        val productList = device.findObject(By.res("ProductList"))
        assertNotNull(productList)
        assertTrue(productList.childCount > 0)

        // Find the first product in the list and press add to cart
        val firstProduct = productList.children[0]
        val firstProductName = firstProduct.findObject(By.res("ProductName")).text
        println("First product name: $firstProductName")
        val addToCartButton = firstProduct.findObject(By.desc(context.getString(R.string.add_to_cart)))
        assertNotNull(addToCartButton)
        addToCartButton.click()
        // Wait for the bottom sheet drag handle
        device.wait(
            Until.hasObject(By.res("BottomSheetDragHandle")),
            LAUNCH_TIMEOUT
        )
        val bottomSheetDragHandle = device
            .findObject(By.res("BottomSheetDragHandle"))
        assertNotNull(bottomSheetDragHandle)
        // Click on the bottom sheet handle to open the cart
        bottomSheetDragHandle.drag(Point(0, 0))
        // Get a hold of the cart list
        device.wait(
            Until.hasObject(By.res("CartList")),
            LAUNCH_TIMEOUT
        )
        val cartList = device.findObject(By.res("CartList"))
        assertNotNull(cartList)
        // Assert that it contains a product with the text of the first product
        val cartItem = cartList.findObject(By.text(firstProductName))
        assertNotNull(cartItem)
    }
}