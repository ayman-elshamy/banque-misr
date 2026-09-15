package com.example.banquemisr

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.banquemisr.ui.theme.BanqueMisrTheme
import com.example.banquemisr.ui.theme.Red


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BanqueMisrTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    // -----------------------------
    // TextField states
    // -----------------------------

    val usernameTextField = rememberTextFieldState()
    val passwordTextField = rememberTextFieldState()


    // -----------------------------
    // Password visibility state
    // -----------------------------

    var passVisibility by remember {
        mutableStateOf(false)
    }


    // -----------------------------
    // Password visibility icon
    // -----------------------------

    val visibilityIcon =
        if (passVisibility) {
            R.drawable.baseline_visibility_24
        } else {
            R.drawable.baseline_visibility_off_24
        }


    // -----------------------------
    // Current language
    // -----------------------------

    val appLocales = AppCompatDelegate.getApplicationLocales()

    val currentLocaleTag =
        appLocales.get(0)?.toLanguageTag() ?: "en"


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {


        // =============================
        // Header
        // =============================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.bm_icon
                ),
                contentDescription = stringResource(
                    R.string.bank_masr_image_cd
                )

            )


            TextButton(
                onClick = {

                    val newLanguageTag =
                        if (currentLocaleTag == "en") {
                            "ar"
                        } else {
                            "en"
                        }

                    val localeList =
                        LocaleListCompat.forLanguageTags(
                            newLanguageTag
                        )

                    AppCompatDelegate.setApplicationLocales(
                        localeList
                    )
                }
            ) {

                Text(
                    text =
                        if (currentLocaleTag == "en") {
                            "العربية"
                        } else {
                            "EN"
                        },

                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Red
                )
            }
        }


        // =============================
        // Username
        // =============================

        OutlinedTextField(
            state = usernameTextField,

            label = {
                Text(
                    text = stringResource(
                        R.string.login_username
                    )
                )
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )


        // =============================
        // Password
        // =============================

        OutlinedSecureTextField(
            state = passwordTextField,

            label = {
                Text(
                    text = stringResource(
                        R.string.login_password
                    )
                )
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            textObfuscationMode =
                if (passVisibility) {
                    TextObfuscationMode.Visible
                } else {
                    TextObfuscationMode.Hidden
                },

            trailingIcon = {

                IconButton(
                    onClick = {
                        passVisibility = !passVisibility
                    }
                ) {

                    Icon(
                        painter = painterResource(
                            id = visibilityIcon
                        ),
                        contentDescription = stringResource(
                            R.string.login_password_hide_icon
                        )
                    )
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )


        // =============================
        // Forgot password / username
        // =============================

        Text(
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        color = Red,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {

                    append(
                        stringResource(
                            R.string.login_forgot_password_or_username
                        )
                    )
                }
            },

            modifier = Modifier.padding(top = 16.dp)
        )


        // =============================
        // Login button
        // =============================

        Button(
            onClick = {},

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(55.dp),

            shape = RoundedCornerShape(8.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Red
            )
        ) {

            Text(
                text = stringResource(
                    R.string.login_btn
                ),

                fontSize = 18.sp
            )
        }


        // =============================
        // Help
        // =============================

        Text(
            text = buildAnnotatedString {

                append(
                    stringResource(
                        R.string.login_help_1
                    )
                )

                withStyle(
                    style = SpanStyle(
                        color = Red,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {

                    append(
                        stringResource(
                            R.string.login_help_2
                        )
                    )
                }
            },

            modifier = Modifier.padding(top = 20.dp)
        )


        // =============================
        // Divider
        // =============================

        HorizontalDivider(
            modifier = Modifier.padding(
                top = 24.dp,
                bottom = 24.dp
            )
        )


        // =============================
        // Bottom options
        // =============================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            // -------------------------
            // Our products
            // -------------------------

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.our_products
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Text(
                    text = stringResource(
                        R.string.login_our_products
                    ),
                    fontSize = 12.sp
                )
            }


            // -------------------------
            // Exchange rate
            // -------------------------

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.exchange_rate
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Text(
                    text = stringResource(
                        R.string.login_exchange_rate
                    ),
                    fontSize = 12.sp
                )
            }


            // -------------------------
            // Security tips
            // -------------------------

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.security_tips
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Text(
                    text = stringResource(
                        R.string.login_security_tips
                    ),
                    fontSize = 12.sp
                )
            }


            // -------------------------
            // Nearest branch / ATM
            // -------------------------

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.nearest_branch_or_atm
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Text(
                    text = stringResource(
                        R.string.login_nearest_branch
                    ),
                    fontSize = 12.sp
                )
            }
        }
    }
}