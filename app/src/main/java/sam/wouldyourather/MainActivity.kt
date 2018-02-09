package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val typesOfGoodExperiences = 3 // You can win things, get things, meet people
    val typesOfBadExperiences = 3 // be denied use of things, hit by things, forced to eat things
    val thingsYouWin = arrayOf("a chess tournament","the superbowl","a marathon","an olympic event")
    val winWords = arrayOf("win","are victorious in","are the winner of","place second in")
    val thingsYouReceive = arrayOf("two thousand dollars","a new car","one million youtube subscribers","a house in beverly hills")
    val receiveWords = arrayOf("receive","get","collect","are the proud new owner of")
    val thingsYouMeet = arrayOf("Obama","God","Einstein","Ghandi")
    val meetWords = arrayOf("meet","have lunch with","talk to","chat with")
    val thingsYouCantUse = arrayOf("phone","computer","bed","shoes")
    val useWords = arrayOf("use","touch","look at","see")
    val thingsYouGetHitBy = arrayOf("a boat","an elephant","a speeding bicycle","a big dude")
    val hitWords = arrayOf("hit","smacked","banged","bashed")
    val thingsYouMustEat = arrayOf("a bowl of manure","a fidget spinner","someone's finger","a rotten egg")
    val eatWords = arrayOf("eat","consume","swallow","snort")

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
        while (topGood.scenarioIdentifier.equals(bottomGood.scenarioIdentifier)){ // these checks prevent duplicate scenarios
            bottomGood = GenerateGoodScenario()
        }
        while (topBad.scenarioIdentifier.equals(bottomBad.scenarioIdentifier)){ // rarely triggers as more things are added
            bottomBad = GenerateBadScenario()
        }
        topbutton.text = "You ${topGood.scenarioString}, but you ${topBad.scenarioString}"
        bottombutton.text = "You ${bottomGood.scenarioString}, but you ${bottomBad.scenarioString}"
    }

    class Scenario(var scenarioString:String="", var scenarioIdentifier:String="")

    fun GenerateGoodScenario():Scenario{
        val random = Random()
        val goodThingType = random.nextInt(typesOfGoodExperiences)
        when(goodThingType){
            0->{
                val innerOption = random.nextInt(thingsYouWin.size)
                return Scenario("${winWords[random.nextInt(winWords.size)]} ${thingsYouWin[innerOption]}","${innerOption} ${goodThingType}")
            }
            1->{
                val innerOption = random.nextInt(thingsYouMeet.size)
                return Scenario("${meetWords[random.nextInt(meetWords.size)]} ${thingsYouMeet[innerOption]}","${innerOption} ${goodThingType}")
            }
            2->{
                val innerOption = random.nextInt(thingsYouReceive.size)
                return Scenario("${receiveWords[random.nextInt(receiveWords.size)]} ${thingsYouReceive[innerOption]}","${innerOption} ${goodThingType}")
            }
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val random = Random()
        val badThingType = random.nextInt(typesOfBadExperiences)
        when(badThingType){
            0->{
                val innerOption = random.nextInt(thingsYouCantUse.size)
                return Scenario("can't ${useWords[random.nextInt(useWords.size)]} your ${thingsYouCantUse[innerOption]}","${innerOption} ${badThingType}")
            }
            1->{
                val innerOption = random.nextInt(thingsYouMustEat.size)
                return Scenario("must ${eatWords[random.nextInt(eatWords.size)]} ${thingsYouMustEat[innerOption]}","${innerOption} ${badThingType}")
            }
            2->{
                val innerOption = random.nextInt(thingsYouGetHitBy.size)
                return Scenario("get ${hitWords[random.nextInt(hitWords.size)]} by ${thingsYouGetHitBy[innerOption]}","${innerOption} ${badThingType}")
            }
            else->return Scenario()
        }
    }
}
