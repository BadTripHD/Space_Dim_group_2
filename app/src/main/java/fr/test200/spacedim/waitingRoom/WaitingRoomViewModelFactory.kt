package fr.test200.spacedim.waitingRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.test200.spacedim.repository.UserRepository

class WaitingRoomViewModelFactory(private val repo: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaitingRoomViewModel::class.java)) {
            return WaitingRoomViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}