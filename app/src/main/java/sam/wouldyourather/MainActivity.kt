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
        val r = Random()
        val n = r.nextInt(maxGoodTypes)
        when(n){
            0-> {
                val winWords = listOf("win","are victorious in","are the winner of")
                val num = r.nextInt(thingsYouWin.size)
                return Scenario("${winWords[r.nextInt(winWords.size)]} ${thingsYouWin[num]}",num.toString()+n.toString())
            }
            1-> {
                val num = r.nextInt(thingsYouMeet.size)
                return Scenario("meet ${thingsYouMeet[num]}",num.toString()+n.toString())
            }
            2-> {
                val num = r.nextInt(thingsYouGet.size)
                return Scenario("get ${thingsYouGet[num]}",num.toString()+n.toString())
            }
            else->return Scenario()
        }
    }

    fun GenerateBadScenario():Scenario{
        val r = Random()
        val n = r.nextInt(maxBadTypes)
        when(n){
            0 -> {
                val useWords = listOf("use","touch","look at")
                val num = r.nextInt(thingsYouCantUse.size)
                return Scenario("can't ${useWords[r.nextInt(useWords.size)]} your ${thingsYouCantUse[num]}",num.toString()+n.toString())
            }
            1 -> {
                val eatWords = listOf("eat","consume","swallow")
                val num = r.nextInt(thingsYouMustEat.size)
                return Scenario("must ${eatWords[r.nextInt(eatWords.size)]} ${thingsYouMustEat[r.nextInt(thingsYouMustEat.size)]}",num.toString()+n.toString())
            }
            2 -> {
                val hitWords = listOf("hit","smacked","banged")
                val num = r.nextInt(thingsYouHitBy.size)
                return Scenario("get ${hitWords[r.nextInt(hitWords.size)]} by ${thingsYouHitBy[r.nextInt(thingsYouHitBy.size)]}",num.toString()+n.toString())
            }
            else->return Scenario()
        }
    }
}
