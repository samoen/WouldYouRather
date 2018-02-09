package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val maxGoodTypes = 3
    val maxBadTypes = 3
    val thingsYouWin = arrayOf("a battle","the superbowl","a marathon")
    val thingsYouGet = arrayOf("1000 bucks","a car","a million subs")
    val thingsYouMeet = arrayOf("Elon Musk","God","Einstein")
    val thingsYouCantUse = arrayOf("phone","computer","bed")
    val thingsYouHitBy = arrayOf("a car","an elephant","a bicycle")
    val thingsYouMustEat = arrayOf("a bowl of manure","a fidget spinner","someone's finger")

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
        while (topGood.scenarioNumber.equals(bottomGood.scenarioNumber)){
            bottomGood = GenerateGoodScenario()
        }
        while (topBad.scenarioNumber.equals(bottomBad.scenarioNumber)){
            bottomBad = GenerateBadScenario()
        }
        topbutton.text = "You ${topGood.scenarioString}, but you ${topBad.scenarioString}"
        bottombutton.text = "You ${bottomGood.scenarioString}, but you ${bottomBad.scenarioString}"
    }

    class Scenario(var scenarioString:String="", var scenarioNumber:Int=0)

    fun GenerateGoodScenario():Scenario{
        val r = Random()
        val n = r.nextInt(maxGoodTypes)
        when(n){
            0-> {
                val winWords = listOf("win","are victorious in","are the winner of")
                return Scenario("${winWords[r.nextInt(winWords.size)]} ${thingsYouWin[r.nextInt(thingsYouWin.size)]}",n)
            }
            1-> return Scenario("meet ${thingsYouMeet[r.nextInt(thingsYouMeet.size)]}",n)
            2-> return Scenario("get ${thingsYouGet[r.nextInt(thingsYouGet.size)]}",n)
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val r = Random()
        val n = r.nextInt(maxBadTypes)
        when(n){
            0 -> {
                val useWords = listOf("use","touch","look at")
                return Scenario("can't ${useWords[r.nextInt(useWords.size)]} your ${thingsYouCantUse[r.nextInt(thingsYouCantUse.size)]}",n)
            }
            1 -> {
                val eatWords = listOf("eat","consume","swallow")
                return Scenario("must ${eatWords[r.nextInt(eatWords.size)]} ${thingsYouMustEat[r.nextInt(thingsYouMustEat.size)]}",n)
            }
            2 -> {
                val hitWords = listOf("hit","smacked","banged")
                return Scenario("get ${hitWords[r.nextInt(hitWords.size)]} by ${thingsYouHitBy[r.nextInt(thingsYouHitBy.size)]}",n)
            }
            else->return Scenario()
        }
    }
}
