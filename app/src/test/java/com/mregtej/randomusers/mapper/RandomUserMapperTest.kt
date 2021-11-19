package com.mregtej.randomusers.mapper

import TestHelper.MODESTO_LOCATION
import TestHelper.MODESTO_NAME
import TestHelper.MODESTO_USER
import TestHelper.MODESTO_USER_ENTITY
import com.mregtej.randomusers.mapper.RandomUserMapper.formatLocation
import com.mregtej.randomusers.mapper.RandomUserMapper.formatName
import com.mregtej.randomusers.mapper.RandomUserMapper.mapToRandomUserEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RandomUserMapperTest {

    @Test
    fun testMapToRandomUserEntity() {
        // Act
        val entity = mapToRandomUserEntity(MODESTO_USER)

        // Assert
        assertThat(entity).isEqualTo(MODESTO_USER_ENTITY)
    }

    @Test
    fun testFormatName() {
        // Act
        val name = MODESTO_USER.formatName()

        // Assert
        assertThat(name).isEqualTo(MODESTO_NAME)
    }

    @Test
    fun testFormatLocation() {
        // Act
        val location = MODESTO_USER.formatLocation()

        // Assert
        assertThat(location).isEqualTo(MODESTO_LOCATION)
    }
}