package fr.test200.spacedim.dashboard

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import fr.test200.spacedim.R
import fr.test200.spacedim.SpaceDim
import fr.test200.spacedim.dataClass.Event
import fr.test200.spacedim.dataClass.State
import fr.test200.spacedim.databinding.DashboardFragmentBinding
import fr.test200.spacedim.network.WSListener
import fr.test200.spacedim.waitingRoom.WaitingRoomViewModel
import fr.test200.spacedim.waitingRoom.WaitingRoomViewModelFactory

enum class moduleTypes {
    BUTTON, SWITCH
}

class DashboardFragment : Fragment() {

    private lateinit var binding: DashboardFragmentBinding

    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(SpaceDim.webSocket)
    }

    private var soundAmbiance: MediaPlayer? = null
    private var tictac: MediaPlayer? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.dashboard_fragment,
                container,
                false
        )

        soundAmbiance = MediaPlayer.create(this.activity, R.raw.ambiance_dashboard)
        tictac = MediaPlayer.create(this.activity, R.raw.tictac)
        soundAmbiance?.isLooping = true
        tictac?.isLooping = true
        soundAmbiance?.start()
        tictac?.start()

        binding.dashboardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val moduleTypeList = mutableListOf<String>()
        val moduleMaxNumber = 9;
        val moduleNumber = (4..moduleMaxNumber).random()

        ObjectAnimator.ofInt(binding.progressBar, "progress", 100)
                .setDuration(10000)
                .start();

        for (moduleIndex in 0..moduleNumber) {
            val moduleType = moduleTypes.values()[(moduleTypes.values().indices).random()]
            moduleTypeList.add(moduleType.toString())
        }

        //création des boutons
        makeList(moduleTypeList, moduleNumber)

        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer<Boolean> { isFinished ->
            if (isFinished) gameFinishedWin()
        })

        viewModel.getWebSocketState().observe(viewLifecycleOwner, Observer {
            updateWebSocketState(it)
        })

        return binding.root
    }

    private fun updateWebSocketState(event: Event?) {
        when(event){
            is Event.GameStarted -> {

            }
        }
    }

    override fun onPause() {
        super.onPause()
        soundAmbiance?.pause()
        tictac?.pause()
    }

    override fun onResume() {
        super.onResume()
        soundAmbiance?.start()
        tictac?.start()
    }

    private fun makeList(moduleTypeList: MutableList<String>, moduleNumber: Int) {
        val numberOfRow: Int = (moduleTypeList.size / 2)
        var countButton = 0
        for (rowIndex in 0..numberOfRow) {
            val row = TableRow(this.context)
            row.gravity = 17
            for ((index, type) in moduleTypeList.withIndex()) {
                if (countButton < moduleNumber && (index == rowIndex || index == rowIndex + 1)) {
                    val element = Button(this.context)
                    /*when (type) {
                            "SWITCH" -> {
                                element = Button(this.context)
                            }
                            "BUTTON" -> {
                                element = Switch(this.context)
                            }
                            else -> {
                                print("don't find type")
                            }
                        }*/



                    element.setOnClickListener(View.OnClickListener {
                        MediaPlayer.create(this.activity, R.raw.button_click).start()
                    })

                    element.text = "tric"
                    val params = TableRow.LayoutParams( 500, 145 ).also { it.setMargins(25, 25, 25, 25) }
                    element.layoutParams = params
                    row.addView(element)
                    countButton ++
                }
            }
            binding.tabletruc.addView(row)
        }
    }


    fun gameFinishedWin() {
        val action = DashboardFragmentDirections.actionDashboardFragmentToEndFragment()
        action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishedComplete()
    }
}