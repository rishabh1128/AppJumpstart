import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.appjumpstart.ui.AppViewModelProvider
import com.phoenix.appjumpstart.ui.navigation.AppRoutes
import com.phoenix.appjumpstart.ui.state.ItemDisplayViewModel

@Composable
fun AppBar(
    onFilterClicked: () -> Unit = {},
    onAddClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
    currentScreen: AppRoutes = AppRoutes.LIST_VIEW,
    viewModel: ItemDisplayViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.itemDisplayUiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFe6eaf6))
            .padding(
                horizontal = 32.dp,
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Title Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            // Title
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            // Filter Text or Add
            Text(
                text = stringResource(currentScreen.buttonText),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.clickable(onClick = { if (currentScreen.hasSearch) onFilterClicked() else onAddClicked() })
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        // Search Field
        if (currentScreen.hasSearch) {
            OutlinedTextField(
                value = uiState.searchValue,
                onValueChange = {
                    viewModel.updateSearchQuery(it)
                },
                placeholder = { Text("Search") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { viewModel.onSearchSubmit() }),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedPlaceholderColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    cursorColor = Color.Gray,
                    focusedContainerColor = Color(0xfff6f6f6),
                    unfocusedContainerColor = Color(0xfff6f6f6),
                    focusedBorderColor = Color(0xffe8e8e8),
                    unfocusedBorderColor = Color(0xffe8e8e8),
                    errorPlaceholderColor = Color.LightGray,
                    errorContainerColor = Color(0xfff6f6f6),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBar()
}

