package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val maxGoodTypes = 3
    val maxBadTypes = 3
    val thingsYouWin = arrayOf("trophy","basketball game","marathon")
    val thingsYouGet = arrayOf("1000 bucks","a car","a million subs")
    val thingsYouMeet = arrayOf("Elon Musk","God","Einstein")
    val thingsYouCantUse = arrayOf("phone","computer","bed")
    val thingsYouHitBy = arrayOf("car","elephant","bicycle")
    val thingsYouMustConsume = arrayOf("bowl of manure","glass of lemon juice","finger")

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
        while (topGood == bottomGood){
            bottomGood = GenerateGoodScenario()
        }
        while (topBad == bottomBad){
            bottomBad = GenerateBadScenario()
        }
        topbutton.text = "You $topGood, but you $topBad"
        bottombutton.text = "You $bottomGood, but you $bottomBad"
    }

    fun GenerateGoodScenario():String{
        val r = Random()
        val n = r.nextInt(maxGoodTypes)
        when(n){
            0-> return "win a ${thingsYouWin[r.nextInt(thingsYouWin.size)]}"
            1-> return "meet ${thingsYouMeet[r.nextInt(thingsYouMeet.size)]}"
            2-> return "get ${thingsYouGet[r.nextInt(thingsYouGet.size)]}"
            else->return "error"
        }
    }

    fun GenerateBadScenario():String{
        val r = Random()
        val n = r.nextInt(maxBadTypes)
        when(n){
            0-> return "can't use your ${thingsYouCantUse[r.nextInt(thingsYouCantUse.size)]}"
            1-> return "must consume a ${thingsYouMustConsume[r.nextInt(thingsYouMustConsume.size)]}"
            2->{
                val hitWords = listOf("hit","smacked","banged")
                return "get ${hitWords[r.nextInt(hitWords.size)]} by a ${thingsYouHitBy[r.nextInt(thingsYouHitBy.size)]}"
            }
            else->return "error"
        }
    }
}
