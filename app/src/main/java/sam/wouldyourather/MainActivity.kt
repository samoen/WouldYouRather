package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val maxGoodThingNumber = 3
    val maxBadThingNumber = 3
    val thingsYouWin = arrayOf("a battle","the superbowl","a marathon","an olympic event")
    val winWords = listOf("win","are victorious in","are the winner of")
    val thingsYouGet = arrayOf("1000 bucks","a new car","a million subscibers","a house in beverly hills")
    val getWords = listOf("get","receive","collect")
    val thingsYouMeet = arrayOf("Elon Musk","God","Einstein","Ghandi")
    val meetWords = listOf("meet","have lunch with","talk to")
    val thingsYouCantUse = arrayOf("phone","computer","bed","car")
    val useWords = listOf("use","touch","look at")
    val thingsYouGetHitBy = arrayOf("a car","an elephant","a bicycle","a big dude")
    val hitWords = listOf("hit","smacked","banged")
    val thingsYouMustEat = arrayOf("a bowl of manure","a fidget spinner","someone's finger","a rotten egg")
    val eatWords = listOf("eat","consume","swallow")

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
        while (topGood.scenarioIdentifier.equals(bottomGood.scenarioIdentifier)){
            bottomGood = GenerateGoodScenario()
        }
        while (topBad.scenarioIdentifier.equals(bottomBad.scenarioIdentifier)){
            bottomBad = GenerateBadScenario()
        }
        topbutton.text = "You ${topGood.scenarioString}, but you ${topBad.scenarioString}"
        bottombutton.text = "You ${bottomGood.scenarioString}, but you ${bottomBad.scenarioString}"
    }

    class Scenario(var scenarioString:String="", var scenarioIdentifier:String="")

    fun GenerateGoodScenario():Scenario{
        val random = Random()
        val goodThingNumber = random.nextInt(maxGoodThingNumber)
        when(goodThingNumber){
            0-> {
                val innerOptionNumber = random.nextInt(thingsYouWin.size)
                return Scenario("${winWords[random.nextInt(winWords.size)]} ${thingsYouWin[innerOptionNumber]}",innerOptionNumber.toString()+goodThingNumber.toString())
            }
            1-> {
                val innerOptionNumber = random.nextInt(thingsYouMeet.size)
                return Scenario("${meetWords[random.nextInt(meetWords.size)]} ${thingsYouMeet[innerOptionNumber]}",innerOptionNumber.toString()+goodThingNumber.toString())
            }
            2-> {
                val innerOptionNumber = random.nextInt(thingsYouGet.size)
                return Scenario("${getWords[random.nextInt(getWords.size)]} ${thingsYouGet[innerOptionNumber]}",innerOptionNumber.toString()+goodThingNumber.toString())
            }
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val random = Random()
        val badThingNumber = random.nextInt(maxBadThingNumber)
        when(badThingNumber){
            0 -> {
                val innerOptionNumber = random.nextInt(thingsYouCantUse.size)
                return Scenario("can't ${useWords[random.nextInt(useWords.size)]} your ${thingsYouCantUse[innerOptionNumber]}",innerOptionNumber.toString()+badThingNumber.toString())
            }
            1 -> {
                val innerOptionNumber = random.nextInt(thingsYouMustEat.size)
                return Scenario("must ${eatWords[random.nextInt(eatWords.size)]} ${thingsYouMustEat[innerOptionNumber]}",innerOptionNumber.toString()+badThingNumber.toString())
            }
            2 -> {
                val innerOptionNumber = random.nextInt(thingsYouGetHitBy.size)
                return Scenario("get ${hitWords[random.nextInt(hitWords.size)]} by ${thingsYouGetHitBy[innerOptionNumber]}",innerOptionNumber.toString()+badThingNumber.toString())
            }
            else->return Scenario()
        }
    }
}
