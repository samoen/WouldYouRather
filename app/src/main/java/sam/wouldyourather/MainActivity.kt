package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val TYPES_OF_GOOD_EXPERIENCES = 3 // You can win things, get things, meet people
    val TYPES_OF_BAD_EXPERIENCES = 3 // You can be denied use of things, get hit by things, and be forced to eat things
    val thingsYouWin = arrayOf("a chess tournament","the superbowl","a marathon","an olympic event")
    val winWords = listOf("win","are victorious in","are the winner of","place second in")
    val thingsYouGet = arrayOf("two thousand dollars","a new car","a million subscibers","a house in beverly hills")
    val getWords = listOf("get","receive","collect","inherit")
    val thingsYouMeet = arrayOf("Elon Musk","God","Einstein","Ghandi")
    val meetWords = listOf("meet","have lunch with","talk to","chat with")
    val thingsYouCantUse = arrayOf("phone","computer","bed","shoes")
    val useWords = listOf("use","touch","look at","see")
    val thingsYouGetHitBy = arrayOf("a boat","an elephant","a speeding bicycle","a big dude")
    val hitWords = listOf("hit","smacked","banged","bashed")
    val thingsYouMustEat = arrayOf("a bowl of manure","a fidget spinner","someone's finger","a rotten egg")
    val eatWords = listOf("eat","consume","swallow","snort")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topbutton.setOnClickListener { ShowNextQuestion() }
        bottombutton.setOnClickListener { ShowNextQuestion() }
        ShowNextQuestion()
    }

    fun ShowNextQuestion(){
        val topGood = GenerateGoodScenario()
        val topBad = GenerateBadScenario()
        var bottomGood = GenerateGoodScenario()
        var bottomBad = GenerateBadScenario()
        while (topGood.scenarioIdentifier.equals(bottomGood.scenarioIdentifier)){ // these checks prevent duplicate scenarios..
            bottomGood = GenerateGoodScenario()
        }
        while (topBad.scenarioIdentifier.equals(bottomBad.scenarioIdentifier)){ // ..they become less unlikely to trigger as more things are added
            bottomBad = GenerateBadScenario()
        }
        topbutton.text = "You ${topGood.scenarioString}, but you ${topBad.scenarioString}"
        bottombutton.text = "You ${bottomGood.scenarioString}, but you ${bottomBad.scenarioString}"
    }

    class Scenario(var scenarioString:String="", var scenarioIdentifier:String="")

    fun GenerateGoodScenario():Scenario{
        val random = Random()
        val goodThingNumber = random.nextInt(TYPES_OF_GOOD_EXPERIENCES)
        when(goodThingNumber){
            0->{
                val innerOptionNumber = random.nextInt(thingsYouWin.size)
                return Scenario("${winWords[random.nextInt(winWords.size)]} ${thingsYouWin[innerOptionNumber]}","${innerOptionNumber},${goodThingNumber}")
            }
            1->{
                val innerOptionNumber = random.nextInt(thingsYouMeet.size)
                return Scenario("${meetWords[random.nextInt(meetWords.size)]} ${thingsYouMeet[innerOptionNumber]}","${innerOptionNumber},${goodThingNumber}")
            }
            2->{
                val innerOptionNumber = random.nextInt(thingsYouGet.size)
                return Scenario("${getWords[random.nextInt(getWords.size)]} ${thingsYouGet[innerOptionNumber]}","${innerOptionNumber},${goodThingNumber}")
            }
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val random = Random()
        val badThingNumber = random.nextInt(TYPES_OF_BAD_EXPERIENCES)
        when(badThingNumber){
            0 -> {
                val innerOptionNumber = random.nextInt(thingsYouCantUse.size)
                return Scenario("can't ${useWords[random.nextInt(useWords.size)]} your ${thingsYouCantUse[innerOptionNumber]}","${innerOptionNumber},${badThingNumber}")
            }
            1 -> {
                val innerOptionNumber = random.nextInt(thingsYouMustEat.size)
                return Scenario("must ${eatWords[random.nextInt(eatWords.size)]} ${thingsYouMustEat[innerOptionNumber]}","${innerOptionNumber},${badThingNumber}")
            }
            2 -> {
                val innerOptionNumber = random.nextInt(thingsYouGetHitBy.size)
                return Scenario("get ${hitWords[random.nextInt(hitWords.size)]} by ${thingsYouGetHitBy[innerOptionNumber]}","${innerOptionNumber},${badThingNumber}")
            }
            else->return Scenario()
        }
    }
}
