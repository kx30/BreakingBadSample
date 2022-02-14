package ru.nikolyashka.gateway.gateway

import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.domain.CharacterDetailsType
import ru.nikolyashka.gateway.Api
import ru.nikolyashka.gateways.CharacterDetailsGateway
import javax.inject.Inject

class RetrofitCharacterDetailsGateway @Inject constructor(
    private val api: Api,
    private val exceptionHandler: ErrorHandler
) : CharacterDetailsGateway {

    override suspend fun getCharacterById(id: Int): CharacterDetailsType = kotlin.runCatching {
        api.getCharacterById(id)
    }.fold(
        onSuccess = { it[0].map(it[0]) },
        onFailure = { CharacterDetailsType.CharacterDetailsErrorModel(exceptionHandler.defineErrorType(it)) }
    )
}