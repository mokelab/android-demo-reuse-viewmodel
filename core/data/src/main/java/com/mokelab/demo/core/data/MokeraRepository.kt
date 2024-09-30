package com.mokelab.demo.core.data

import com.mokelab.demo.core.model.Mokera

/**
 * Repository for Mokera
 */
interface MokeraRepository {
    /**
     * Get all Mokeras
     */
    suspend fun getAll(): List<Mokera>
}