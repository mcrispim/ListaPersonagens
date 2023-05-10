package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.listapersonagens.R
import com.example.listapersonagens.databinding.FragmentLoginBinding
import com.example.listapersonagens.data.remote.authentication.FirebaseAuthenticator
import com.example.listapersonagens.ui.viewmodels.CharactersFragmentViewModel
import com.example.listapersonagens.ui.viewmodels.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: LoginFragmentViewModel

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
        setupView()
    }
    
    private fun setupView() {
        with(binding) {
            btnLogin.setOnClickListener {
//                val isLoggedIn = FirebaseAuthenticator.login(tietEmail.text.toString(), tietPassword.text.toString())
                viewModel.setIsLogged(tietEmail.text.toString(), tietPassword.text.toString())
            }

            viewModel.tries.observe(viewLifecycleOwner) {
                if (viewModel.isLogged.value!!)
                    findNavController().navigate(R.id.action_loginFragment_to_charactersFragment)
                else
                    Toast.makeText(requireContext(), "Usuário não encontrado.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}