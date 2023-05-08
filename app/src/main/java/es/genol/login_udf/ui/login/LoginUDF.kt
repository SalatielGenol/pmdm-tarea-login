package es.genol.login_udf.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import es.genol.login_udf.ui.LoginViewModel
import es.genol.login_udf.ui.theme.LoginUDFTheme

@Composable
fun LoginUDF() {
    /*
    Me he dado cuenta de que si se instancia la clase del viewModel, aun teniendo definida
    la herencia, si no se inicializa llamando a la función viewModel(), no se mantienen
    los cambios en pantalla al girar el dispositivo o cambiar el tema, etc. Asi que se
    pierde funcionalidad.

    Supongo que el poder pasarle parámetros al viewModel está mas pensado para cuando
    se utilicen flows, pero con estados mutables no funciona correctamente.
    */
    val viewModel: LoginViewModel = viewModel()

    LoginUDFTheme {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = viewModel.loginTopBarStateText, fontSize = 22.sp)
                    if (viewModel.isLoginSuccess) {
                        Button(
                            onClick = { viewModel.onClickLogOut() },
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(text = "Log Out")
                        }
                    }
                }
                if (viewModel.isLoginError || viewModel.isLoginSuccess) {
                    Text(
                        text = viewModel.loginStateText,
                        fontSize = 14.sp,
                        color = if (viewModel.isLoginError)
                            MaterialTheme.colors.error
                        else Color.Unspecified
                    )
                }
                if (!viewModel.isLoginSuccess || viewModel.isLoginError) {
                    OutlinedTextField(
                        value = viewModel.mailStateText,
                        onValueChange = { viewModel.onChangeMail(it) },
                        label = { Text(text = "Email") },
                        isError = viewModel.isLoginError,
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = viewModel.passwordStateText,
                        onValueChange = { viewModel.onChangePassword(it) },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Contraseña") },
                        isError = viewModel.isLoginError,
                        singleLine = true
                    )
                    Button(
                        onClick = { viewModel.onClickLogIn() },
                        enabled = viewModel.isDataVerified,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Log In")
                    }
                }
                Spacer(modifier = Modifier.fillMaxHeight(.9f))
            }
        }
    }
}
