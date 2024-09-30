package com.mokelab.demo.core.data

import com.mokelab.demo.core.model.Mokera
import com.mokelab.demo.core.model.MokeraType
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Demo implementation of MokeraRepository
 */
class DemoMokeraRepository @Inject constructor(): MokeraRepository {
    override suspend fun getAll(): List<Mokera> {
        delay(1000)
        return listOf(
            Mokera(id = 1, name = "Original", type = MokeraType.MelonSoda),
            Mokera(id = 2, name = "Milk", type = MokeraType.Milk),
            Mokera(id = 3, name = "Coffee", type = MokeraType.Coffee),
            Mokera(id = 4, name = "OrangeJuice", type = MokeraType.OrangeJuice),
        )
    }
}