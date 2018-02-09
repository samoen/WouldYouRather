package sam.wouldyourather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topbutton.setOnClickListener { ShowNextQuestion() }
        bottombutton.setOnClickListener { ShowNextQuestion() }
        ShowNextQuestion()
    }
    fun ShowNextQuestion(){
        val q = Question()
        val topGoodString = q.GetRandomGoodQuestion()
        val topBadString = q.GetRandomBadQuestion()
        var bottomGoodString = q.GetRandomGoodQuestion()
        var bottomBadString = q.GetRandomBadQuestion()
        while (topGoodString == bottomGoodString){
            bottomGoodString = q.GetRandomGoodQuestion()
        }
        while (topBadString == bottomBadString){
            bottomBadString = q.GetRandomBadQuestion()
        }
        topbutton.text = "You $topGoodString, but you $topBadString"
        bottombutton.text = "You $bottomGoodString, but you $bottomBadString"
    }

    enum class GoodThingType { WINNING, GETTING, MEETING }

    enum class BadThingType { CANTUSE, HITBY, MUSTCONSUME }

    class Question{
        val thingsYouWin = arrayOf("trophy","basketball game","marathon")
        val thingsYouGet = arrayOf("1000 bucks","a car","a million subs")
        val thingsYouMeet = arrayOf("Elon Musk","God","Einstein")
        val thingsYouCantUse = arrayOf("phone","computer","bed")
        val thingsYouHitBy = arrayOf("car","elephant","bicycle")
        val thingsYouMustConsume = arrayOf("bowl of manure","glass of lemon juice","finger")
        fun GetRandomGoodQuestion():String{
            val r = Random()
            val n = r.nextInt(GoodThingType.values().size)
            val type = GoodThingType.values()[n]
            when(type){
                GoodThingType.WINNING-> return "win a ${thingsYouWin[r.nextInt(thingsYouWin.size)]}"
                GoodThingType.MEETING-> return "meet ${thingsYouMeet[r.nextInt(thingsYouMeet.size)]}"
                GoodThingType.GETTING-> return "get ${thingsYouGet[r.nextInt(thingsYouGet.size)]}"
            }
        }
        fun GetRandomBadQuestion():String{
            val r = Random()
            val n = r.nextInt(BadThingType.values().size)
            val type = BadThingType.values()[n]
            when(type){
                BadThingType.CANTUSE-> return "can't use your ${thingsYouCantUse[r.nextInt(thingsYouCantUse.size)]}"
                BadThingType.MUSTCONSUME-> return "must consume a ${thingsYouMustConsume[r.nextInt(thingsYouMustConsume.size)]}"
                BadThingType.HITBY->{
                    val hitWords = listOf("hit","smacked","banged")
                    return "get ${hitWords[r.nextInt(hitWords.size)]} by a ${thingsYouHitBy[r.nextInt(thingsYouHitBy.size)]}"
                }
            }
        }
    }
}
