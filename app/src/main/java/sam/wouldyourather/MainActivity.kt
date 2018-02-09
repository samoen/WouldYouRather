package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val goodThings = arrayListOf<String>(
            "Get $1000 cash",
            "Throw the best party ever",
            "Have magic hard drive with every movie on it",
            "Get a free car",
            "Own 10 lemon trees",
            "Gain a million subscribers",
            "Choose a software platform and build a career"
    )

    val badThings = arrayListOf<String>(
            "fall off a cliff",
            "get hit by a bicycle",
            "must eat manure",
            "must drink a glass of lemon juice",
            "step on a nail",
            "lose all your subscribers",
            "fall into the trap of cross-platform development"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topbutton.setOnClickListener { ShowNextQuestion() }
        bottombutton.setOnClickListener { ShowNextQuestion() }
        ShowNextQuestion()
    }

    fun ShowNextQuestion(){
        val topGoodRandom = Random().nextInt(goodThings.size)
        val topBadRandom = Random().nextInt(badThings.size)
        var BottomGoodRandom = Random().nextInt(goodThings.size)
        var BottomBadRandom = Random().nextInt(badThings.size)
        while (topGoodRandom == BottomGoodRandom){
            BottomGoodRandom = Random().nextInt(goodThings.size)
        }
        while(BottomBadRandom == topBadRandom){
            BottomBadRandom = Random().nextInt(badThings.size)
        }
        topbutton.text = "${goodThings[topGoodRandom]}, but you ${badThings[topBadRandom]}"
        bottombutton.text = "${goodThings[BottomGoodRandom]}, but you ${badThings[BottomBadRandom]}"
    }
}
