package com.mregtej.randomusers.mapper

import androidx.annotation.VisibleForTesting
import com.mregtej.randomusers.database.model.RandomUser as RandomUserEntity
import com.mregtej.randomusers.network.model.RandomUser as RandomUserNetwork

object RandomUserMapper {

    fun mapToRandomUserEntity(user: RandomUserNetwork) = RandomUserEntity(
        uuid = user.login.uuid,
        name = user.formatName(),
        email = user.email,
        photoUrl = user.picture.medium,
        phone = user.phone,
        gender = user.gender,
        location = user.formatLocation(),
        registeredDate = user.registered.date.toString()
    )

    @VisibleForTesting
    fun RandomUserNetwork.formatName() =
        "${name.title} ${name.firstName} ${name.lastName}"

    @VisibleForTesting
    fun RandomUserNetwork.formatLocation() =
        "${location.street.number}, ${location.street.name}, ${location.city}, ${location.state}"
}