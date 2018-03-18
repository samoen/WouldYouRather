package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val maxGoodTypes = 3
    val maxBadTypes = 3
    val thingsYouWin = arrayOf("a battle","the superbowl","a marathon","an olympic event")
    val thingsYouGet = arrayOf("1000 bucks","a new car","a million subscibers","a house in beverly hills")
    val thingsYouMeet = arrayOf("Elon Musk","God","Einstein","Ghandi")
    val thingsYouCantUse = arrayOf("phone","computer","bed","car")
    val thingsYouGetHitBy = arrayOf("a car","an elephant","a bicycle","a big dude")
    val thingsYouMustEat = arrayOf("a bowl of manure","a fidget spinner","someone's finger","a rotten egg")

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
        val goodTypeKey = random.nextInt(maxGoodTypes)
        when(goodTypeKey){
            0-> {
                val winWords = listOf("win","are victorious in","are the winner of")
                val thingKey = random.nextInt(thingsYouWin.size)
                return Scenario("${winWords[random.nextInt(winWords.size)]} ${thingsYouWin[thingKey]}",thingKey.toString()+goodTypeKey.toString())
            }
            1-> {
                val meetWords = listOf("meet","have lunch with","talk to")
                val thingKey = random.nextInt(thingsYouMeet.size)
                return Scenario("${meetWords[random.nextInt(meetWords.size)]} ${thingsYouMeet[thingKey]}",thingKey.toString()+goodTypeKey.toString())
            }
            2-> {
                val getWords = listOf("get","receive","collect")
                val thingKey = random.nextInt(thingsYouGet.size)
                return Scenario("${getWords[random.nextInt(getWords.size)]} ${thingsYouGet[thingKey]}",thingKey.toString()+goodTypeKey.toString())
            }
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val random = Random()
        val badTypeKey = random.nextInt(maxBadTypes)
        when(badTypeKey){
            0 -> {
                val useWords = listOf("use","touch","look at")
                val thingKey = random.nextInt(thingsYouCantUse.size)
                return Scenario("can't ${useWords[random.nextInt(useWords.size)]} your ${thingsYouCantUse[thingKey]}",thingKey.toString()+badTypeKey.toString())
            }
            1 -> {
                val eatWords = listOf("eat","consume","swallow")
                val thingKey = random.nextInt(thingsYouMustEat.size)
                return Scenario("must ${eatWords[random.nextInt(eatWords.size)]} ${thingsYouMustEat[thingKey]}",thingKey.toString()+badTypeKey.toString())
            }
            2 -> {
                val hitWords = listOf("hit","smacked","banged")
                val thingKey = random.nextInt(thingsYouGetHitBy.size)
                return Scenario("get ${hitWords[random.nextInt(hitWords.size)]} by ${thingsYouGetHitBy[thingKey]}",thingKey.toString()+badTypeKey.toString())
            }
            else->return Scenario()
        }
    }
}
