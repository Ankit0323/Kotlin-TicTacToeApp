package com.example.tictactoe

import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit  var binding:ActivityMainBinding
   var count=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.NoActionBar)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.reset.setOnClickListener(object: View.OnClickListener{
           override  fun onClick(v :View ){
               //list= arrayListOf(2,2,2,2,2,2,2,2,2)
               count=0

               resume=true
               for(slot in 0..8){
                   list[slot]=2

               }
               binding.root.children.forEach{
                   if(it !is Button && it !is LinearLayout)
                   {
                       val imageslot=it as ImageView
                       if(imageslot.tag !=null)
                       {
                           imageslot.setImageDrawable(null)
                       }
                   }
               }

            }
        } )
                binding.playAgain.setOnClickListener(object: View.OnClickListener{
            override  fun onClick(v :View ){
                //list= arrayListOf(2,2,2,2,2,2,2,2,2)
                count=0
                v.visibility=View.GONE
                resume=true
                for(slot in 0..8){
                    list[slot]=2

                }
                binding.root.children.forEach{
                    if(it !is Button && it !is LinearLayout)
                    {
                        val imageslot=it as ImageView
                        if(imageslot.tag !=null)
                        {
                            imageslot.setImageDrawable(null)
                        }
                    }
                }

            }
        })




    }


    var list=arrayListOf(2,2,2,2,2,2,2,2,2)
    var sol=listOf(listOf(0,1,2),
            listOf(3,4,5),
            listOf(6,7,8),
            listOf(0,3,6),
            listOf(1,4,7),
            listOf(2,5,8),
            listOf(0,4,8),
            listOf(2,4,6),)
    var value=0
    var resume= true
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun drop(view : View){
        count++
        if(count==1){
            binding.reset.visibility=View.VISIBLE
        }

       val image=view as ImageView

        var tag=image.tag.toString().toInt()

        if(list[tag]==2 && resume==true) {




                image.animate().scaleX(0f).scaleY(0f)
                if (value == 0) {
                    list[tag]=0
                    //image.animate().scaleX(0f).scaleY(0f)

                    image.setImageResource(R.drawable.o)
                    image.animate().scaleX(1f).scaleY(1f).setDuration(3000)
                    value = 1
                } else {
                    list[tag]=1
                    image.setImageResource(R.drawable.x)
                    image.animate().scaleX(1f).scaleY(1f).setDuration(3000)
                    value = 0

                }
            if(!list.contains(2)){
                Toast.makeText(this,"match tied",Toast.LENGTH_LONG).show()
                binding.playAgain.visibility=View.VISIBLE
                binding.reset.visibility=View.GONE
            }

            for(ans in sol){
                if(list[ans[0]]==list[ans[1]] &&list[ans[1]]==list[ans[2]] && list[ans[0]]!=2){
                    resume=false
                    if(value==0){


                        Toast.makeText(this,"X is a winner",Toast.LENGTH_LONG).show()
                       

                    }else
                    {
                        Toast.makeText(this,"O is a winner",Toast.LENGTH_LONG).show()
                    }

                    val playButton=binding.playAgain
                    playButton.visibility=View.VISIBLE
                    binding.reset.visibility=View.GONE

                }
            }



        }
    }
}