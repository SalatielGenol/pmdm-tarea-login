package es.genol.login_udf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val mail = "test@test.es"
    private val password = "12345678"

    /*
    Variables para validar el mail y el password
    */
    private var isMailOK = false
    private var isPasswordOK = false
    val isDataVerified get() = isMailOK && isPasswordOK

    /*
    Variables de estado para manejo de la UI
    */
    var isLoginSuccess by mutableStateOf(false)
        private set

    var isLoginError by mutableStateOf(false)
        private set

    var loginStateText by mutableStateOf("")
        private set

    var mailStateText by mutableStateOf("")
        private set

    var passwordStateText by mutableStateOf("")
        private set

    var loginTopBarStateText by mutableStateOf("Login")
        private set

    /*
    Función que recibe los cambios del TextField y comprueba que sea um mail válido
    */
    fun onChangeMail(mail: String) {
        mailStateText = mail
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            isMailOK = true
        }
    }

    /*
    Función que recibe los cambios del TextField y comprueba que sea um password válido
    */
    fun onChangePassword(password: String) {
        passwordStateText = password
        if (password.length >= 8) {
            isPasswordOK = true
        }
    }


    /*
    Función que implementa la lógica del cambio de elementos en pantalla
    */
    fun onClickLogIn() {
        if (mailStateText == mail && passwordStateText == password) {
            isLoginSuccess = true
            isLoginError = false
            loginStateText = "Acceso correcto"
            loginTopBarStateText =
                mailStateText.substringBefore(delimiter = '@').capitalize(locale = Locale.current)
        } else {
            isLoginError = true
            loginStateText = "Email o contraseña inválidos"
        }
    }

    fun onClickLogOut() {
        isLoginSuccess = false
        loginTopBarStateText = "Login"
    }
}