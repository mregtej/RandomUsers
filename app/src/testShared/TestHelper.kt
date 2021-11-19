import com.mregtej.randomusers.database.model.RandomUser
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.network.model.*
import java.text.ParseException
import java.text.SimpleDateFormat

object TestHelper {

    val MODESTO_USER = RandomUser(
        gender = "male",
        name = UserName(
            title = "Mr",
            firstName = "Modesto",
            lastName = "Rego"),
        location = LocationData(
            street = StreetData(
                number = 3,
                name = "Street"
            ),
            city = "city",
            state = "state",
            country = "country",
            postcode = "postcode",
            coordinates = Coordinates(0.0, 0.0),
            timezone = TimeZone("offset", "description")
        ),
        email = "modesto.rego.tejeda@gmail.com",
        login = LoginData(
            uuid = "1",
            username = "username",
            password = "password",
            salt = "salt",
            md5 = "md5",
            sha1 = "sha1",
            sha256 = "sha256"
        ),
        dob = CustomDate(parseDate(
            date = "2021-11-10")!!,
            age = 1),
        registered = CustomDate(parseDate(
            date = "2021-11-10")!!,
            age = 1),
        phone = "phone",
        cell = "cell",
        id = Id(
            name = "name",
            value = "value"
        ),
        picture = PictureData(
            large = "large",
            medium = "medium",
            thumbnail = "thumbnail"
        ),
        nationality = "ES"
    )

    private val CLARA_USER = RandomUser(
        gender = "female",
        name = UserName(
            title = "Miss",
            firstName = "Clara",
            lastName = "Schuhmacher"),
        location = LocationData(
            street = StreetData(
                number = 5,
                name = "Street"
            ),
            city = "city",
            state = "state",
            country = "country",
            postcode = "postcode",
            coordinates = Coordinates(0.0, 0.0),
            timezone = TimeZone("offset", "description")
        ),
        email = "clara.schuhmacher@gmail.com",
        login = LoginData(
            uuid = "2",
            username = "username",
            password = "password",
            salt = "salt",
            md5 = "md5",
            sha1 = "sha1",
            sha256 = "sha256"
        ),
        dob = CustomDate(parseDate(
            date = "2021-11-10")!!,
            age = 1),
        registered = CustomDate(parseDate(
            date = "2021-11-10")!!,
            age = 1),
        phone = "phone",
        cell = "cell",
        id = Id(
            name = "name",
            value = "value"
        ),
        picture = PictureData(
            large = "large",
            medium = "medium",
            thumbnail = "thumbnail"
        ),
        nationality = "ES"
    )

    const val MODESTO_UUID = "1"
    const val MODESTO_NAME = "Mr Modesto Rego"
    const val MODESTO_LOCATION = "3, Street, city, state"

    val MODESTO_USER_ENTITY = RandomUser(
        uuid = MODESTO_UUID,
        name = MODESTO_NAME,
        email = "modesto.rego.tejeda@gmail.com",
        photoUrl = "medium",
        phone = "phone",
        gender = "male",
        location = MODESTO_LOCATION,
        registeredDate = "Wed Nov 10 00:00:00 CET 2021"
    )

    private val MODESTO_USER_LIST_DATA = RandomUserListData(
        uuid = MODESTO_UUID,
        name = MODESTO_NAME,
        email = "modesto.rego.tejeda@gmail.com",
        photoUrl = "medium",
        phone = "phone"
    )

    val MODESTO_USER_DETAILED_DATA = RandomUserDetailsData(
        uuid = MODESTO_UUID,
        name = MODESTO_NAME,
        email = "modesto.rego.tejeda@gmail.com",
        photoUrl = "medium",
        phone = "phone",
        gender = "male",
        location = MODESTO_LOCATION,
        registeredDate = "Wed Nov 10 00:00:00 CET 2021"
    )

    private val CLARA_USER_ENTITY = RandomUser(
        uuid = "2",
        name = "Miss Clara Schuhmacher",
        email = "clara.schuhmacher@gmail.com",
        photoUrl = "medium",
        phone = "phone",
        gender = "female",
        location = "5, Street, city, state",
        registeredDate = "Wed Nov 10 00:00:00 CET 2021"
    )

    private val CLARA_USER_LIST_DATA = RandomUserListData(
        uuid = "2",
        name = "Miss Clara Schuhmacher",
        email = "clara.schuhmacher@gmail.com",
        photoUrl = "medium",
        phone = "phone"
    )

    val USERS = listOf(MODESTO_USER_LIST_DATA, CLARA_USER_LIST_DATA)

    val EXCEPTION = "java.lang.Exception: error test"
    val EXCEPTION_MSG = "error test"

    private fun parseDate(date: String) = try {
        SimpleDateFormat("yyyy-MM-dd").parse(date)
    } catch (e: ParseException) {
        null
    }
}