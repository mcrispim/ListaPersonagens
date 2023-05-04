package com.example.listapersonagens.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.listapersonagens.R
import com.example.listapersonagens.data.model.CharacterType.DISNEY
import com.example.listapersonagens.data.model.CharacterType.RICKY_AND_MORTY
import com.example.listapersonagens.databinding.FragmentCharactersBinding
import com.example.listapersonagens.ui.utils.adapter.CharactersAdapter
import com.example.listapersonagens.ui.utils.extension.gone
import com.example.listapersonagens.ui.utils.extension.visible
import com.example.listapersonagens.viewmodels.CharactersFragmentViewModel

/*
- Qual o princípio e suas características?
    Single Responsability Principle: as unidades de código (classes e funções) devem ter
    uma e apenas uma razão para ser alterados, ou, segundo alguns autores, uma única função.

- Por quê foi utilizado?
    A classe CharactersFragment deveria ter uma única responsabilidade:
    exibir os personagens do tipo escolhido pelo usuário. Entretanto, ela
    foi elaborada para exibir os personagens de dois tipos diferentes,
    além de realizar a conexão com a Internet para buscar as informações
    a serem exibidas.
    Sendo assim estas outras funções extras serão extraídas para classes próprias
    (cad uma com apenas uma responsabilidade).

- Quais os ganhos essa implementação irá trazer caso o app cresça?
    A aplicação deste principio facilita a manutenção do código, permite que
    outros desenvolvedores tenham uma visão mais clara da função de cada classe (função).
 */

/*
- Qual o princípio e suas características?
    Open Closed Principle: as classes devem ser abertas para extensão, mas fechadas para
    modificação. Isto significa que as classes devem ser implementadasde forma a permitir
    modificações futuras que permitam exetender suas funções, mas sem permitir a modificação
    das funcionalidades já existentes.

- Por quê foi utilizado?
    A classe CharactersFragment acessa dados de duas fontes diferentes, através das APIs
    DisneyCharacters  e RickAndMortyCharacters. Ao invés de ter de conhecer os detalhes de
    cada uma das fontes, ela passará a acessar os dados de forma independente, através da
    função getCharacters(), a ser implementada por qualquer classe que implemente a interface
    CharacterService

- Quais os ganhos essa implementação irá trazer caso o app cresça?
    A aplicação deste principio facilita a manutenção do cédigo, permitindo que novas fontes
    de caracteres sejam adicionados ao programa, sem que o cédigo desta classe seja alterado.
*/

/*
- Qual o princípio e suas características?
    Liskov Closed Principle: as subclasses devem ser substituíveis por suas superclasses.

- Por quê foi utilizado?
    Com a utilização de uma interface (CharacterService) para definir fontes de dados para
    o programa, estamos aplicando o princípio de Substituição de Liskov toda vez que acessamos
    a função getCharacters() de uma classe que implementa esta interface.

- Quais os ganhos essa implementação irá trazer caso o app cresça?
    A aplicação deste principio facilita a manutenção do cédigo, permitindo que novas fontes
    de caracteres sejam adicionados ao programa, sem que o cédigo desta classe seja alterado.
*/

/*
- Qual o princípio e suas características?
    Interface Segregation Principle: o princípio diz que muitas interfaces específicas são
    melhores que uma interface genérica que implemente as funções das várias interfaces
    específicas. Os clientes não deveriam ser obrigados a implementar funções que eles não
    vão usar.

- Por quê foi utilizado?
    De certa forma, a interface CharacterService já realiza a plicação deste princípio, ao
    estabelecer apenas uma funcionalidade: trazer um conjunto de caracteres.

- Quais os ganhos essa implementação irá trazer caso o app cresça?
    A aplicação deste principio torna o código mais limpo, evitando a necessidade de
    implementar funçães que não utilizamos.
*/

/*
- Qual o princípio e suas características?
    Dependency Inversion Principle:  o princípio diz que as classes devem depender de
    abstrações (interfaces ou classes abstratas) e não de classes concretas.

- Por quê foi utilizado?
    Novamente, a implementação da interface CharacterService realiza também a aplicação
    deste princípio, ao fazer com que a classe CharactersFragment não dependa de implementações
    de classes concretas que implementam os serviços de acesso às fontes de dados.

- Quais os ganhos essa implementação irá trazer caso o app cresça?
    A aplicação deste principio nos fornece uma solução mais flexível, da mesma forma que o
    princípio aberto-fechado.
*/

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharactersFragmentViewModel
    //private var viewModel: CharactersFragmentViewModel by viewModels()

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val viewModel: CharactersFragmentViewModel by viewModels()
        setupView(viewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersFragmentViewModel::class.java)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView(viewModel: CharactersFragmentViewModel) {
        with(binding) {
            rvCharacters.adapter = charactersAdapter

            rgCharacterType.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rbCharacterTypeDisney -> viewModel.setCharacterType(DISNEY)
                    R.id.rbCharacterTypeRickyAndMorty -> viewModel.setCharacterType(RICKY_AND_MORTY)
                }
            }

            viewModel.characterType.observe(viewLifecycleOwner) {
                pbLoadingCharacters.visible()
                tvCharactersTypeTitle.text = it.title
                llCharactersTypeHeader.background = AppCompatResources.getDrawable(
                    requireContext(),
                    it.colorRes
                )
                Glide.with(binding.root)
                    .load(it.imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivCharactersTypeImage)
            }

            viewModel.characters.observe(viewLifecycleOwner) {
                charactersAdapter.submitList(it)
                pbLoadingCharacters.gone()
            }
        }
    }
}