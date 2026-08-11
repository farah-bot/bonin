package com.bonin.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonin.ui.theme.BoninTheme

@Composable
fun HomeScreen(
    userName: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                HomeHeader(
                    userName = userName,
                    onLogoutClick = onLogoutClick
                )
            }

            item {
                BalanceCard()
            }

            item {
                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Your groups",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                GroupCard(
                    name = "Trip Bandung",
                    description = "4 people • Rp1.950.000 spent",
                    balance = "You are owed Rp125.000",
                    positive = true
                )
            }

            item {
                GroupCard(
                    name = "Kost Agustus",
                    description = "3 people • Rp2.400.000 spent",
                    balance = "You owe Rp75.000",
                    positive = false
                )
            }

            item {
                GroupCard(
                    name = "Bukber kantor",
                    description = "6 people • Rp840.000 spent",
                    balance = "Settled up",
                    positive = true
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Create a group",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    onLogoutClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Good morning,",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        TextButton(
            onClick = onLogoutClick
        ) {
            Text("Logout")
        }
    }
}

@Composable
private fun BalanceCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Overall balance",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "+ Rp50.000",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Across all your groups",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun GroupCard(
    name: String,
    description: String,
    balance: String,
    positive: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = balance,
                style = MaterialTheme.typography.labelLarge,
                color = if (positive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeScreenPreview() {
    BoninTheme {
        HomeScreen(
            userName = "Farah",
            onLogoutClick = {}
        )
    }
}