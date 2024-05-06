package za.ac.ST110454008.snoozecatadventures

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private lateinit var EatprogressBar: ProgressBar///this codes are for the progress bar
    private lateinit var BathprogressBar: ProgressBar
    private lateinit var PlayprogressBar: ProgressBar

    private var hunger_level = 100f
    private var hungerDecreaseRate = 5f
    private var clean_level = 100f
    private var cleanDecreaseRate = 5f
    private var play_level = 100f
    private var playDecreaseRate =5f
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        ///declarations


        val imageset = findViewById<ImageView>(R.id.Garfieldset)///this is where the declarations start
        val Eatbutton = findViewById<Button>(R.id.Eat_Button)
        val Bathbutton = findViewById<Button>(R.id.Bath_Button)
        val Playbutton = findViewById<Button>(R.id.Play_Button)
        val Back_Button = findViewById<ImageButton>(R.id.Back_Button)
        val Action_text = findViewById<TextView>(R.id.prompt_feed)
        EatprogressBar = findViewById(R.id.Progress_Eat)
        BathprogressBar = findViewById(R.id.Progress_Bath)
        PlayprogressBar = findViewById(R.id.ProgressPlay)

        EatprogressBar.progress = hunger_level.toInt()
        val eatingCoroutine = launchEatingCoroutine()
        BathprogressBar.progress = clean_level.toInt()
        val cleaningCoroutine = launchCleaningCoroutine()
        PlayprogressBar.progress = play_level.toInt()
        val playCoroutine = launchPlayingCoroutine()


        Back_Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)/// this is the codes for the back button to allow the user to go back to the home page automatically once they have clicked on it

        }

        Eatbutton.setOnClickListener {
            imageset.setImageResource(R.drawable.eatingpicture)
            Action_text.text= "Snoozy is eating"///this code is to display what is actually happening as the images change  once you have pressed the "Feed button"
            hunger_level = 100f
            EatprogressBar.progress = hunger_level.toInt()

        }
            Bathbutton.setOnClickListener {
                imageset.setImageResource(R.drawable.bathingpicture)
                Action_text.text="Snoozy is bathing"///this code is to display what is actually happening as the images change  once you have pressed the "Bath Button"
                clean_level=100f
                BathprogressBar.progress = clean_level.toInt()
            }
                Playbutton.setOnClickListener{
                    imageset.setImageResource(R.drawable.playingpicture)
                    Action_text.text="Snoozy is playing"///this code is to display what is actually happening as the images change  once you have pressed the "Play Button"
                    play_level =100f
                    PlayprogressBar.progress = play_level.toInt()
                }

        }
    private fun launchEatingCoroutine(): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                if (hunger_level > 0f) {
                    hunger_level -= hungerDecreaseRate
                    EatprogressBar.progress = hunger_level.toInt()
                }
                delay(1000)
            }
        }
    }
    private fun launchCleaningCoroutine(): Job{
        return CoroutineScope(Dispatchers.Main).launch{
            while (isActive){
                if (clean_level > 0f){
                    clean_level -=cleanDecreaseRate
                    BathprogressBar.progress = clean_level.toInt()
                }
                delay(1000)
            }
        }
    }
    private fun launchPlayingCoroutine(): Job{
        return CoroutineScope(Dispatchers.Main).launch{
            while(isActive){
                if (play_level >0f){
                    play_level-=playDecreaseRate
                    PlayprogressBar.progress=play_level.toInt()
                }
                delay(1000)
            }



        }
    }


}


