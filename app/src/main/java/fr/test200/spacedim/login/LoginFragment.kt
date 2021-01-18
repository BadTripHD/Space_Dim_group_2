package fr.test200.spacedim.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fr.test200.spacedim.R
import fr.test200.spacedim.SpaceDim
import fr.test200.spacedim.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.login_fragment,
                container,
                false
        )
        viewModelFactory = LoginViewModelFactory(SpaceDim.userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Navigates back to game when button is pressed
        viewModel.eventGo.observe(viewLifecycleOwner, Observer { go ->
            if (go) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWaitingRoomFragment())
                viewModel.onGoComplete()
            }
        })

        return binding.root
    }

    /*private fun createEventRegisterUser(btnRegister: Button) {
        val oldColors: ColorStateList = login_info_register.textColors // switch white (loading) to red (error)

        btnRegister.setOnClickListener {
            login_info_register.setTextColor(oldColors) // white
            login_info_register.text = resources.getString(R.string.common_loading)
            val editTextPseudo: EditText = findViewById(R.id.login_pseudo)
            val pseudo: String = editTextPseudo.text.trim().toString()

            Utils.hideKeyboard(this)

            UserPost(pseudo).create(
                fun() { // success
                    runOnUiThread { // android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                        login_info_register.text = resources.getString(R.string.login_title_user_create)
                    }
                }, fun() { // unauthorized
                    runOnUiThread {
                        login_info_register.setTextColor(resources.getColor(R.color.text_error, theme))
                        login_info_register.text = resources.getString(R.string.login_title_already_exist)
                    }
                }, fun() { // error
                    runOnUiThread {
                        login_info_register.setTextColor(resources.getColor(R.color.text_error, theme))
                        login_info_register.text = resources.getString(R.string.login_title_fail)
                    }
                })
        }
    }

    override fun onBackPressed() {
        Utils.createDialog(
                this,
                resources.getString(R.string.login_title_leave),
                resources.getString(R.string.login_message_leave),
                true,
                fun() {
                    finish()
                }
        )
    }*/
}

